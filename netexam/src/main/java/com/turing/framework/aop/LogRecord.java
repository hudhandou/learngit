package com.turing.framework.aop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.turing.system.entity.SysUser;

public class LogRecord {
	/**
	 * 日志
	 */
	private  Logger log=LoggerFactory.getLogger(this.getClass());	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	public Object logwrite(ProceedingJoinPoint p) {		
		Object result = null;
		try {
			//前置部分添加日志记录 [hu] - 60.37.2.189 - com.turing.xx.xxController.addpage
			HttpSession session = request.getSession();
			SysUser user =(SysUser) session.getAttribute("user");
			if (user!=null) {
				String username = user.getUserName();
				String ip = request.getRemoteAddr();
				String className = p.getTarget().getClass().getName();
				String methodName = p.getSignature().getName();
				StringBuilder sb=new StringBuilder();
				sb.append("[").append(username).append("] - ").append(ip)
				.append(" - ").append(className).append(".").append(methodName);
				log.info(sb.toString());
			}
			result=p.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			//全局异常处理
			try {
				request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			} catch (ServletException | IOException e1) {
				
				e1.printStackTrace();
			}
		}
		return result;		
	}
}
