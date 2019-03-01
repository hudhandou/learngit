package com.turing.system.service;

import java.util.List;

import com.turing.framework.select.SelectOptions;
import com.turing.system.entity.SysRole;
import com.turing.system.entity.SysUser;
import com.turing.system.page.UserPage;

public interface IUserService {

	void update(SysUser user);

	List<SysUser> queryPage(UserPage page);
	/**
	 * 查询用户对应的人员
	 * @param list
	 * @return
	 */
	List<SysUser> queryUserWithPerson(List<SysUser> list);

	SysUser queryById(String id);

	void delete(String[] ids);

	void save(SysUser user);

	List<SelectOptions> queryRoleByUser(String id);

	void changeRole(String userId, String roleIds);

}
