package com.turing.manage.service;

import java.util.List;

import com.turing.manage.entity.Subject;
import com.turing.manage.page.SubjectBigPage;

public interface ISubjectBigService {
	/**
	 * 查询所有数据
	 * @param page
	 * @return
	 */
	List<Subject> queryAll(SubjectBigPage page);
	/**
	 * 验证名字是否重复
	 * @param subjectName
	 * @return
	 */
	int querysubjectBigname(String subjectName);
	/**
	 * 添加保存
	 * @param sj
	 * @return
	 */
	int addSave(Subject sj);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	Subject queryBigSubjectById(String id);
	/**
	 * 修改
	 * @param sj
	 * @return
	 */
	int editSave(Subject sj);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	String delete(String ids);


}
