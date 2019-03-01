package com.turing.manage.service;

import java.util.List;


import com.turing.manage.entity.Subject;
import com.turing.manage.page.SubjectPage;

public interface ISubjectService {
	/**
	 * 查询所有数据
	 * @param page
	 * @return
	 */
	List<Subject> queryAllBysubject(SubjectPage page);
	/**
	 * 验证名字是否重复
	 * @param subjectName
	 * @return
	 */
	int querysubjectname(String subjectName);
	/**
	 * 添加
	 * @param sj
	 * @return
	 */
	int addSave(Subject sj);

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
	int delete(String ids);
	/**
	 * 查询大类别
	 * @return
	 */
	List<Subject> queryBig();

}
