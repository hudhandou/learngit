package com.turing.manage.controller;

import java.util.ArrayList;
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
import com.turing.framework.util.TreeNode;
import com.turing.manage.entity.Classs;
import com.turing.manage.entity.Paper;
import com.turing.manage.page.ClassPage;
import com.turing.manage.page.PaperPage;
import com.turing.manage.service.IClassService;
import com.turing.manage.service.IPaperService;
/**
 * 试卷的基本操作
 * @author Administrator
 *
 */
@Controller
@RequestMapping("paper")
@SessionAttributes("paperpage")
public class PaperController {

	/**
	 * 模型层
	 */
	@Autowired
	private IPaperService service;
	/**
	 * 匹配是否有同名对象,没有就将返回值放在model里
	 * @return
	 */
	@ModelAttribute("paperpage")
	public PaperPage a() {
		return new PaperPage();		
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
		@ModelAttribute("paperpage") PaperPage page,
		ModelMap model){
		PageHelper.startPage(page.getPageNum(),page.getPageSize());
		List<Paper> list = service.queryAll(page);
		model.addAttribute("pageInfo", new PageInfo<Paper>(list));
		//model.put("list", list);

		return "manage/paper/paper_query";		
	}
	/**
	 * 转到添加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addpage")
	private String addPage(Model model) {
	
		return "manage/paper/paper_add";	
	}
	/**
	 * 转到修改页面
	 * @param id
	 * @param mp
	 * @return
	 */
	@RequestMapping("editpage")
	public String editPage(String id,ModelMap mp) {
		Paper p = service.queryPaperById(id);
		mp.put("p", p);
	return "manage/paper/paper_edit";
	}
	/**
	 * 系统学科
	 */
	@RequestMapping("querySubjectTree")
	@ResponseBody
	public List<TreeNode> querySubjectTree (String id){
		if (id == null) {
			id="1";
			List<TreeNode> rootList=new ArrayList<TreeNode>();
			TreeNode rootNode=new TreeNode();
			rootNode.setId("0");
			rootNode.setText("系统学科");
			rootNode.setChildren(service.querySubjectTree(id));
			rootNode.setState("open");
			rootList.add(rootNode);
			return rootList;
		}
		return service.querySubjectTree(id);
		
	}
	/**
	 * 查询试卷的所有学科id
	 */
	@RequestMapping("checkedsjtree")
	@ResponseBody
	public List<String> checkedsjtree(String paperId) {
		
		return service.querySubjectPaerById(paperId);
	}
	/**
	 * 添加保存
	 * @param 
	 * @return
	 */
	@RequestMapping(value="addsave")
	@ResponseBody
	public ResultJson addsave(Paper p,String ids){
		
	int tiao = service.addsave(p, ids);
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
	 * @param p
	 * @param ids
	 * @return
	 */
	@RequestMapping("editsave")
	@ResponseBody
	public ResultJson editsave(Paper p ,String ids) {
	int	tiao=service.editsave( p,ids);
	ResultJson r=new ResultJson ();
	if (tiao>0) {
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
	
	/**
	 * 设置考试取消考试
	 */
	@RequestMapping("kaoshiquxiao")
	@ResponseBody
	public ResultJson examyesorno(String zhi) {
		ResultJson r = new ResultJson();
		String[] str = zhi.split(",");
		String state = str[0];
		String id = str[1];
		Paper p = new Paper();
		p.setPaperId(id);
		int tiao = 0;
		if("yes".equals(state)) {   //yes  变为可考试状态
			p.setPaperState("1");
			tiao = service.kaoshi(p);
		}else {  
			p.setPaperState("0");
			tiao = service.kaoshi(p);
		}
		if (tiao>0) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
}
