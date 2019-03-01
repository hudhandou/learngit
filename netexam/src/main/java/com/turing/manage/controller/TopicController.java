package com.turing.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.framework.util.ResultJson;
import com.turing.manage.entity.Subject;
import com.turing.manage.entity.Topic;
import com.turing.manage.page.TopicPage;
import com.turing.manage.service.ITopicService;
/**
 * 题目的基本操作
 * @author Administrator
 *
 */
@Controller
@RequestMapping("topic")
@SessionAttributes("topicpage")
public class TopicController {
	/**
	 * 模型层
	 */
	@Autowired
	private ITopicService service;
	/**
	 * 匹配是否有同名对象,没有就将返回值放在model里
	 * @return
	 */
	@ModelAttribute("topicpage")
	public TopicPage a() {
		return new TopicPage();		
	};
	/**
	 * 查询题目信息
	 * @param model	模型数据
	 * @param pageNum	当前页
	 * @param pageSize  每页多少条
	 * @return 页面
	 */
	@RequestMapping("query")
	public String query(
		@ModelAttribute("topicpage") TopicPage page,
		ModelMap model){
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<Topic> list = service.queryAll(page);
		model.put("pageInfo", new PageInfo<Topic>(list));
		//model.put("list", list);
		
		return "manage/topic/topic_query";		
	}
	/**
	 * 转到添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addpage")
	public String addPage(Model model) {
		List<Subject> subList=service.query();		
		model.addAttribute("subList", subList);
		return "manage/topic/topic_add";	
	}
	/**
	 * 转到修改页面
	 * @param mp
	 * @param id
	 * @return
	 */
	@RequestMapping("editpage")
	public String editpage(ModelMap model,String id){
		Topic t= service.queryById(id);
		model.addAttribute("t", t);
		List<Subject> subList=service.query();		
		model.addAttribute("subList", subList);
		return "manage/topic/topic_edit";
	}
	/**
	 * 添加保存
	 * @param 
	 * @return
	 */
	@RequestMapping(value="save")
	@ResponseBody
	public ResultJson save(Topic t){
		
	int tiao = service.save(t);
	ResultJson r= new ResultJson();
	if (tiao > 0) {
		r.setSuccess(true);
	}else {
		r.setSuccess(false);
	}
	return r;
  }
	/**
	 * 修改保存
	 * @param hygl
	 * @return
	 */
	@RequestMapping(value="edit")
	@ResponseBody
	public ResultJson edit(Topic t){
		
	int tiao = service.edit(t);
	ResultJson r= new ResultJson();
	if (tiao > 0) {
		r.setSuccess(true);
	
	  }else {
		 r.setSuccess(false);
	  }
	return r;
  }
	/**
	 * 删除
	 * @param ids 主键集合
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public ResultJson delete(String ids){
		String names= service.delete(ids);
		ResultJson r= new ResultJson();
		if("".equals(names)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
			r.setMsg("删除失败,<span style=\"color:red;\">"+names+"</span>被使用中,不能被删除!");
		}
		return r;
	}
}
