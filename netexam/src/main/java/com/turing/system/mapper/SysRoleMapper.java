package com.turing.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;

import com.turing.framework.select.SelectOptions;
import com.turing.system.entity.SysRole;
import com.turing.system.page.RolePage;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	List<SelectOptions> queryAllRoleWithOptions();

	List<SelectOptions> queryRoleByUserWithOptions(String id);
	
	@SelectProvider(method="pageSql",type=RolePage.class)
	@ResultMap("BaseResultMap")
	List<SysRole> query(RolePage page);
	
	@SelectProvider(method="allSql" ,type=RolePage.class)
	@ResultMap("BaseResultMap")
	List<SysRole> queryAll(RolePage page);
	/**
	 * 查询学生的id
	 * @return
	 */
	SysRole queryRoleId();

}