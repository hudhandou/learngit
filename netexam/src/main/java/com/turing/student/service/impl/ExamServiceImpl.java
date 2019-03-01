package com.turing.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.manage.entity.Paper;
import com.turing.manage.entity.Topic;
import com.turing.manage.mapper.PaperMapper;
import com.turing.manage.mapper.TopicMapper;
import com.turing.student.service.ExamService;
@Service
public class ExamServiceImpl implements ExamService {
    /**
     * 试卷接口
     */
	@Autowired
	private PaperMapper papermapper;
	/**
	 * 题目接口
	 */
	@Autowired
	private TopicMapper topmapper; 
	/**
	 * 查询可以考试的试卷
	 */
	@Override
	public List<Paper> queryStudentPaper() {
		return papermapper.queryStudentPaper();
	}
     /**
      * 查询考试页面题目
      */
	@Override
	public List<Topic> queryToppic(String id) {
		// TODO Auto-generated method stub
		return topmapper.queryTopById(id);
	}

}
