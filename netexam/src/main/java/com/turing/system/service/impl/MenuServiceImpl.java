package com.turing.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.framework.util.TreeNode;
import com.turing.system.entity.SysMenu;
import com.turing.system.mapper.SysMenuMapper;
import com.turing.system.mapper.SysRoleMapper;
import com.turing.system.mapper.SysRoleMenuMapper;
import com.turing.system.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{
	//菜单
	@Autowired
	private SysMenuMapper menuMapper;
	//角色
	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	@Override
	public List<SysMenu> queryAllWithList() {
		List<SysMenu> topMenuList = menuMapper.queryAllMenuByTop();
		List<SysMenu> rvList = new ArrayList<SysMenu>();
		for (SysMenu sysMenu : topMenuList) {
			SysMenu rv = this.recursiveMenuByTopMenu(sysMenu);
			rvList.add(rv);
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
	public SysMenu queryById(String id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(SysMenu menu) {
		menuMapper.insertSelective(menu);
	}

	@Override
	public void update(SysMenu menu) {
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void deleteById(String id) {
		menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TreeNode> querySysMenu(String pid) {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("系统");
		node.setState("open");
		node.getAttributes().put("url", "");
		node.getAttributes().put("level", "0");
		node.setChildren( panduanTree( "0" , node.getChildren() ) );
//		List<TreeNode> treelist = panduanTree( "0" , node.getChildren() );
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		treeList.add(node);
		return treeList;
	}
	/**
	 * 判断当前菜单有无子菜单,如果有则把菜单转换成treeNode格式
	 * 递归调用
	 */
	private List<TreeNode> panduanTree(String pid, List<TreeNode> treeList){
		//根据pid查询子菜单
		List<SysMenu> list = menuMapper.queryMenuByPid(pid);
		if(list!=null && list.size()>0 ){
			for (int i = 0; i < list.size(); i++) {
				SysMenu m = (SysMenu) list.get(i);
				TreeNode node = new TreeNode();
				node.setId(m.getMenuId());
				node.setText(m.getMenuName());
				if ("2".equals(m.getMenuIslink())) {
					List<SysMenu> clist = menuMapper.queryMenuByPid(m.getMenuId());
					if (clist.isEmpty()) {
						node.setState("open");
					}else{
						node.setState("closed");
					}
				}else{
					node.setState("open");
				}
				node.getAttributes().put("url", m.getMenuUrl() );
				node.getAttributes().put("level", m.getMenuIslink());
				treeList.add(node);	//添加子节点
				panduanTree( m.getMenuId() , node.getChildren() );
			}
		}
		return treeList;
	}

	@Override
	public int delete(String id) {
		//先删除角色菜单表
		int ge = 0;
		ge = roleMenuMapper.deleteRoleMenuByMenuId(id);
		//再删除菜单表
		ge = menuMapper.deleteByPrimaryKey(id);
		return ge;
	}

	@Override
	public SysMenu addsave(SysMenu menu) {
		menu.setMenuId(UUID.randomUUID().toString());
		menu.setMenuState("A");
		menu.setMenuIco("/images/menu/plugin.png");
		menuMapper.insertSelective(menu);
		return menuMapper.selectByPrimaryKey(menu.getMenuId());
	}

	@Override
	public int editsave(SysMenu menu) {
		return menuMapper.updateByPrimaryKeySelective(menu);
	}

}
