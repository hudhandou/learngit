package com.turing.student.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.framework.util.MD5Util;
import com.turing.manage.entity.Classs;
import com.turing.manage.mapper.ClasssMapper;
import com.turing.student.entity.Student;
import com.turing.student.mapper.StudentMapper;
import com.turing.student.service.IStudentService;
import com.turing.system.entity.SysRole;
import com.turing.system.entity.SysUser;
import com.turing.system.entity.SysUserRole;
import com.turing.system.mapper.SysRoleMapper;
import com.turing.system.mapper.SysUserMapper;
import com.turing.system.mapper.SysUserRoleMapper;
@Service
public class StuentServiceImpl implements IStudentService {
    /**
     * 学生接口
     */
	@Autowired
	private StudentMapper stumapper;
	/**
	 * 班级的mapper
	 */
	@Autowired
	private ClasssMapper mapper;
	/**
	 * 角色的mapper
	 */
	@Autowired
	private SysRoleMapper sysmapper;
	/**
	 * 用户mapper
	 */
	@Autowired
	private SysUserMapper usermapper;
	/**
	 * 角色中间表关联
	 */
	@Autowired
	private SysUserRoleMapper sumapper; 
	/**
	 * 提交保存
	 */
	@Override
	public int save(Student stu,SysUser user) {
		//添加用户
		user.setUserId(UUID.randomUUID().toString());
		user.setUserState("A");
		user.setUserName(stu.getStudentName());
		System.out.println(user.getStudentPassword()+"+++++++++");
		user.setUserPass(MD5Util.md5(user.getStudentPassword()));
		int tiao1=usermapper.insertSelective(user);

		//添加角色中间表关联
		SysUserRole sysUser=new SysUserRole();
		sysUser.setUrId(System.currentTimeMillis()+"");
		sysUser.setRoleId("a662ac70-6d4a-449b-ab10-df39ceb98032");
		sysUser.setUserId(user.getUserId());
		int tiao2=sumapper.insertSelective(sysUser);
		//添加学生
		stu.setStudentId(System.currentTimeMillis()+"");
		stu.setStudentTel(user.getNickName());
		stu.setStudentName(user.getUserName());
		stu.setUserId(user.getUserId());
		int tiao3=stumapper.insertSelective(stu);
		
		return (tiao1+tiao2+tiao3) == 3 ? 1 : 0 ;	
	} 
	/**
	 * 查询班级
	 */
	@Override
	public Classs queryClass() {
		
		return mapper.queryClass();
	}



	

}
