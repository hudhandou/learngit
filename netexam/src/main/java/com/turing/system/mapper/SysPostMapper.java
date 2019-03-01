package com.turing.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import com.turing.system.entity.SysPost;
import com.turing.system.page.PosPage;

public interface SysPostMapper {
    int deleteByPrimaryKey(String spId);

    int insert(SysPost record);

    int insertSelective(SysPost record);

    SysPost selectByPrimaryKey(String spId);

    int updateByPrimaryKeySelective(SysPost record);

    int updateByPrimaryKey(SysPost record);

	List<SysPost> selective(SysPost sp);

	@SelectProvider(method="pageSql",type=PosPage.class)
	@ResultMap("BaseResultMap")
	List<SysPost> query(PosPage page);

	@SelectProvider(method="allSql" ,type=PosPage.class)
	@ResultMap("BaseResultMap")
	List<SysPost> queryAll(PosPage page);
}