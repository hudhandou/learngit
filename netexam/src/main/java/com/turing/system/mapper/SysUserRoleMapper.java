package com.turing.system.mapper;

import com.turing.system.entity.SysUserRole;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String urId);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String urId);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

	void deleteByUserId(String userId);
}