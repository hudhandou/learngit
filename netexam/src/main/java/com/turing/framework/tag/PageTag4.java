package com.turing.framework.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.github.pagehelper.PageInfo;

public class PageTag4 extends TagSupport{

	private String pageName;
	private String uri;
	@Override
	public int doStartTag() throws JspException {
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		PageInfo pageInfo = (PageInfo) request.getAttribute(pageName);
		String ctx = request.getContextPath();
		int pageNum = pageInfo.getPageNum();
		boolean hasPreviousPage = pageInfo.isHasPreviousPage();
		boolean hasNextPage = pageInfo.isHasNextPage();
		int prePage = pageInfo.getPrePage();
		int nextPage = pageInfo.getNextPage();
		int lastPage = pageInfo.getPages();	//最后一页
		try {
			sb.append("");
			sb.append("<style>");
			sb.append("	.paginationzg {");
			sb.append("	  display: inline-block;");
			sb.append("	  border: 1px solid #CDCDCD;");
			sb.append("	  border-radius: 3px; }");
				
			sb.append("	.paginationzg a {");
			sb.append("	  display: block;");
			sb.append("	  float: left;");
			sb.append("	  width: 60px;");
			sb.append("	  height: 40px;");
			sb.append("	  outline: none;");
			sb.append("	  border-right: 1px solid #CDCDCD;");
			sb.append("	  border-left: 1px solid #CDCDCD;");
			sb.append("	  color: #555555;");
			sb.append("	  vertical-align: middle;");
			sb.append("	  text-align: center;");
			sb.append("	  text-decoration: none;");
			sb.append("	  font-weight: bold;");
			sb.append("	  font-size: 25px;");
			sb.append("	  font-family: Times, 'Times New Roman', Georgia, Palatino;");
			sb.append("	  /* ATTN: need a better font stack */");
			sb.append("	  background-color: #f3f3f3;");
			sb.append("	  background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f3f3f3), color-stop(100%, lightgrey));");
			sb.append("	  background-image: -webkit-linear-gradient(#f3f3f3, lightgrey);");
			sb.append("	  background-image: linear-gradient(#f3f3f3, lightgrey); }");
			sb.append("	  .paginationzg a:hover, .paginationzg a:focus, .paginationzg a:active {");
			sb.append("	    background-color: #cecece;");
			sb.append("	    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #e4e4e4), color-stop(100%, #cecece));");
			sb.append("	    background-image: -webkit-linear-gradient(#e4e4e4, #cecece);");
			sb.append("	    background-image: linear-gradient(#e4e4e4, #cecece); }");
			sb.append("	  .paginationzg a.disabled, .paginationzg a.disabled:hover, .paginationzg a.disabled:focus, .paginationzg a.disabled:active {");
			sb.append("	    background-color: #f3f3f3;");
			sb.append("	    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f3f3f3), color-stop(100%, lightgrey));");
			sb.append("	    background-image: -webkit-linear-gradient(#f3f3f3, lightgrey);");
			sb.append("	    background-image: linear-gradient(#f3f3f3, lightgrey);");
			sb.append("	    color: #A8A8A8;");
			sb.append("	    cursor: default; }");
				
			sb.append("	.paginationzg a:first-child {");
			sb.append("	  border: none;");
			sb.append("	  border-radius: 2px 0 0 2px; }");
				
			sb.append("	.paginationzg a:last-child {");
			sb.append("	  border: none;");
			sb.append("	  border-radius: 0 2px 2px 0; }");
				
			sb.append("	.paginationzg input {");
			sb.append("	  float: left;");
			sb.append("	  margin: 0;");
			sb.append("	  padding: 0;");
			sb.append("	  width: 220px;");
			sb.append("	  height: 40px;");
			sb.append("	  outline: none;");
			sb.append("	  border: none;");
			sb.append("	  vertical-align: middle;");
			sb.append("	  text-align: center;");
			sb.append("	  font-size: 20px;");
			sb.append("	}");
								
			sb.append("</style>");
			sb.append("<div class=\"paginationzg\">");
			int ye = 1;
			sb.append("    <a href=\"javascript:zhuan('"+ye+"')\" class=\"first\" >&laquo;</a>");
			if (hasPreviousPage) {
				ye = prePage;
			}else{
				ye = 1;
			}
			sb.append("    <a href=\"javascript:zhuan('"+ye+"')\" class=\"previous\" >&lsaquo;</a>");
			
			sb.append("    <input type=\"text\" class=\"pageinput\" value=\"Page "+pageNum+" of "+lastPage+"\" ");
			sb.append("    onfocus=\"pageChufa(this)\" onblur=\"pagelikai(this)\" />");
			if(hasNextPage){
				ye = nextPage;
			}else{
				ye = lastPage;
			}
			sb.append("    <a href=\"javascript:zhuan('"+ye+"')\" class=\"next\" >&rsaquo;</a>");
			sb.append("    <a href=\"javascript:zhuan('"+lastPage+"')\" class=\"last\" >&raquo;</a>");
			sb.append("</div>");
			sb.append("<script type=\"text/javascript\">");
			sb.append("	function zhuan(str){");
			String lj = parseUri(uri);
				sb.append("	location.href = \""+ctx+"/"+uri+""+lj+"pageNum=\"+str;");
				sb.append("}");
				sb.append("function pageChufa(o){");
				sb.append("	o.value = \"\";");
				sb.append("}");
				sb.append("function pagelikai(o){");
				sb.append("	o.value = \"Page "+pageNum+" of "+lastPage+"\";");
				sb.append("}");
				sb.append("$(\".pageinput\").bind({");
				sb.append("	keydown:function(e){");
				sb.append("		if(e.keyCode>=48 && e.keyCode <= 57){");
				sb.append("			return true;");
				sb.append("		}else if(e.keyCode>=96 && e.keyCode <= 105){");
				sb.append("			return true;");
				sb.append("		}else if(e.keyCode == 8){");
				sb.append("			return true;");
				sb.append("		}");
				sb.append("		return false;");
				sb.append("	},");
				sb.append("	keyup:function(e){");
				sb.append("		var v = $(this).val();");
				sb.append("		var lastPage = \""+lastPage+"\";");
				sb.append("		if(Number(v) < 1){");
				sb.append("			$(this).val(\"1\");");
				sb.append("		}");
				sb.append("		if(Number(v) > Number(lastPage)){");
				sb.append("			$(this).val(lastPage);");
				sb.append("		}");
				sb.append("		if(e.keyCode == 13){");
				sb.append("			location.href = \""+ctx+"/"+uri+""+lj+"pageNum=\"+$(this).val();");
				sb.append("		}");
				sb.append("		return true;");
				sb.append("	}");
				sb.append("});");
				sb.append("</script>");
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
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
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
