package com.turing.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.system.entity.OperLog;
import com.turing.system.entity.SysDept;
import com.turing.system.entity.SysMenu;
import com.turing.system.entity.SysPerson;
import com.turing.system.entity.SysUser;
import com.turing.system.mapper.OperLogMapper;
import com.turing.system.mapper.SysDeptMapper;
import com.turing.system.mapper.SysMenuMapper;
import com.turing.system.mapper.SysPersonMapper;
import com.turing.system.mapper.SysUserMapper;
import com.turing.system.page.OperLogPage;
import com.turing.system.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	private SysMenuMapper menuMapper;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysPersonMapper ppMapper;
	@Autowired
	private SysDeptMapper deptMapper;
	/**
	 * 系统日志mapper
	 */
	@Autowired
	private OperLogMapper logMapper;
	
	@Override
	public List<SysMenu> queryAllMenuByTop() {	
		return menuMapper.queryAllMenuByTop();
	}

	@Override
	public List<SysMenu> queryAllMenuByOne() {
		List<SysMenu> oneList=this.queryAllMenuByTop();
		return menuMapper.queryAllMenuByOne(oneList.get(0).getMenuId());
	}

	@Override
	public List<SysMenu> queryMenuByTop(SysUser user) {
		return menuMapper.queryMenuByTop(user.getUserId());
	}

	@Override
	public List<SysMenu> queryMenuByOne(SysUser user) {
		List<SysMenu> oneList=this.queryMenuByTop(user);
		SysMenu topMenu = oneList.get(0);
		topMenu.setUserId(user.getUserId());
		List<SysMenu> secondMenuList = menuMapper.queryMenuByOne(topMenu);
		List<SysMenu> rvList = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : secondMenuList) {
			sysMenu.setUserId(user.getUserId());
			sysMenu = recursiveMenuByTopMenu(sysMenu);
			rvList.add(sysMenu);
		}
		return rvList;
	}
	
	private SysMenu recursiveMenuByTopMenu(SysMenu menu) {

		List<SysMenu> childMenus = menuMapper.queryMenuByOne(menu);
		for (SysMenu sysMenu : childMenus) {
			SysMenu m = recursiveMenuByTopMenu(sysMenu);
			menu.getMenuList().add(m);
		}
		return menu;
	}
	
	@Override
	public SysPerson queryPersonById(String sysPersonId) {
		return ppMapper.selectByPrimaryKey(sysPersonId);
	}

	@Override
	public SysDept queryDeptById(String deptId) {
		return deptMapper.selectByPrimaryKey(deptId);
	}

	@Override
	public List<SysUser> queryUserWithPerson(SysUser user) {
		return userMapper.selectiveWithPerson(user);
	}

	@Override
	public SysMenu queryMenuById(String pid) {
		return menuMapper.selectByPrimaryKey(pid);
	}

	@Override
	public List queryMenuByOther(SysMenu temMenu) {
		List<SysMenu> secondMenuList = menuMapper.queryMenuByOne(temMenu);
//		System.out.println("---------------end-------------------");
		List<SysMenu> rvList = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : secondMenuList) {
			//System.err.println(sysMenu.getMenuName());
			sysMenu.setUserId(temMenu.getUserId());
			sysMenu = recursiveMenuByTopMenu(sysMenu);
			rvList.add(sysMenu);
		}
		return rvList;
	}

	@Override
	public List<OperLog> queryLog(OperLogPage page) {
		
		return logMapper.queryLog(page);
	}

	@Override
	public Set<String> getRoleNameByUserId(String userId) {
		// TODO Auto-generated method stub
		return userMapper.getRoleNameByUserId(userId);
	}
	
}
