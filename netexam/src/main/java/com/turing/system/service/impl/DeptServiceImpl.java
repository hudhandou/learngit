package com.turing.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.framework.util.TreeNode;
import com.turing.system.entity.SysDept;
import com.turing.system.entity.SysMenu;
import com.turing.system.mapper.SysDeptMapper;
import com.turing.system.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService{

	@Autowired
	private SysDeptMapper deptMapper;
	
	@Override
	public List<SysDept> queryAllWithList() {
		List<SysDept> topDeptList = deptMapper.queryAllDeptByTop();
//		System.out.println("========"+topDeptList.size());
		List<SysDept> rvList = new ArrayList<SysDept>();
		for (SysDept sysDept : topDeptList) {
			SysDept rv = this.recursiveDeptByTopDept(sysDept);
			rvList.add(rv);
		}
		return rvList;
	}
	
	private SysDept recursiveDeptByTopDept(SysDept dept) {
		List<SysDept> childDepts = deptMapper.queryDeptByOne(dept);
//		System.out.println(childDepts.size());
		for (SysDept sysDept : childDepts) {
			SysDept m = recursiveDeptByTopDept(sysDept);
			dept.getDeptList().add(m);
		}
		return dept;
	}

	@Override
	public SysDept queryById(String id) {
		return deptMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(SysDept dept) {
		return deptMapper.insertSelective(dept);
	}

	@Override
	public int update(SysDept dept) {
		return deptMapper.updateByPrimaryKeySelective(dept);
	}

	@Override
	public int deleteById(String id) {
		return deptMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TreeNode> querySysDept(String string) {
		List<SysDept> list = deptMapper.querySysDept(string);
		List<TreeNode> treeList = new ArrayList<TreeNode>();
		for (int i = 0; i <list.size(); i++) {
			SysDept d = list.get(i);
			TreeNode node = new TreeNode();
			node.setId(d.getDeptId());
			node.setText(d.getDeptName());
			node.setState("open");
			node.getAttributes().put("level", "2");
			List<SysDept> childList = deptMapper.querySysDept(d.getDeptId());
			if (!childList.isEmpty()) {
				for (int j = 0; j <childList.size(); j++) {
					SysDept d2 = childList.get(j);
					TreeNode node2 = new TreeNode();
					node2.setId(d2.getDeptId());
					node2.setText(d2.getDeptName());
					node2.setState("open");
					node2.getAttributes().put("level", "3");
					node.getChildren().add(node2);
				}
			}
			treeList.add(node);
		}
		return treeList;
	}

	@Override
	public int yanzhengDeleteDept(String id) {
		
		return deptMapper.yanzhengDeleteDept(id);
	}

}
