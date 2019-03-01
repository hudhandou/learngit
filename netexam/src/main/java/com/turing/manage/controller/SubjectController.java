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
import com.turing.manage.page.SubjectPage;
import com.turing.manage.service.ISubjectService;
import com.turing.manage.service.impl.SubjectServiceImpl;
/**
 * 学科的基本操作
 * @author Administrator
 *
 */
@Controller
@RequestMapping("subject")
@SessionAttributes("subjectPage")
public class SubjectController {
    /**
     * 模型层
     */
	@Autowired
	private ISubjectService service;
	/**
	 * 匹配是否有同名对象,没有就将返回值放在model里
	 * @return
	 */
	@ModelAttribute("subjectPage")
	public SubjectBigPage initModel() {
		
		return new SubjectPage();
	}
	/**
	 * 查询班级信息
	 * @param model	模型数据
	 * @param pageNum	当前页
	 * @param pageSize  每页多少条
	 * @return 页面
	 */
	@RequestMapping("query")
	public String query(ModelMap mp ,@ModelAttribute SubjectPage page){
		//开启分页
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		List<Subject> list = service.queryAllBysubject(page);
		mp.put("pageInfo", new PageInfo<Subject>(list));
		return "/manage/subject/subject_query";
	}
	
	/**
	 * 添加跳页
	 */
	@RequestMapping("addPage")
	public String addPage(ModelMap mp) {
		List<Subject> sjb = service.queryBig();
		mp.put("sjb", sjb);
		return "/manage/subject/subject_add";
	}
	
	/**
	 * 验证名称是否重复
	 */
	@RequestMapping("subjectname")
	@ResponseBody
	public ResultJson bigName(String subjectName) {
		int count = service.querysubjectname(subjectName);
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
		List<Subject> sjblist = service.queryBig();
		Subject sj = service.queryBigSubjectById(id);
		mp.put("sjblist", sjblist);
		mp.put("sj", sj);
		return "/manage/subject/subject_edit";
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
		int tiao = service.delete(ids);
		ResultJson r = new ResultJson();
		if (tiao>0) {
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
			
		}
		return r;
	}
}
