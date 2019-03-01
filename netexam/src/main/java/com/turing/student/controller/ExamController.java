package com.turing.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.manage.entity.Paper;
import com.turing.manage.entity.Topic;
import com.turing.student.page.ExamPage;
import com.turing.student.service.ExamService;
import com.turing.student.service.impl.ExamServiceImpl;
@Controller
@RequestMapping("exam")
@SessionAttributes("examPage")
public class ExamController {
   
	/**
	 * 模型层
	 */
	@Autowired
	private ExamService service ;
	/**
	 * 初始化model
	 */
	@ModelAttribute("examPage")
	public ExamPage initModel() {
		
		return new ExamPage();
	}
	
	/**
	 * 查询可以考试的试卷
	 */
	@RequestMapping("query")
	public String querypaper(ModelMap mp , @ModelAttribute ExamPage page) {
		//开启分页功能
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Paper> list = service.queryStudentPaper();
		mp.put("pageInfo", new PageInfo<Paper>(list));
		
		return "manage/exam/paper_query";
	}
	/**
	 * 开始考试
	 */
	@RequestMapping("startexam")
	public String startexam(String id,ModelMap mp) {
		List<Topic> list = service.queryToppic(id);
		System.out.println("--?"+list);
		mp.put("list", list);
		return "/manage/exam/exam_page";
	}
}
