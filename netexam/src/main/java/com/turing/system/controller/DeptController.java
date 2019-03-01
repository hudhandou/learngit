package com.turing.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.turing.framework.util.ResultJson;
import com.turing.framework.util.TreeNode;
import com.turing.system.entity.SysDept;
import com.turing.system.service.IDeptService;

@Controller
@RequestMapping("dept")
@Scope("prototype")
public class DeptController {
	
	@Autowired
	private IDeptService deptService;
	
	@RequestMapping("query")
	public String query(ModelMap mp){
		List<SysDept> topList =  deptService.queryAllWithList();
		mp.put("topList", topList);
		return "system/dept/query_dept";
	}
	
	@RequestMapping("addpage")
	@ResponseBody
	public SysDept addpage(String id){
		SysDept parDept = deptService.queryById(id);
		return parDept;
	}
	
	@RequestMapping("editpage")
	@ResponseBody
	public List editpage(String id){
		List rvList = new ArrayList();
		SysDept dept = deptService.queryById(id);
		SysDept parDept = deptService.queryById(dept.getDeptPar());
		rvList.add(dept);
		rvList.add(parDept);
		return rvList;
	}
	
	@RequestMapping("save")
	public String save(SysDept dept){
		if("".equals(dept.getDeptId())){
			dept.setDeptId(UUID.randomUUID().toString());
			dept.setDeptState("A");
			deptService.save(dept);
		}else{
			deptService.update(dept);
		}
		return "redirect:query.action";
	}
	
	@RequestMapping("delete")
	public String delete(String id){
		deptService.deleteById(id);
		return "redirect:query.action";
	}
	/**
	 * 查询所有部门
	 * @return
	 */
	@RequestMapping("querySysDept")
	@ResponseBody
	public List<TreeNode> querySysDept() {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("系统菜单");
		node.getAttributes().put("level", "1");
		node.setState("open");
		node.setChildren( deptService.querySysDept("0") );
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(node);
		return list;
	}
	/**
	 * easyui 转到添加页面
	 * @return
	 */
	@RequestMapping("toaddpage")
	public String toaddpage(String deptPar,ModelMap mm) {
		//查询上级菜单名称
		SysDept parentDept = deptService.queryById(deptPar);
		if (parentDept != null) {
			mm.put("parentName", parentDept.getDeptName());
		} else {
			mm.put("parentName", "系统菜单");
		}
		return "system/dept/dept_add";
	}
	/**
	 * 添加保存
	 * @return
	 */
	@RequestMapping("deptsave")
	@ResponseBody
	public ResultJson deptsave(SysDept dept) {
		ResultJson r = new ResultJson();
		dept.setDeptState("A");
		dept.setDeptId(UUID.randomUUID().toString());
		int tiao = deptService.save(dept);
		if (tiao > 0) {
			r.setSuccess(true);
			r.setMsg("保存成功");
			r.setObj(dept);
		}else {
			r.setSuccess(false);
			r.setMsg("保存失败");
		}
		return r;
	}
	/**
	 * 转到修改页面
	 * @param deptPar
	 * @param mm
	 * @return
	 */
	@RequestMapping("toeditpage")
	public String toeditpage(ModelMap mm,String id) {
		SysDept dept = deptService.queryById(id);
		mm.put("dept", dept);
		//查询上级菜单名称
		SysDept parentDept = deptService.queryById(dept.getDeptPar());
		if (parentDept != null) {
			mm.put("parentName", parentDept.getDeptName());
		} else {
			mm.put("parentName", "系统菜单");
		}
		return "system/dept/dept_edit";
	}	
	/**
	 * 修改保存
	 * @param dept
	 * @return
	 */
	@RequestMapping("deptedit")
	@ResponseBody
	public ResultJson deptedit(SysDept dept) {
		ResultJson r = new ResultJson();
		int tiao = deptService.update(dept);
		if (tiao > 0) {
			r.setSuccess(true);
			r.setMsg("修改成功");
			r.setObj(dept);
		}else {
			r.setSuccess(false);
			r.setMsg("修改失败");
		}
		return r;
	}
	/**
	 * 删除一个部门
	 * @param dept
	 * @return
	 */
	@RequestMapping("deptdelete")
	@ResponseBody
	public ResultJson deptdelete(String id) {
		ResultJson r = new ResultJson();
		//先验证是否能删除
		int ge = deptService.yanzhengDeleteDept(id);
		if (ge > 0) {
			r.setSuccess(false);
			r.setMsg("删除失败,部门可能被工程管理,仓库,联系人,人员,职位,流程所有使用!");
			return r;
		}
		int tiao = deptService.deleteById(id);
		if (tiao > 0) {
			r.setSuccess(true);
			r.setMsg("删除成功");
		}else {
			r.setSuccess(false);
			r.setMsg("删除失败");
		}
		return r;
	}
}
