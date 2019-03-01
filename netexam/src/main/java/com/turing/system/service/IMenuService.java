package com.turing.system.service;

import java.util.List;

import com.turing.framework.util.TreeNode;
import com.turing.system.entity.SysMenu;

public interface IMenuService {
	/**
	 * 查询所有一级菜单并将其子菜单放在menuList中
	 * @return
	 */
	List<SysMenu> queryAllWithList();

	SysMenu queryById(String id);

	void save(SysMenu menu);

	void update(SysMenu menu);

	void deleteById(String id);
	/**
	 * 查询所有菜单
	 * @return
	 */
	List<TreeNode> querySysMenu(String pid);
	/**
	 * 删除菜单
	 * @param id 主键
	 * @return
	 */
	int delete(String id);
	/**
	 * 添加保存
	 * @param menu
	 * @return
	 */
	SysMenu addsave(SysMenu menu);
	/**
	 * 修改保存
	 * @param menu
	 * @return
	 */
	int editsave(SysMenu menu);

}
