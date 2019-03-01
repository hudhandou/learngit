package com.turing.system.controller;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.turing.system.entity.SysDept;
import com.turing.system.entity.SysPost;
import com.turing.system.page.PosPage;
import com.turing.system.service.IPosService;

@Controller
@RequestMapping("pos")
@Scope("prototype")
@SessionAttributes("pospage")
public class PosController {
	@Autowired
	private IPosService posService;
	
	@ModelAttribute("pospage")
	public PosPage initPage(){
		return new PosPage();
	}
	
	@RequestMapping("query")
	public String query(@ModelAttribute("pospage")PosPage page,ModelMap mp){
		List<SysPost> list = posService.queryPage(page);
		List<SysDept> deptList = posService.queryDept();
		mp.put("list", list);
		mp.put("page", page);
		mp.put("deptList", deptList);
		return "/system/pos/query_pos";
	}
	
	@RequestMapping("page")
	public String page(ModelMap mp,String id){
		if(null!=id){
			SysPost pos = posService.queryById(id);
			mp.put("pos", pos);
		}
		List<SysDept> deptList = posService.queryDept();
		mp.put("deptList", deptList);
		return "/system/pos/page_pos";
	}
	
	@RequestMapping("delete")
	public String delete(String[] ids){
		posService.delete(ids);
		return "redirect:query.action";
	}
	
	@RequestMapping("save")
	public String save(SysPost pos) throws ParseException{	
		
		if("".equals(pos.getSpId())){
			pos.setSpId(UUID.randomUUID().toString());
			
			posService.save(pos);
		}else{
			posService.update(pos);
		}
		return "redirect:query.action";
	}
}
