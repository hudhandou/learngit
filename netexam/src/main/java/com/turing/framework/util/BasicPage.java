package com.turing.framework.util;

import java.util.ArrayList;
import java.util.List;



public abstract class BasicPage<T> {
	
	private int pageNo=1;//页号
	private int pageSize=5;//每页显示多少行
	private int pageTotalSize;//一共有多少条数据
	private int pageGroup=1;//第几组
	private int pageGroupSize=5;//每组多少页
	private int pageTotalGroup;//一共多少组
	private int pageCount;//一共有多少页
	
	
	private List rows=new ArrayList();
	
	
	
	
	public abstract String reWhereString(T page);

	public abstract String pageSql(T page);

	public abstract String allSql(T page);
	

	
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageTotalSize() {
		return pageTotalSize;
	}
	public void setPageTotalSize(int pageTotalSize) {
		this.pageTotalSize = pageTotalSize;
	}
	public int getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	public int getPageTotalGroup() {
		return pageTotalGroup;
	}
	public void setPageTotalGroup(int pageTotalGroup) {
		this.pageTotalGroup = pageTotalGroup;
	}
	
	
	/**
	 * 通过页号和每页多少条来计算分页的所有数据
	 * @param p 分页对象
	 * @param l 分页查询返回的list
	 * @param size 总共有多少条数据
	 * @param page 页号
	 * @param rows2 每页多少行
	 */
	public void setPageState(BasicPage p, List l) {
		
		p.setRows(l);
		p.setPageTotalSize(l.size());
		int zurongliang=p.getPageGroupSize();
		
		p.setPageGroup((p.getPageNo()-1)/zurongliang+1);
		p.setPageCount(p.getPageTotalSize()%p.getPageSize()==0?p.getPageTotalSize()/p.getPageSize():p.getPageTotalSize()/p.getPageSize()+1);
		p.setPageTotalGroup(p.getPageCount()%p.getPageGroupSize()==0?p.getPageCount()/p.getPageGroupSize():p.getPageCount()/p.getPageGroupSize()+1);
		
		if(p.getPageNo()>p.getPageCount()){
			p.setPageNo(1);
		}
	}

}
