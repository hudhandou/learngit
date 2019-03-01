package com.turing.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turing.manage.entity.Subject;
import com.turing.manage.mapper.SubjectMapper;
import com.turing.manage.page.SubjectBigPage;
import com.turing.manage.service.ISubjectBigService;
@Service
public class SubjectBigServiceImpl implements ISubjectBigService {
	@Autowired
	private SubjectMapper mapper;
    /**
     * 查询
     */
	@Override
	public List<Subject> queryAll(SubjectBigPage page) {
		
		return mapper.queryAll(page);
	}
	/**
	 * 验证名字是否重复
	 */
	@Override
	public int querysubjectBigname(String subjectName) {
		
		return mapper.querysubjectBigname(subjectName);
	}
	/**
	 * 添加保存
	 */
	@Transactional
	@Override
	public int addSave(Subject sj) {
		sj.setSubjectId(System.currentTimeMillis()+"");
		sj.setSubjectPid("1");
		return mapper.insertSelective(sj);
	}
	/**
	 * 根据id查询大类别
	 */
	@Override
	public Subject queryBigSubjectById(String id) {
		return mapper.selectByPrimaryKey(id);
	}
	/**
	 * 修改保存
	 */
	@Transactional
	@Override
	public int editSave(Subject sj) {
		
		return mapper.updateByPrimaryKeySelective(sj);
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
				String ClassName = mapper.getsubjectNameById(id);
				names=names+ClassName+"";
			}else {
				mapper.deleteByPrimaryKey(id);
			}
		}
		if(!"".equals(names)) {
			names = names.substring(0,names.length()-1);  //去掉尾部逗号
		}
		return names;
	}
}
