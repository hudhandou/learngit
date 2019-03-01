package com.turing.manage.service;

import java.util.List;

import com.turing.manage.entity.Subject;
import com.turing.manage.entity.Topic;
import com.turing.manage.page.TopicPage;

public interface ITopicService {
	/**
	 * 查询所有数据
	 * @param page
	 * @return
	 */
	List<Topic> queryAll(TopicPage page);
	/**
	 * 转到修改页面
	 * @param id
	 * @return
	 */
	Topic queryById(String id);
	/**
	 * 根据id回显下拉框
	 * @return
	 */
	List<Subject> query();
	/**
	 * 添加保存
	 * @param t
	 * @return
	 */
	int save(Topic t);
	/**
	 * 根据id删除
	 * @param ids
	 * @return
	 */
	String delete(String ids);
	/**
	 * 修改保存
	 * @param t
	 * @return
	 */
	int edit(Topic t);
	

}
