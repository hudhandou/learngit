package com.turing.system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.turing.framework.select.SelectOptions;
import com.turing.system.entity.SysRole;
import com.turing.system.page.RolePage;
import com.turing.system.service.IRoleService;

@Controller
@RequestMapping("role")
@Scope("prototype")
@SessionAttributes("rolepage")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@ModelAttribute("rolepage")
	public RolePage initPage(){
		return new RolePage();
	}
	
	@RequestMapping("query")
	public String query(@ModelAttribute("rolepage")RolePage page,ModelMap mp){
		List<SysRole> list =roleService.queryPage(page);
		mp.put("list", list);
		mp.put("page", page);
		return "system/role/query_role";
	}
	
	@RequestMapping("page")
	public String page(ModelMap mp,String id){
		if(null!=id){
			SysRole role = roleService.queryById(id);
			mp.put("role", role);
		}
		return "system/role/page_role";
	}
	
	@RequestMapping("delete")
	public String delete(String[] ids){
		roleService.delete(ids);
		return "redirect:query.action";
	}
	
	@RequestMapping("save")
	public String save(SysRole role){
		if("".equals(role.getRoleId())){
			role.setRoleId(UUID.randomUUID().toString());
			role.setRoleState("A");
			roleService.save(role);
		}else{
			roleService.update(role);
		}
		return "redirect:query.action";
	}
	
	@RequestMapping("roleMenuPage")
	public String roleMenuPage(ModelMap mp,String id){
		SysRole role = roleService.queryById(id);
		//查询菜单，用户菜单为selected
		List<SelectOptions> list = roleService.queryRoleMenu(id);
		mp.put("role", role);
		mp.put("list", list);
		return "system/role/role_menu";
	}
	
	@RequestMapping("roleMenu")
	public String roleMenu(String roleId,String menuIds){
		roleService.roleMenu(roleId,menuIds);
		return "redirect:query.action";
	}
}
