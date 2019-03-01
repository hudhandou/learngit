package com.turing.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.framework.select.SelectOptions;
import com.turing.system.entity.SysPerson;
import com.turing.system.entity.SysUser;
import com.turing.system.entity.SysUserRole;
import com.turing.system.mapper.SysPersonMapper;
import com.turing.system.mapper.SysRoleMapper;
import com.turing.system.mapper.SysUserMapper;
import com.turing.system.mapper.SysUserRoleMapper;
import com.turing.system.page.UserPage;
import com.turing.system.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Autowired
	private SysPersonMapper personMapper;
	
	@Autowired
	private SysRoleMapper roleMapper;
	
	@Autowired
	private SysUserRoleMapper urMapper;
	
	@Override
	public void update(SysUser user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<SysUser> queryPage(UserPage page) {
		List<SysUser>  list=userMapper.query(page);
		List<SysUser>  allList=userMapper.queryAll(page);
		page.setPageState(page, allList);
		return list;
	}

	@Override
	public List<SysUser> queryUserWithPerson(List<SysUser> list) {
		for (int i=0;i<list.size();i++) {
			
			SysPerson pp = new SysPerson();
			pp.setUserId(list.get(i).getUserId());
			List<SysPerson> ppList = personMapper.selective(pp);
			if(ppList.size()>1){
				list.get(i).setSysPersonName("关联人员不唯一!");
			}else if (ppList.size()==1){
				list.get(i).setSysPersonName(ppList.get(0).getPpName());
			}
		}
		return list;
	}

	@Override
	public SysUser queryById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(String[] ids) {
		for (String string : ids) {
			userMapper.deleteByPrimaryKey(string);
		}
	}

	@Override
	public void save(SysUser user) {
		userMapper.insertSelective(user);
	}

	@Override
	public List<SelectOptions> queryRoleByUser(String id) {
		List<SelectOptions> allRoleList = roleMapper.queryAllRoleWithOptions();
		List<SelectOptions> roleUserList = roleMapper.queryRoleByUserWithOptions(id);
		List<SelectOptions> list = new ArrayList<SelectOptions>();
		for (SelectOptions allRole : allRoleList) {
			for (SelectOptions userRole : roleUserList) {
				if(allRole.getKey().equals(userRole.getKey())){
					allRole.setSelected("selected");
				}
			}
			list.add(allRole);
		}
		return list;
	}

	@Override
	public void changeRole(String userId,String roleIds) {
		urMapper.deleteByUserId(userId);
		if(!"".equals(roleIds)){
			String[] ids = roleIds.split("@");
			for (String roleId : ids) {
				SysUserRole ur = new SysUserRole();
				ur.setUrId(UUID.randomUUID().toString());
				ur.setRoleId(roleId);
				ur.setUserId(userId);
				urMapper.insertSelective(ur);
			}
		}
	}
}
