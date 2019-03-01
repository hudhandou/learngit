package com.turing.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turing.framework.util.ResultJson;

import com.turing.manage.entity.Subject;
import com.turing.manage.page.SubjectBigPage;
import com.turing.manage.service.ISubjectBigService;
/**
 * 学科大类别的基本操作
 * @author Administrator
 *
 */
@Controller
@RequestMapping("big")
@SessionAttributes("subjectBigPage")
public class SubjectBigController {
    /**
     * 模型层
     */
	@Autowired
	private ISubjectBigService service;
	/**
	 * 匹配是否有同名对象,没有就将返回值放在model里
	 * @return
	 */
	@ModelAttribute("subjectBigPage")
	public SubjectBigPage initModel(){
		
		return new SubjectBigPage();
	}
	/**
	 * 查询班级信息
	 * @param model	模型数据
	 * @param pageNum	当前页
	 * @param pageSize  每页多少条
	 * @return 页面
	 */
	@RequestMapping("query")
	public String query(ModelMap mp ,@ModelAttribute SubjectBigPage page){
		//开启分页
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Subject> list = service.queryAll(page);
		mp.put("pageInfo", new PageInfo<Subject>(list));
		return "/manage/subjectBig/subject_query";
	}
	
	/**
	 * 添加跳页
	 */
	@RequestMapping("addPage")
	public String addPage() {
		
		return "/manage/subjectBig/subject_add";
	}
	
	/**
	 * 验证名称是否重复
	 */
	@RequestMapping("subjectBigname")
	@ResponseBody
	public ResultJson bigName(String subjectName) {
		int count = service.querysubjectBigname(subjectName);
		ResultJson r = new ResultJson();
		if (count > 0) {
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r;
	}
	
	/**
	 * 添加保存
	 */
	@RequestMapping("addSave")
	@ResponseBody
	public ResultJson addSave(Subject sj ) {
		int tiao = service.addSave(sj);
		ResultJson r = new ResultJson();
		if (tiao > 0) {
			r.setSuccess(true);

		} else {
			r.setSuccess(false);
		}
		return r;
	}
	
	/**
	 * 修改跳页
	 */
	@RequestMapping("editPage")
	public String editPage(String id,ModelMap mp) {
		Subject sj = service.queryBigSubjectById(id);
		mp.put("sj", sj);
		return "/manage/subjectBig/subject_edit";
	}
	
	/**
	 * 修改保存
	 */
	@RequestMapping("editSave")
	@ResponseBody
	public ResultJson editSave(Subject sj ) {
		int tiao = service.editSave(sj);
		ResultJson r = new ResultJson();
		if (tiao > 0) {
			r.setSuccess(true);

		} else {
			r.setSuccess(false);
		}
		return r;
	}
	/**
	 * 删除
	 */
	@RequestMapping("delete")
	@ResponseBody
	public ResultJson delete(String ids) {
		String names = service.delete(ids);
		ResultJson r = new ResultJson();
		if ("".equals(names)) {
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
			r.setMsg("删除失败,<span style=\"color:red;\">该类别下存在:" + names + "<span>学科,不能删除!");
		}
		return r;
	}
}
