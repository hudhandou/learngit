<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title>查询可以考试的试卷</title>
    </head>
    <body style="background:linear-gradient(white,#ebebeb,white);">
    
        <div class="container" style="margin-top:10px;">
            <form name="searchForm" action="<%=ctx %>/paper/query.action" class="form form-horizontal" method="post">
                <div class="form-group">
                    
                </div>
                
            </form>
        </div>
        <form action="<%=ctx %>/jjfs/delete.action" method="post" name="delForm">
        <div class="container ">
            <table class="table table-hover table-striped  text-center" >
                <caption class="text-center" style="border: 0px solid;">
                   
                        <strong>试卷信息</strong>
                    
                </caption>
                <thead >
                    <tr class="bg-primary">
                        <td>序号</td>
                        <td>试卷名称</td>
                        <td>需要时间(分钟)</td>
                        <td>每题分数</td>
                        <td>题目数量</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list }" var="t" varStatus="i">
                    <tr>
                        <td>${i.count}</td>
                        <td>${t.paperName}</td>
                        <td>${t.paperNeedtime }</td>
                        <td>${t.paperScore }</td>
                        <td>${t.paperTmsl }</td>
                        <td>
                            <button onclick="startexam('${t.paperId }')" type="button" class="btn btn-sm btn-primary">开始考试</button>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>    
        </div>
        </form>
        
        
        <%-- 分页start --%>
        <div class="container text-center"  >
            <page:mypage1 pageName="pageInfo" uri="exam/query.action" ></page:mypage1>
        </div>
        <%-- 分页end --%>
    </body>

</html>
<%@include file="/common/noty.jsp" %>
<script type="text/javascript">
    //开始考试
    var examDialog = null
    function startexam(id){
    	examDialog = bootbox.dialog({  
    	    title: "标题",  
    	    message: "<iframe src=\"<%=ctx%>/exam/startexam.action?id="+id+"\" width='100%' height='400px' style='border:0;'></iframe>",  
    	    size: "large",
    	    buttons: {  
    	        Cancel: {  
    	            label: "关闭",  
    	            className: "btn-default",  
    	            callback: function () {  
    	                //取消 
    	            }  
    	        }  
    	    }  
    	});
    }
    function closeModal(){
    	examDialog.modal("hide");
    }
</script>