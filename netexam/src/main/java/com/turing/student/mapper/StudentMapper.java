package com.turing.student.mapper;

import org.apache.ibatis.annotations.Select;

import com.turing.student.entity.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    /**
     * 注册提交
     * @param stu
     * @return
     */
	int save(Student stu);
	/**
	 * 查询学生试卷
	 * @param string
	 * @return
	 */
	@Select("select count(*) ge from sys_user where user_name = #{param1}")
	int queryCountStudengtPaper(String string);
}