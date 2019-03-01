package com.turing.framework.tag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.github.pagehelper.PageInfo;

@SuppressWarnings("serial")
public class PageTag2 extends TagSupport{
	private String uri;
	private String pageName;
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		PageInfo pageInfo = (PageInfo) request.getAttribute(pageName);
		String ctx = request.getContextPath();
		JspWriter out = pageContext.getOut();
		boolean hasShang = pageInfo.isHasPreviousPage();
		boolean hasXia = pageInfo.isHasNextPage();
		int beforePage = pageInfo.getPrePage();
		int afterPage = pageInfo.getNextPage();
		int lastPage = pageInfo.getPages();	//最后一页
		String lj = parseUri(uri);
		try {
			out.print("<ul class=\"pager\">");
			int ye = 1;
			if (hasShang) {
				ye = beforePage;
			}else{
				ye = 1;
			}
			out.print("<li><a href=\""+ctx+"/"+uri+""+lj+"pageNum="+ye+"\">上一页</a></li>");
			if (hasXia) {
				ye = afterPage;
			}else{
				ye = lastPage;
			}
			out.print("<li><a href=\""+ctx+"/"+uri+""+lj+"pageNum="+ye+"\">下一页</a></li>");
			out.print("<li>  "+pageInfo.getPageNum()+"/"+lastPage+"</li>");
			out.print("</ul>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return super.doStartTag();
	}
	private String parseUri(String uri2) {
		//判断传入的uri中是否存在？   如果有就加个&号，没有就加个？号
		int p = uri2.lastIndexOf("?");
		if( p == -1 ){
			return "?";
		}else{
			return "&";
		}
	}

	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}


	public String getPageName() {
		return pageName;
	}


	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
}
