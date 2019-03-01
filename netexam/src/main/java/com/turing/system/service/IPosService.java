package com.turing.system.service;

import java.util.List;

import com.turing.system.entity.SysDept;
import com.turing.system.entity.SysPost;
import com.turing.system.page.PosPage;

public interface IPosService {

	SysPost queryById(String id);

	List<SysDept> queryDept();

	void save(SysPost pos);

	void update(SysPost pos);

	void delete(String[] ids);

	List<SysPost> queryPage(PosPage page);

}
