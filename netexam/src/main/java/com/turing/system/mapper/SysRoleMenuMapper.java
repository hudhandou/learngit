package com.turing.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.turing.system.entity.SysRoleMenu;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(String rmid);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String rmid);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

	List<SysRoleMenu> queryMenuByRoleId(String id);

	void deleteByRoleId(String roleId);
	/**
	 * 删除 角色菜单表的数据
	 * @param id 菜单主键
	 * @return
	 */
	@Delete("delete from sys_role_menu  where  menu_id=#{id}")
	int deleteRoleMenuByMenuId(String id);
}