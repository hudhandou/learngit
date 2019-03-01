package com.turing.manage.service;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.turing.framework.util.TreeNode;
import com.turing.manage.entity.Paper;
import com.turing.manage.entity.Subject;
import com.turing.manage.page.PaperPage;

public interface IPaperService {
	/**
	 * 查询所有数据
	 * @param page 
	 * @return
	 */
	List<Paper> queryAll(PaperPage page);
	/**
	 * 查询学科
	 * @param id
	 * @return
	 */
	
	List<TreeNode> querySubjectTree(String id);
	/**
	 * 添加保存
	 * @param p
	 * @param ids
	 * @return
	 */
	int addsave(Paper p, String ids);
	/**
	 * 修改添加
	 * @param p
	 * @param ids
	 * @return
	 */
	int editsave(Paper p, String ids);
	/**
	 * 查询试卷的 所有学科id
	 * @param paperId
	 * @return
	 */
	List<String> querySubjectPaerById(String paperId);
	/**
	 * 转到修改页面
	 * @param id
	 * @return
	 */
	Paper queryPaperById(String id);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	String delete(String ids);
	/**
	 * 设置考试
	 * @param p
	 * @return
	 */
	int kaoshi(Paper p);

	

}
