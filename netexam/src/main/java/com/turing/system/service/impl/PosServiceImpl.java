package com.turing.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turing.system.entity.SysDept;
import com.turing.system.entity.SysPost;
import com.turing.system.mapper.SysDeptMapper;
import com.turing.system.mapper.SysPostMapper;
import com.turing.system.page.PosPage;
import com.turing.system.service.IPosService;

@Service
public class PosServiceImpl implements IPosService{

	@Autowired
	private SysPostMapper posMapper;
	@Autowired
	private SysDeptMapper deptMapper;
	
	@Override
	public SysPost queryById(String id) {
		return posMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysDept> queryDept() {
		return deptMapper.selectAll();
	}

	@Override
	public void save(SysPost pos) {
		posMapper.insertSelective(pos);
	}

	@Override
	public void update(SysPost pos) {
		posMapper.updateByPrimaryKeySelective(pos);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			posMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<SysPost> queryPage(PosPage page) {
		List<SysPost> list = posMapper.query(page);
		List<SysPost> allList = posMapper.queryAll(page);
		page.setPageState(page, allList);
		return list;
	}
}
