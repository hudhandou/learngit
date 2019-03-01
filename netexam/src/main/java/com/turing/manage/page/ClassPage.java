package com.turing.manage.page;

/**
 *@author 胡
 *
 **/
public class ClassPage{
	private Integer pageNum=1; //第几页
	private Integer pageSize=10;//每页显示多少行数据
	private String  className;
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
