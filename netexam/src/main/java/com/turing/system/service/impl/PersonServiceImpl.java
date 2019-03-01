package com.turing.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.framework.service.BasicServiceImpl;
import com.turing.system.entity.SysDept;
import com.turing.system.entity.SysPerson;
import com.turing.system.entity.SysPost;
import com.turing.system.entity.SysUser;
import com.turing.system.mapper.SysDeptMapper;
import com.turing.system.mapper.SysPersonMapper;
import com.turing.system.mapper.SysPostMapper;
import com.turing.system.mapper.SysUserMapper;
import com.turing.system.page.PersonPage;
import com.turing.system.service.IPersonService;

@Service
public class PersonServiceImpl extends BasicServiceImpl implements IPersonService{

	@Autowired
	private SysPersonMapper personMapper;
	
	@Autowired
	private SysDeptMapper deptMapper;
	
	@Autowired
	private SysPostMapper spMapper;
	
	@Autowired
	private SysUserMapper userMapper;
	
	@Override
	public List<SysPerson> queryPage(PersonPage page) {
		List<SysPerson> list = personMapper.query(page);
		List<SysPerson> allList = personMapper.queryAll(page);
		page.setPageState(page, allList);
		return list;
	}

	@Override
	public SysPerson queryById(String id) {
		return personMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delete(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			personMapper.deleteByPrimaryKey(ids[i]);
		}
	}

	@Override
	public void save(SysPerson person) {
		personMapper.insertSelective(person);
	}

	@Override
	public void update(SysPerson person) {
		personMapper.updateByPrimaryKeySelective(person);
	}

	@Override
	public List<SysDept> queryDept() {
		return deptMapper.selectAll();
	}

	@Override
	public List<SysPost> querySpByDeptId(String deptId) {
		SysPost sp = new SysPost();
		sp.setDeptId(deptId);
		return spMapper.selective(sp);
	}

	@Override
	public List<SysUser> queryUnconnectedUser() {
		List<SysPerson> ppList = personMapper.queryAll(new PersonPage());
		List list = new ArrayList();
		for (SysPerson pp : ppList) {
			if(pp.getUserId()!=null){
				list.add(pp.getUserId());
			}
		}
		
		if(list.size()==0){
			list = null;
		}
		
		List<SysUser> userList = userMapper.selectNotInIds(list);
		return userList;
	}

	@Override
	public SysPerson queryAllInfoById(String ppId) {
		return personMapper.queryAllInfoById(ppId);
	}

	@Override
	public void updateUserIdToNull(String ppId) {
		personMapper.updateUserIdToNull(ppId);
	}

	@Override
	public void saveUser(SysUser user) {
		userMapper.insertSelective(user);
	}

}
