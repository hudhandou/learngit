package com.turing.system.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.framework.util.MD5Util;
import com.turing.system.entity.OperLog;
import com.turing.system.entity.SysDept;
import com.turing.system.entity.SysMenu;
import com.turing.system.entity.SysPerson;
import com.turing.system.entity.SysUser;
import com.turing.system.page.OperLogPage;
import com.turing.system.service.ILoginService;

@Controller
@RequestMapping("/login")
@Scope(value="prototype")
@SessionAttributes(value="pageNum",types=OperLogPage.class)
public class LoginController {
	
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("login")
	public String login(ModelMap mp,SysUser user,HttpSession session,HttpServletRequest request){
		String loginState = "";
		String pid;
		String message = "";
		
		// 判断是否登录成功,noUser表示没查到用户，noPerson表示用户未与人员关联，ok表示登录成功
		user.setUserPass(MD5Util.md5(user.getUserPass()));
		List<SysUser> dbUserList = loginService.queryUserWithPerson(user);
		if(dbUserList.size()>1){
			message="登录信息重复，请与管理员联系!";
			loginState="reLogin";
			mp.put("message", message);
			mp.put("loginState", loginState);
			return "bridge";
		}else if(dbUserList.size()==0){
			message="登录名或密码错误!";
			loginState="reLogin";
			mp.put("message", message);
			mp.put("loginState", loginState);
			return "bridge";
		}else{		
				loginState="ok";
		}
		// 成功取得用户的权限
		List<SysMenu> topList=loginService.queryMenuByTop(dbUserList.get(0));
		if(topList!=null  && topList.size()>0){
			pid=topList.get(0).getMenuId();
			List<SysMenu> oneList=loginService.queryMenuByOne(dbUserList.get(0));
			//将用户的信息存入到session中
			session.setAttribute("user", dbUserList.get(0));
			session.setAttribute("oneList", oneList);
			session.setAttribute("topList", topList);
			session.setAttribute("pid", pid);
			mp.put("message", message);
			mp.put("loginState", loginState);
			//查询登录人的角色,保存到session里
			Set<String> roles=loginService.getRoleNameByUserId(dbUserList.get(0).getUserId());
			session.setAttribute("roles", roles);
			System.out.println("-------------------");
			System.out.println(roles);
			return "bridge";
		}else{
			message="没有权限";
			loginState="reLogin";
			mp.put("message", message);
			mp.put("loginState", loginState);
			return "bridge";
		}
	}
	
	@RequestMapping("menuLeft")
	@ResponseBody
	public List menuLeft(String pid,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		List oneList = (List) session.getAttribute("oneList");
		List menuOtherList = new ArrayList();
		if( "0".equals(user.getUserId())   &&    "临时".equals(user.getNickName()) ){
			if("a".equals(pid)){
			}else{
				pid=(String) session.getAttribute("pid");
			}			
			if(oneList!=null &&  oneList.size()>0) {
				SysMenu temMenu = loginService.queryMenuById(pid);
				menuOtherList=loginService.queryMenuByOther(temMenu);
			}else{
				SysMenu  menu=new SysMenu("sys", "","系统维护", "2","xxx");
				menuOtherList.add(menu);
				SysMenu  menuUser=new SysMenu("user","user!query.action", "用户维护", "3","sys");
				menuOtherList.add(menuUser);
				SysMenu  menuRole=new SysMenu("role","role!query.action", "角色维护", "3","sys");
				menuOtherList.add(menuRole);
				SysMenu  menuMenu=new SysMenu("menu","menu!queryAll.action", "菜单维护", "3","sys");
				menuOtherList.add(menuMenu);
			}
		}else{
			SysMenu temMenu = loginService.queryMenuById(pid);
			temMenu.setUserId(user.getUserId());
			menuOtherList=loginService.queryMenuByOther(temMenu);
		}
		return menuOtherList;
	}
	
	@RequestMapping("layout")
	@ResponseBody()
	public void layout(HttpSession session){
		session.invalidate();
	}
	/**
	 * 查询系统日志
	 * @return
	 */
	@RequestMapping("queryLog")
	public String queryLog(ModelMap model,
			@RequestParam(value="pageNum",defaultValue="1")int pageNum,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize,
			OperLogPage page
			){
		PageHelper.startPage(pageNum, pageSize);
		List<OperLog> list = loginService.queryLog(page);
		model.put("pageInfo", new PageInfo<OperLog>(list));
		model.put("page", page);
		model.put("pageNum", pageNum);
		return "/system/log/log_query";
	}
}
