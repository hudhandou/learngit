package com.turing.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turing.framework.util.TreeNode;
import com.turing.manage.entity.Paper;
import com.turing.manage.entity.Subject;
import com.turing.manage.entity.SubjectPaperTable;
import com.turing.manage.mapper.PaperMapper;
import com.turing.manage.mapper.SubjectMapper;
import com.turing.manage.mapper.SubjectPaperTableMapper;
import com.turing.manage.page.PaperPage;
import com.turing.manage.service.IPaperService;
import com.turing.student.mapper.StudentMapper;
@Service
public class PaperService implements IPaperService{
	/**
	 * 试卷mapper
	 */
	@Autowired
	private PaperMapper mapper;
	/**
	 * 学科mapper
	 */
	@Autowired
	private SubjectMapper submapper;
	/**
	 * 试卷表mapper
	 */
	@Autowired
	private SubjectPaperTableMapper sptmapper;
	/**
	 * 学生试卷mapper
	 */
	@Autowired
	private StudentMapper stumapper;
	@Override
	public List<Paper> queryAll(PaperPage page) {
		
		return mapper.queryAll(page);
	}
	/**
	 * 查询所有数据
	 */
	@Override
	public List<TreeNode>querySubjectTree(String id) {
		//根据父id 查子学科
		List<Subject> subList=submapper.querysujectByPid(id);	
		List<TreeNode>treeList=new ArrayList<TreeNode>();
		//把Subject数据传到treeNode
		for (Subject s : subList) {
			TreeNode node=new TreeNode();
			node.setId(s.getSubjectId());
			node.setText(s.getSubjectName());
			if (!"1".equals(id)) { // 如果id不是1 就说明是小学科的 那么就加入自定义标签 levele
				node.getAttributes().put("level", "2");
			}
			//判断是否有子节点
			List<Subject> childList=submapper.querysujectByPid(s.getSubjectId());
			if (childList !=null && !childList.isEmpty()) {
				//node.setState("close");
			} else {
				node.setState("open");	
			}
			treeList.add(node);
		}		
		return treeList;
		
	}
	/**
	 * 添加保存
	 */
	@Override
	@Transactional
	public int addsave(Paper p, String ids) {
		//添加一个试卷 
		p.setPaperId(UUID.randomUUID().toString());
		p.setPaperCreatetime(new Date());
		p.setPaperState("0");//不能考试
		int tiao =mapper.insertSelective(p);
		//添加试卷科目表
			addSubjectPaper(p, ids);
		
		return tiao;
	}

	private void addSubjectPaper(Paper p, String ids) {
		String[] subjctIds= ids.split(",");
		for (String subjctId : subjctIds) {
			SubjectPaperTable spt=new SubjectPaperTable();
			spt.setSubjectPaperId(UUID.randomUUID().toString());
			spt.setPaperId(p.getPaperId());
			spt.setSubjectId(subjctId);
			sptmapper.insertSelective(spt);
		}
	}

	/**
	 * 修改保存
	 */
	@Override
	@Transactional
	public int editsave(Paper p, String ids) {
		// 删除当前试卷的题目试卷中间表的数据
		sptmapper.deleteByPaperId(p.getPaperId());
		int tiao = mapper.updateByPrimaryKeySelective(p);
		//添加试卷科目表
		addSubjectPaper(p, ids);
		return tiao;
	}
	/**
	 * 查询学生试卷的id
	 */
	@Override
	public List<String> querySubjectPaerById(String paperId) {
		List<SubjectPaperTable> sptlist = sptmapper.queryquerySubjectPaerById(paperId);
		List<String> idlist = new ArrayList<String>();	
		for (SubjectPaperTable subjectPaperTable : sptlist) {
			idlist.add(subjectPaperTable.getSubjectId());
		}
		return idlist;
	}

	@Override
	public Paper queryPaperById(String id) {
	
		return mapper.selectByPrimaryKey(id);
	}

	 /**
     * 删除
     */
	@Transactional
	@Override
	public String delete(String ids) {
		String names = "";
		String[] strs = ids.split(",");
		for (int i = 0; i < strs.length; i++) {
			String id = strs[i];
			//验证是否能够删除
			int ge = mapper.deleteYanZheng(id);
			if(ge>0) {
				String paperName = mapper.getpaperNameById(id);
				names=names+paperName+"";
			}else {
				mapper.deleteByPrimaryKey(id);
			}
		}
		if(!"".equals(names)) {
			names = names.substring(0,names.length()-1);  //去掉尾部逗号
		}
		return names;
	}

	@Override
	public int kaoshi(Paper p) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(p);
	}
}
