package com.turing.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.turing.manage.entity.Classs;
import com.turing.manage.page.ClassPage;
import com.turing.manage.service.IClassService;
/**
 * 班级的基本操作
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("classs")
@SessionAttributes("classspage")
public class ClassController {

	/**
	 * 模型层
	 */
	@Autowired
	private IClassService service;
	
	/**
	 * 匹配是否有同名对象,没有就将返回值放在model里
	 * @return
	 */
	@ModelAttribute("classspage")
	public ClassPage a() {
		return new ClassPage();		
	};
	/**
	 * 查询班级信息
	 * @param model	模型数据
	 * @param pageNum	当前页
	 * @param pageSize  每页多少条
	 * @return 页面
	 */
	@RequestMapping("query")
	public String query(
		@ModelAttribute("classspage") ClassPage page,
		ModelMap model){
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<Classs> list = service.queryAll(page);
		model.put("pageInfo", new PageInfo<Classs>(list));
		//model.put("list", list);

		return "manage/class/class_query";		
	}
	/**
	 * 转到添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addpage")
	private String addPage(Model model) {
	
		return "manage/class/class_add";	
	}
	/**
	 * 转到修改页面
	 * @param mp
	 * @param id
	 * @return
	 */
	@RequestMapping("editpage")
	public String editpage(ModelMap mp,String id){
		Classs c= service.queryById(id);
		mp.put("c", c);
		return "manage/class/class_edit";
	}
	/**
	 * 添加保存
	 * @param 
	 * @return
	 */
	@RequestMapping(value="save")
	@ResponseBody
	public ResultJson save(Classs c){
		
	int tiao = service.save(c);
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
	 * @param
	 * @return
	 */
	@RequestMapping(value="edit")
	@ResponseBody
	public ResultJson edit(Classs c){
		
	int tiao = service.edit(c);
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
	/**
	 * 验证名称是否重复
	 * @param className
	 * @return
	 */
	@RequestMapping(value="checkName")
	@ResponseBody
	public ResultJson checkName(String className) {
		
		int count=service.checkName(className);
		ResultJson r= new ResultJson();
		if (count>0) { //重复
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r;
	}
	/**
	 * 设置默认
	 * @param mm
	 * @param id
	 * @return
	 */
	@RequestMapping("moren")
	@ResponseBody
	public ResultJson moren(Classs c,String id) {
		ResultJson r  = new ResultJson();
	    int tiao = service.moren(c,id);
		if (tiao > 0) {
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r  ;
	}
	
}
