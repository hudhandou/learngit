package com.turing.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.turing.framework.util.ResultJson;
import com.turing.manage.entity.Classs;
import com.turing.student.entity.Student;
import com.turing.student.service.IStudentService;
import com.turing.system.entity.SysUser;

@Controller
@RequestMapping("student")
public class StudentController {
	/**
	 * 学生模型层
	 * @param model
	 * @return
	 */
	@Autowired
	private IStudentService service;
	@RequestMapping("/zhuce")
	public String zhuce(Model model) {
		//查询默认班级
		Classs c = service.queryClass();
		model.addAttribute("classs",c);
		return "manage/student/zhuce";	
	}
	/**
	 * 提交注册信息
	 * @param stu
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public ResultJson save(Student stu ,SysUser user) {
		System.out.println("stu:"+stu);
		ResultJson r = new ResultJson();
		int tiao = 0;
		tiao = service.save(stu,user);
		System.out.println(tiao);
		if (tiao>0) {
			r.setSuccess(true);
			r.setMsg("注册成功");
		} else {
            r.setSuccess(false);
            r.setMsg("注册失败");
		}
		return r;
	} 
	
}
