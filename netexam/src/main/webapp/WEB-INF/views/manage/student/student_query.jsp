<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title>查询学生信息</title>
    </head>
    <body style="background:linear-gradient(white,#ebebeb,white);">
    
        <div class="row" style="margin-top:10px;">
            <form name="searchForm" action="<%=ctx %>/paper/query.action" class="form form-horizontal" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2 col-md-2">学生姓名:</label>
                    <div class="col-sm-2">
                        <input class="form-control" name="paperName" value="" placeholder="请输入学生名称" />
                    </div>
                    <label class="control-label col-sm-2 col-md-2">所属班级:</label>
                    <div class="col-sm-2">
                        <select class="form-control">
                        	<option value="">===选择班级===</option>
                        	<c:forEach items="${classList }" var="t">
                        		<option value="${t.classId }">${t.className }</option>
                        	</c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-1">
                        <input class="form-control btn-primary" type="submit" value="查询" />
                    </div>
                    <div class="col-sm-1">
                        <input class="form-control btn-primary" type="button" onclick="cleanUp()" value="清空" />
                    </div>
                </div>
                
            </form>
        </div>
        <form action="<%=ctx %>/jjfs/delete.action" method="post" name="delForm">
        <div class="row " style="margin-left:5px;">
            <table class="table table-hover table-striped  text-center" >
                <caption class="text-center" style="border: 0px solid;">
                    <div class="col-sm-3" style="margin-left:-15px;">
                        <div class="btn-group" style="display:block;">
                            <button type="button" class="btn btn-danger" onclick="del()">删除<span class="glyphicon glyphicon-minus"></span></button>
                        </div>
                    </div>
                    <div class="col-sm-5 text-center">
                        <strong>试卷信息</strong>
                    </div>
                </caption>
                <thead >
                    <tr class="bg-primary">
                        <td>序号</td>
                        <td><a href="javascript:selectAll()"><font style="color: white;">全选</font></a></td>
                        <td>学生姓名</td>
                        <td>电话</td>
                        <td>所属班级</td>
                        <td>操作</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list }" var="t" varStatus="i">
                    <tr>
                        <td>${i.count}</td>
                        <td><input type="checkbox" name="ids" value="${t.studentId}"></td>
                        <td>${t.studentName}</td>
                        <td>${t.studentTel }</td>
                        <td>${t.className }</td>
                        <td>
                        	<button onclick="examforview('${t.studentId }')" type="button" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-edit"></span> 考试历程</button>
                        </td>
                        <td>
                            <button onclick="setpass('${t.studentId }')" type="button" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-edit"></span> 重设密码</button>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>    
        </div>
        </form>
        
        
        <%-- 分页start --%>
        <div class="container text-center"  >
            <page:mypage1 pageName="pageInfo" uri="paper/query.action" formName="searchForm"></page:mypage1>
        </div>
        <%-- 分页end --%>
    </body>

</html>
<%@include file="/common/noty.jsp" %>
<script type="text/javascript">
    function examyesorno(state,paperId){
    	window.location.href = "<%=ctx%>/paper/examyesorno.action?state="+state+"&paperId="+paperId;
    }
    function addpage(){
        window.location.href = "<%=ctx%>/paper/addpage.action";
    }
    
    function editpage(id){
        window.location.href = "<%=ctx%>/paper/editpage.action?id="+id;
    }
    
    //全选
    function selectAll(){
        var inpts = $("input[name='ids']");
        for(var i=0;i<inpts.length;i++){
            if(inpts[i].checked!=true){
                inpts[i].checked=true;
            }else{
                inpts[i].checked=false;
            }
        }
    }
    
    function del(){
        var idsinput = $("input[name='ids']:checked");
        if (idsinput.length == 0) {
            bootbox.alert("请先选择要删除的记录!");
            return;
        }
        bootbox.confirm("确定要删除吗?",function(r){
            if (r) {
                $("form[name='delForm']").attr("action","<%=ctx%>/paper/delete.action");
                $("form[name='delForm']").submit();
            }
        });
    }
    
    function setpass(studentId){
    	bootbox.prompt("请输入新密码",function(r){
    		if(r != null){
    			//
    		}
    	});
    }
</script>