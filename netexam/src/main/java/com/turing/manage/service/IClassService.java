package com.turing.manage.service;

import java.util.List;

import com.turing.manage.entity.Classs;
import com.turing.manage.page.ClassPage;

public interface IClassService {

	/**
	 * 查询所有数据
	 * @param page 
	 * @return
	 */
	List<Classs> queryAll(ClassPage page);
	/**
	 * 转到修改页面根据id
	 * @param id
	 * @return
	 */
	Classs queryById(String id);
	/**
	 * 添加保存
	 * @param c
	 * @return
	 */
	int save(Classs c);
	/**
	 * 修改保存
	 * @param c
	 * @return
	 */
	int edit(Classs c);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	String delete(String ids);
	/**
	 * 验证名字是否重复
	 * @param className
	 * @return
	 */
	int checkName(String className);
	
	/**
	 * 设置默认班级
	 * @param id
	 * @return
	 */
	int moren(Classs c, String id);




	
}
