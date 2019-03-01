package com.turing.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turing.manage.entity.Subject;
import com.turing.manage.mapper.SubjectMapper;
import com.turing.manage.page.SubjectPage;
import com.turing.manage.service.ISubjectService;
@Service
public class SubjectServiceImpl implements ISubjectService {
	/**
	 * 科目接口
	 */
	@Autowired
	private SubjectMapper mapper;

     /**
      * 
      * 查询科目
      */
	@Override
	public List<Subject> queryAllBysubject(SubjectPage page) {
		
		return mapper.queryAllBysubject(page);
	}
	/**
	 * 验证科目名称是否重复
	 */
	@Override
	public int querysubjectname(String subjectName) {
		return mapper.querysubjectname(subjectName);
	}
	/**
	 * 添加保存
	 */
	@Transactional
	@Override
	public int addSave(Subject sj) {
		sj.setSubjectId(System.currentTimeMillis()+"");
		return mapper.insertSelective(sj);
	}
	/**
	 * 根据id查询科目
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
	public int delete(String ids) {
		int tiao = 0;
		String[] strs = ids.split(",");
		for (int i = 0; i < strs.length; i++) {
			String id = strs[i];			
			tiao =	 mapper.deleteByPrimaryKey(id);		
		}
		
		return tiao;
	}
	/**
	 * 查询大类别
	 */
	@Override
	public List<Subject> queryBig() {
		return mapper.queryAllBig();
	}
   
}
