package com.turing.framework.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.turing.framework.util.BasicPage;

@SuppressWarnings("serial")
public class PageBarTag extends TagSupport {
	
	private String  requestMappingString=""; 

	@Override
	public int doStartTag() throws JspException {
		
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		String path=request.getContextPath()+"/";
		
		
		JspWriter out = this.pageContext.getOut();
		
		
		BasicPage page = (BasicPage)request.getAttribute(requestMappingString+"page");
		
		int pageNo=page.getPageNo();//页号
		int pageSize=page.getPageSize();//每页显示多少行
		int pageTotalSize=page.getPageTotalSize();//一共有多少条数据
		int pageGroup=page.getPageGroup();//第几组
		int pageGroupSize=page.getPageGroupSize();//每组多少页
		int pageTotalGroup=page.getPageTotalGroup();//一共多少组
		int pageCount=page.getPageCount();//一共有多少页
		

		
		StringBuffer strbuf=new StringBuffer();
		strbuf.append("");
		
		strbuf.append("<div class=\"panel-footer  \">");
		strbuf.append("	<!-- page mobile  移动界面 -->");
		strbuf.append("		<div class=\"container text-center visible-xs\">");
		int ajaxPageParam=(pageNo+1)>pageCount?pageCount:(pageNo+1);
		strbuf.append("			<label class=\"control-label\" onclick=\"ajaxPage('"+ajaxPageParam+"')\" style=\"cursor:pointer\">");
		strbuf.append("				<span id=\"pgcolor\" class=\"\">点击查看更多内容</span>");
		strbuf.append("				<span class=\"fa fa-angle-down\"></span>");
		strbuf.append("			</label>");
		strbuf.append("		</div>");
		strbuf.append("		<!-- page mobile  end -->");
		
		strbuf.append("<!-- 自定义分页标签 先在footer中创建一个 class＝pageTag的div 再用include引入jsp的文件，在引入文件的 -->");
		strbuf.append("<div class=\"pageTag visible-md visible-sm visible-lg\">");
		strbuf.append("			<div id=\"customers2_length\" class=\"col-sm-6\">");
		strbuf.append("				<label>");
		strbuf.append("				<span>每页显示</span>");
		strbuf.append("					<select id=\"rows\" onchange=\"turnRows()\" class=\"\" name=\"customers2_length\" aria-controls=\"customers2\">");
		
		strbuf.append("						<option value=\"5\" " + ((pageSize==5)?"selected":"") + ">5</option>");
		strbuf.append("						<option value=\"10\" " + ((pageSize==10)?"selected":"") + ">10</option>");
		strbuf.append("						<option value=\"30\" " + ((pageSize==30)?"selected":"" ) + ">30</option>");
		strbuf.append("						<option value=\"50\" " + ((pageSize==50)?"selected":"") + ">50</option>");
		strbuf.append("					</select>");
		strbuf.append("				<span>条</span>");	 
		strbuf.append("				&nbsp;&nbsp;&nbsp;"); 
		
		strbuf.append("				<span>从</span> <span id=\"rowFrom\" >" + (pageSize*(pageNo-1)+1)+ "</span> 到 <span id=\"rowTo\" >"+
									((pageSize*pageNo>pageTotalSize)?pageTotalSize:(pageSize*pageNo))  + "</span><span>条 </span>"); 
		
		strbuf.append("				共 <span id=\"rowCount\" >" + pageTotalSize + "</span> 条");     
		strbuf.append("				</label>");     
		strbuf.append("			</div>");     
		strbuf.append("			<div id=\"customers2_paginate\" class=\"dataTables_paginate \">");     
		strbuf.append("				<a href=\"javascript:pagego('1')\" class=\"paginate_button previous disabled\" aria-controls=\"customers2\" data-dt-idx=\"0\" tabindex=\"0\">首页</a>");     
		strbuf.append("				<a href=\"javascript:pagego('" + ((pageNo-1)<1?1:(pageNo-1))+ "')\" id=\"customers2_previous\" class=\"paginate_button previous disabled\" aria-controls=\"customers2\" data-dt-idx=\"0\" tabindex=\"0\">前一页</a>");     
		strbuf.append("				<a id=\"leftPG\" href=\"javascript:leftPageGroup('" + pageGroup+ "')\"    class=\"paginate_button \" aria-controls=\"customers2\" data-dt-idx=\"0\" tabindex=\"0\">...</a>");     
				
		strbuf.append("				<span>");   
		
		int   forEachEnd=((pageCount -(pageGroup-1)* pageGroupSize) >pageGroupSize ? pageGroupSize :( pageCount -(pageGroup-1)* pageGroupSize ));
		for (int i = 1; i <= forEachEnd; i++) {
			strbuf.append("		<a id=\"pagenumber" + pageNo+ "\"   href=\"javascript:pagego('" + (i+(pageGroup-1)* pageGroupSize)+ "')\" class=\"paginate_button  \" aria-controls=\"customers2\" data-dt-idx=\"" + (i+(pageGroup-1)* pageGroupSize)+ "\" tabindex=\"0\">" + (i+(pageGroup-1)* 5) + "</a>");     
		}
		
		strbuf.append("				</span>");     
		strbuf.append("				<a id=\"rightPG\"  href=\"javascript:rightPageGroup('" + pageGroup+ "')\"  class=\"paginate_button \" aria-controls=\"customers2\" data-dt-idx=\"6\" tabindex=\"0\">...</a>");     
		strbuf.append("				<a  href=\"javascript:pagego('"+ ((pageNo+1)>pageCount?pageCount:(pageNo+1))+ "')\" id=\"customers2_next\" class=\"paginate_button next\" aria-controls=\"customers2\" data-dt-idx=\"7\" tabindex=\"0\">后一页</a>");     
		strbuf.append("				<a  href=\"javascript:pagego('" + pageCount+ "')\" class=\"paginate_button next\" aria-controls=\"customers2\" data-dt-idx=\"7\" tabindex=\"0\">尾页</a>");     
		strbuf.append("			</div>");     
		strbuf.append("		</div>");     
		strbuf.append("	</div>");  
		

		strbuf.append("		<script type=\"text/javascript\">");  
		
		
		
	/**/	
////移动端的分页方式解决方案
		
		StringBuffer strfnMobile=new StringBuffer();
		strfnMobile.append("");
		strfnMobile.append(" 	var pno="+(pageNo+1)+";");
		strfnMobile.append("	var psz="+pageSize+";");
		strfnMobile.append("	function ajaxPage(p){   ");
		strfnMobile.append(" 		var pageSize=$(\"#rows\").val(); ");
		strfnMobile.append("		$.get('"+path+requestMappingString+"/ajaxPage.action?pageNo='+pno+'&pageSize='+pageSize,function(msg){  ");

		////由于页面不会刷新pageNo是定值但是条件查询会引发冲突，所以当pageNo不是1
		////的时候强制刷新回第一页
		strfnMobile.append("		var ppp='"+pageNo+"'; ");
		
		strfnMobile.append("		if(ppp!='1'){ ");
		strfnMobile.append("			window.location.href='"+path+requestMappingString+"/query.action?pageNo=1&pageSize='+pageSize; ");
		strfnMobile.append("			return; ");
		strfnMobile.append("		} ");
		
		strfnMobile.append("	if(pno<="+pageCount+"){ ");
		
		strfnMobile.append("	for(var i=0;i<msg.length;i++){ ");
		//	
		strfnMobile.append("		var str=\"<tr>\"+ ");
		strfnMobile.append("	\"		<td  >\"+((pno-1)*psz+1+i) +\"</td>\"+ ");
		strfnMobile.append("	\"		<td><div class=\'icheckbox_minimal-grey\' style=\'position: relative;\'><input class=\'icheckbox\' type=\'checkbox\' value=\''+msg[i].stuId+'\' style=\'position: absolute; visibility: hidden;\'><ins class=\'iCheck-helper\' style=\\\"position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);\\\"></ins></div></td>\"+");
		strfnMobile.append("	\"		<td >\"+");
		strfnMobile.append("	\"&nbsp;<button onclick=\\\"location.href='"+path+requestMappingString+"/addOrEditPage.action?id='+msg[i].stuId+'\\\" class=\'visible-md-inline visible-sm-inline visible-lg-inline btn btn-primary\'>修改</button>&nbsp;\"+");
		strfnMobile.append("	\"&nbsp;<button onclick=\\\"deleteOne(\'\"+msg[i].stuId+\"\',this)\\\" class=\'visible-md-inline visible-sm-inline visible-lg-inline btn btn-danger\'>删除</button>&nbsp;\"+");
		strfnMobile.append("	\"&nbsp;<button onclick=\\\"location.href='"+path+requestMappingString+"/addOrEditPage.action?id='+msg[i].stuId+'\\\" class=\'visible-xs-inline btn btn-xs btn-primary\'><span class=\\\"fa fa-edit\\\"></span></button>&nbsp;\"+");
	//	strfnMobile.append("	\"&nbsp;<button onclick=\\\"deleteOne(\'\"+msg[i].stuId+\"\',this)\\\" class=\'visible-xs-inline btn btn-xs  btn-danger\'><span class=\'glyphicon glyphicon-trash\'></span></button>&nbsp;\"+");
		strfnMobile.append("	\"		</td>\"+");
		strfnMobile.append("	\"		<td >\"+msg[i].stuName+\"</td>\"+");
		strfnMobile.append("	\"		<td >\"+msg[i].stuInAge+\"</td>\"+");
		strfnMobile.append("	\"		<td >\"+msg[i].ctName+\"</td>\"+");
		strfnMobile.append("	\"		<td >\"+msg[i].pyNames+\"</td>\"+");
					
		strfnMobile.append("			\"	</tr>\";");
			
		strfnMobile.append("			$(\"#dataTable\").append(str); ");
		strfnMobile.append("		} ");
		strfnMobile.append("		pno++; ");
		strfnMobile.append("		$(\"#pgcolor\").html('点击查看更多内容'); ");
		strfnMobile.append("		$(\"#pgcolor\").removeClass(\"text-muted\"); ");
		strfnMobile.append("	}else{ ");
		strfnMobile.append("		$(\"#pgcolor\").html('没有更多内容'); ");
		strfnMobile.append("		$(\"#pgcolor\").addClass(\"text-muted\"); ");
		strfnMobile.append("	} ");

		
		////让后创建的复选框可以选中的方法

		strfnMobile.append("	$(\".iCheck-helper:gt('+((pno-2)*psz)+')\").bind('click',chk);");
		strfnMobile.append("	},\"json\"); ");
		strfnMobile.append("		}  ");
		
		//System.out.println( "strfnMobile:"+ strfnMobile  );
		
		strbuf.append(	strfnMobile	);
		
		
//
////转到对应页
		StringBuffer strfnPagego=new StringBuffer();
		strfnPagego.append(" function pagego(p){ ");
		strfnPagego.append(" 		var pageSize=$(\"#rows\").val(); ");
		strfnPagego.append(" 		window.location.href='"+path+requestMappingString+"/query.action?pageNo='+p+'&pageSize='+pageSize ;");
		strfnPagego.append(" } ");
		strbuf.append( strfnPagego	);
//
////重新设定每页显示数量   回到第1页
		StringBuffer strfnTurnRows=new StringBuffer();
		strfnTurnRows.append("	function turnRows(){	");
		strfnTurnRows.append(" 		var pageSize=$(\"#rows\").val(); ");
		strfnTurnRows.append("		window.location.href='"+path+requestMappingString+"/query.action?pageNo=1&pageSize='+pageSize ;");
		strfnTurnRows.append("	}	");
		strbuf.append( strfnTurnRows	);
				
//
		
////点击分页 左边...  转到上一组分页信息
		StringBuffer strfnLeftPageGroup=new StringBuffer();
		strfnLeftPageGroup.append(" function leftPageGroup(pg){ ");
		strfnLeftPageGroup.append(" 	var pageSize=$(\"#rows\").val(); ");
		strfnLeftPageGroup.append(" 	var pageNo=(pg-2)*"+pageGroupSize+"+1; ");
		strfnLeftPageGroup.append("		window.location.href='"+path+requestMappingString+"/query.action?pageNo='+pageNo+'&pageSize='+pageSize ;");
		strfnLeftPageGroup.append(" } ");
		
		strbuf.append( strfnLeftPageGroup	);
//
//点击分页 右边...  转到下一组分页信息
		StringBuffer strfnRightPageGroup=new StringBuffer();
		strfnRightPageGroup.append("	function rightPageGroup(pg){");
		//strfnRightPageGroup.append("		alert(pg) ;");
		strfnRightPageGroup.append("		var pageSize=$(\"#rows\").val();");
		strfnRightPageGroup.append("		var pageNo=pg*"+pageGroupSize+"+1;");
		strfnRightPageGroup.append("		window.location.href='"+path+requestMappingString+"/query.action?pageNo='+pageNo+'&pageSize='+pageSize ;");
		strfnRightPageGroup.append("	}	");
		
		//System.out.println("strfnRightPageGroup:"+strfnRightPageGroup);
		
		strbuf.append( strfnRightPageGroup	);
		
		
////加载执行
		//分页   左右... 的显隐
		StringBuffer strfnLeftRight=new StringBuffer();
		
		strfnLeftRight.append("  $(function(){ ");  
		
		boolean pageGroup0=pageCount<=5;
		strfnLeftRight.append(" 	if( "+pageGroup0+"){	");  	
		strfnLeftRight.append("			$(\"#leftPG\").hide();	");  	
		strfnLeftRight.append("			$(\"#rightPG\").hide();	");  	
		boolean pageGroup1=(pageCount > pageGroupSize) && (pageGroup==1);
		strfnLeftRight.append("		}else if( "+pageGroup1 + "){" );  
		strfnLeftRight.append("			$(\"#leftPG\").hide();");  
		strfnLeftRight.append("			$(\"#rightPG\").show();");  
		boolean pageGroup2=(pageCount > pageGroupSize)  && (pageGroup>1)  &&  ((pageGroup*pageGroupSize)<pageCount);
		strfnLeftRight.append("		}else if( "+pageGroup2+" ){");  
		strfnLeftRight.append("			$(\"#leftPG\").show();");  
		strfnLeftRight.append("			$(\"#rightPG\").show();");  
		boolean pageGroup3=(pageCount>pageGroupSize)  &&  (pageGroup>1)  &&  ((pageGroup*pageGroupSize)>pageCount);
		strfnLeftRight.append("		}else if(	"+pageGroup3+" ){");  
		strfnLeftRight.append("			$(\"#leftPG\").show();");  
		strfnLeftRight.append("			$(\"#rightPG\").hide();");  
		strfnLeftRight.append("		}else{");  
		strfnLeftRight.append("			$(\"#leftPG\").hide();");  
		strfnLeftRight.append("			$(\"#rightPG\").hide();");  
		strfnLeftRight.append("		}");  
		strfnLeftRight.append("	});  	");  
		
		//System.out.println("eval:"+strfnLeftRight);
		strbuf.append( strfnLeftRight	);
		
//
		
	////current  选中变色
		StringBuffer strfnCurrent=new StringBuffer();
		
		strfnCurrent.append("	 $(function(){ ");  
		

		strfnCurrent.append("		var as=$(\"a[id^=pagenumber]\");");  
		strfnCurrent.append("		$.each(as,function(index,item){");  
		strfnCurrent.append("			if($(item).html()=='"+pageNo+"'){");  
		strfnCurrent.append("				$(item).addClass(\"current\");");  
		strfnCurrent.append("			}");  
		strfnCurrent.append("		});");  
		
		strfnCurrent.append("	});		");  
		//System.out.println("current:"+strfnCurrent);
		strbuf.append(strfnCurrent);
//
//
		
////让后创建的复选框选中的方法
	
		StringBuffer strfnChk=new StringBuffer();
		strfnChk.append("	function chk(){  ");

		strfnChk.append("		if($(this).parent().attr(\"class\").indexOf(\"checked\")==-1){");
		strfnChk.append("			$(this).parent().addClass(\"checked\");");
		strfnChk.append("			$(this).prev().attr(\"checked\",true);");
		strfnChk.append("		}else{");
		strfnChk.append("			$(this).prev().attr(\"checked\",false);");
		strfnChk.append("			$(this).parent().removeClass(\"checked\");");
		strfnChk.append("		} ");
		strfnChk.append("	}   ");

		strbuf.append(strfnChk);


		strbuf.append("		</script>");
		
	
	
	
		try {
			//分页内容
			out.print(strbuf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}

	public String getRequestMappingString() {
		return requestMappingString;
	}

	public void setRequestMappingString(String requestMappingString) {
		this.requestMappingString = requestMappingString;
	}
	
}
