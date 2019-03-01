package com.turing.framework.aop;

import java.io.IOException;
import java.util.Set;

import javax.security.auth.message.callback.SecretKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonPointer;

@Aspect  //把此类注册为切面类
public class PermissionAop {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	/**
	 * 横切所有系统设置控制层中的方法
	 */
	@Pointcut("execution(* com.turing.system.controller.*.*(..)")
	public void accessSystem() {}
	/**
	 * 横切出题出试卷的控制层
	 */
	@Pointcut("execution(* com.turing.manage.controller.*.*(..)")
	public void accessManage() {}
	/**
	 * admin用户角色控制
	 * @param p
	 */
	@Before("accessSystem()	")
	public void systemPermission(JoinPoint p) {
		//改造登录系统,把角色名装到session里
		String methodName=p.getSignature().getName();
		if ("login".equals(methodName) || 
			"layout".equals(methodName)||
			"topasswordjsp".equals(methodName)||
			"saveNewPass".equals(methodName)||
			 "main".equals(methodName)||
			 "menuLeft".equals(methodName)) {
			
	}else {			
		Set<String> roles= (Set<String>) request.getSession().getAttribute("roles");
		if ( roles!=null && roles.size()>0 ) {
			if ( !roles.contains("admin") ) {
				try {
					request.getRequestDispatcher("/error/noPermission.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					
					e.printStackTrace();
				}
			}
		 }
	  }
	}
	/**
	 * exam 角色控制
	 * @param p
	 */
	@Before("accessManage()")
	public void ManagePermission(JoinPoint p) {
		Set<String> roles= (Set<String>) request.getSession().getAttribute("roles");
		if ( roles!=null && roles.size()>0 ) {
			if ( !roles.contains("exam") ) {
				try {
					request.getRequestDispatcher("/error/noPermission.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					
					e.printStackTrace();
				}
			}
		 }
	 }
	
	
}
