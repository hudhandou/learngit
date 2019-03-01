package com.turing.student.service;

import com.turing.manage.entity.Classs;
import com.turing.student.entity.Student;
import com.turing.system.entity.SysUser;

public interface IStudentService {
	/**
	 * 提交注册
	 * @param stu
	 * @param user 
	 * @return
	 */
	int save(Student stu, SysUser user);
	/**
	 * 显示班级
	 * @param c
	 * @return
	 */
	Classs queryClass();
	

	

}
