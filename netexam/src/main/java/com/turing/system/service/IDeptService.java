package com.turing.system.service;

import java.util.List;

import com.turing.framework.util.TreeNode;
import com.turing.system.entity.SysDept;

public interface IDeptService {
	
	List<SysDept> queryAllWithList();

	SysDept queryById(String id);

	int save(SysDept dept);

	int update(SysDept dept);

	int deleteById(String id);
	/**
	 * 查询部门
	 */
	List<TreeNode> querySysDept(String string);
	/**
	 * 验证部门是否能被删除
	 * @param id
	 * @return
	 */
	int yanzhengDeleteDept(String id);

}
