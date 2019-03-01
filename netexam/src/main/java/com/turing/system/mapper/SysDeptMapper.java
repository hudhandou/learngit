package com.turing.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.turing.system.entity.SysDept;

public interface SysDeptMapper {
    int deleteByPrimaryKey(String deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(String deptId);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

	List<SysDept> selectAll();
	/**
	 * 查询一级部门菜单
	 * @return
	 */
	List<SysDept> queryAllDeptByTop();
	/**
	 * 查询子部门
	 * @param dept
	 * @return
	 */
	List<SysDept> queryDeptByOne(SysDept dept);
	/**
	 * 查询所有部门
	 * @param string
	 * @return
	 */
	@Select("select dept_id, dept_name, dept_code, dept_info, dept_phone, dept_sequ, dept_state, dept_par from sys_dept where dept_par=#{0}")
	@ResultMap("BaseResultMap")
	List<SysDept> querySysDept(String string);
	/**
	 * 验证部门是否能被删除
	 * @param id
	 * @return
	 */
	int yanzhengDeleteDept(String id);
}