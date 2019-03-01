package com.turing.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.framework.select.SelectOptions;
import com.turing.system.entity.SysMenu;
import com.turing.system.entity.SysRole;
import com.turing.system.entity.SysRoleMenu;
import com.turing.system.mapper.SysMenuMapper;
import com.turing.system.mapper.SysRoleMapper;
import com.turing.system.mapper.SysRoleMenuMapper;
import com.turing.system.page.RolePage;
import com.turing.system.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysMenuMapper menuMapper;
	@Autowired
	private SysRoleMenuMapper srmMapper;
	
	@Override
	public List<SysRole> queryPage(RolePage page) {
		List<SysRole> list = roleMapper.query(page);
		List<SysRole> allList = roleMapper.queryAll(page);
		page.setPageState(page, allList);
		return list;
	}

	@Override
	public SysRole queryById(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			roleMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void save(SysRole role) {
		roleMapper.insertSelective(role);
	}

	@Override
	public void update(SysRole role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public List<SelectOptions> queryRoleMenu(String id) {
		List<SelectOptions> topList = menuMapper.queryAllMenuByTopWithOptions();
		List<SysRoleMenu> srmList = srmMapper.queryMenuByRoleId(id);
		List<SelectOptions> rvList = new ArrayList<SelectOptions>();
		for (SelectOptions sp : topList) {
			SelectOptions rv = this.recursiveMenuByTopMenuWithOptions(srmList,sp);
			rvList.add(rv);
		}
		return rvList;
	}
	
	private SelectOptions recursiveMenuByTopMenuWithOptions(List<SysRoleMenu> srmList,SelectOptions sp) {
		List<SelectOptions> childMenus = menuMapper.queryMenuByOneWithOptions(sp);
		for (SelectOptions sysMenu : childMenus) {
			for (int i = 0; i < srmList.size(); i++) {
				if(srmList.get(i).getMenuId().equals(sysMenu.getKey())){
					sp.setSelected("checked");
					sysMenu.setSelected("checked");
					break;
				}
			}
			SelectOptions m = recursiveMenuByTopMenuWithOptions(srmList,sysMenu);
			sp.getSelectList().add(m);
		}
		return sp;
	}

	@Override
	public void roleMenu(String roleId, String menuIds) {
		srmMapper.deleteByRoleId(roleId);
		if(!"".equals(menuIds)){
			String[] ids = menuIds.split("@");
			for (int i = 0; i < ids.length; i++) {
				SysRoleMenu srm = new SysRoleMenu();
				srm.setRmid(UUID.randomUUID().toString());
				srm.setMenuId(ids[i]);
				srm.setRoleId(roleId);
				srmMapper.insertSelective(srm);
			}
		}
	}
}
