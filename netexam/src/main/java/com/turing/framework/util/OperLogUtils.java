package com.turing.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turing.system.entity.OperLog;
import com.turing.system.entity.SysPerson;
import com.turing.system.mapper.OperLogMapper;

/**
 *@description 用数据库记录登录人的操作步骤
 *@author 赵刚
 *@date 2017年9月21日 下午8:57:42
 */
@Component
public class OperLogUtils {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private OperLogMapper logMapper;
	/**
	 * 
	 * @param operModel 操作模块 会员，角色，商品等
	 * @param operType  操作类型 1.添加， 2.修改， 3.删除
	 * @param operDesc  操作描述 修改计价方式默认选项，添加了一个会员，等等
	 */
	public void write(String operModel,Integer operType,String operDesc){
		OperLog log = new OperLog();
//		//添加操作人员id
		SysPerson person = (SysPerson)request.getSession().getAttribute("person");
//		SysPerson person = (SysPerson)session.getAttribute("person");
		log.setOperPersonid(person.getPpId());
//		//添加操作人员名称
		log.setOperPersonname(person.getPpName());
//		//操作时间
		log.setOperTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//		//操作类型 1.增加, 2.删除, 3.修改
		log.setOperType(String.valueOf(operType));
//		//操作描述
		log.setOperDesc(operDesc);
//		//操作模块 1.SPL,2.系统参数,3.DCP, 4.片库 5.影厅信息,
		log.setOperModel(operModel);
//		//存到数据库
		log.setOperId(UUID.randomUUID().toString());
		logMapper.insertSelective(log);
	}
}
