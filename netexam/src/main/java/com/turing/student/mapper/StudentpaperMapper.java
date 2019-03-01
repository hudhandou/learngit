package com.turing.student.mapper;

import org.apache.ibatis.annotations.Select;

import com.turing.student.entity.Studentpaper;

public interface StudentpaperMapper {
    int deleteByPrimaryKey(String studentpaperId);

    int insert(Studentpaper record);

    int insertSelective(Studentpaper record);

    Studentpaper selectByPrimaryKey(String studentpaperId);

    int updateByPrimaryKeySelective(Studentpaper record);

    int updateByPrimaryKey(Studentpaper record);
    @Select("select count(*) ge from studentpaper where paper_id = #{param1}")
	int queryCountStudengtPaper(String string);
}