package com.turing.system.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.turing.framework.util.ResultJson;
import com.turing.framework.util.TreeNode;
import com.turing.system.entity.SysMenu;
import com.turing.system.service.IMenuService;

@Controller
@RequestMapping(value={"/tree","/menu"})
@Scope(value="prototype")
public class MenuController {
	
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("main")
	public String main(){
		return "frame/main";
	}
	
	@RequestMapping("query")
	public String query(ModelMap mp){
		List<SysMenu> topList =  menuService.queryAllWithList();
		mp.put("topList", topList);
		return "/system/menu/query_menu";
	}
	
/*	@RequestMapping("addpage")
	@ResponseBody
	public SysMenu addpage(String id){
		SysMenu parMenu = menuService.queryById(id);
		return parMenu;
	}
	
	@RequestMapping("editpage")
	@ResponseBody
	public List editpage(String id){
		List rvList = new ArrayList();
		SysMenu menu = menuService.queryById(id);
		SysMenu parMenu = menuService.queryById(menu.getMenuPare());
		rvList.add(menu);
		rvList.add(parMenu);
		return rvList;
	}*/
	/**
	 * 转到添加页面
	 */
	@RequestMapping("/addpage")
	public String addpage() {
		return "/system/menu/menu_add";
	}
	/**
	 * 转到修改页面
	 */
	@RequestMapping("/editpage")
	public String editpage(String id,ModelMap modelMap) {
		//接
		//调
		SysMenu menu = menuService.queryById(id);
		//存
		modelMap.put("menu", menu);
		//转
		return "/system/menu/menu_edit";
	}
	
/*	@RequestMapping("save")
	public String save(SysMenu menu){
		if("".equals(menu.getMenuId())){
			menu.setMenuId(UUID.randomUUID().toString());
			menu.setMenuState("A");
			menuService.save(menu);
		}else{
			menuService.update(menu);
		}
		return "redirect:query.action";
	}
	
	@RequestMapping("delete")
	public String delete(String id){
		menuService.deleteById(id);
		return "redirect:query.action";
	}*/
	/**
	 * 添加保存
	 */
	@RequestMapping("/addsave")
	@ResponseBody
	public ResultJson addsave(SysMenu menu) {
		ResultJson r = new ResultJson();
		SysMenu o = menuService.addsave(menu);
		if (o!=null) {
			r.setSuccess(true);
			r.setMsg("添加成功");
			r.setObj(o);
		} else {
			r.setSuccess(false);
			r.setMsg("添加失败");
		}
		return r;
	}
	/**
	 * 修改保存
	 */
	@RequestMapping("/editsave")
	@ResponseBody
	public ResultJson editsave(SysMenu menu) {
		ResultJson r = new ResultJson();
		int tiao = menuService.editsave(menu);
		if ( tiao > 0 ) {
			r.setSuccess(true);
			r.setMsg("修改成功");
			r.setObj(menu);
		} else {
			r.setSuccess(false);
			r.setMsg("修改失败");
		}
		return r;
	}
	/**
	 * 删除一个菜单节点
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResultJson delete(String id) {
		ResultJson r = new ResultJson();
		//调
		int tiao = menuService.delete(id);
		if ( tiao > 0) {
			r.setSuccess(true);
			r.setMsg("删除成功");
		} else {
			r.setSuccess(false);
			r.setMsg("删除失败");
		}
		return r;
	}
	/**
	 * 查询所有菜单
	 */
	@RequestMapping("/querySysMenu")
	@ResponseBody
	public List<TreeNode> querySysMenu(HttpSession session){
		return menuService.querySysMenu("0");
	}
}
