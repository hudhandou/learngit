package com.turing.manage.page;

/**
 *@author 胡
 *
 **/
public class TopicPage{
	private Integer pageNum=1; //第几页
	private Integer pageSize=10;//每页显示多少行数据

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
