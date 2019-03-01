package com.turing.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.turing.framework.select.SelectOptions;
import com.turing.system.entity.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    /**
     * 查询所有一级菜单
     * @return
     */
	List<SysMenu> queryAllMenuByTop();
	/**
	 * 查询指定一级菜单下的子菜单
	 */
	List<SysMenu> queryAllMenuByOne(String menuId);
	/**
	 * 根据用户Id来查询用户的一级菜单
	 * @param userId
	 * @return
	 */
	List<SysMenu> queryMenuByTop(String userId);
	/**
	 * 查询指定用户第一个一级菜单的所有子菜单
	 * @param menu
	 * @return
	 */
	List<SysMenu> queryMenuByOne(SysMenu menu);

	List<SelectOptions> queryAllMenuByTopWithOptions();

	List<SelectOptions> queryMenuByOneWithOptions(SelectOptions sp);
	/**
	 * 根据pid查询子菜单
	 * @param pid
	 * @return
	 */
	@Select("select menu_id, menu_url, menu_name, menu_islink, menu_sequ, menu_pare, menu_state, menu_info, menu_ico from sys_menu where menu_pare=#{pid} order by menu_sequ")
	@ResultMap("BaseResultMap")
	List<SysMenu> queryMenuByPid(String pid);
}