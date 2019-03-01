package com.turing.student.service;

import java.util.List;

import com.turing.manage.entity.Paper;
import com.turing.manage.entity.Topic;

public interface ExamService {
	/**
	 * 查询试卷
	 */
	List<Paper> queryStudentPaper();
	/**
	 * 查询题目根据id
	 * @param id
	 * @return
	 */
	List<Topic> queryToppic(String id);

}
