<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询用户</title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<label class="control-label label-default col-xs-12 " style="margin-bottom: 30px;">
			<h4>当前位置：用户维护 － 查询用户</h4>
		</label>
		<div class="container">
			<hr />
			<form class="form-horizontal text-center" action="<%=ctx%>/user/query.action?pageNo=1" method="post">
				<div class="container ">
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">显示名:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="nickName" placeholder="请输入显示名" />
						</div>
						<label class="control-label col-sm-2 col-md-2">登陆名:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="userName" placeholder="请输入登录名" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
							
						</div>
						<div class=" col-lg-6 col-md-6 col-sm-8 col-xs-8" >
							<div class="col-md-3 col-sm-3">
								<input class="form-control btn-primary" type="submit" value="查询" />
							</div>
							<div class="col-md-3 col-sm-3">
								<input class="form-control btn-primary" type="reset" value="重置" />
							</div>
							<div class="col-md-3 col-sm-3">
								<input class="form-control btn-primary" type="button" onclick="addpage()" value="增加" />
							</div>
							<div class="col-md-3 col-sm-3">
								<input class="form-control btn-primary" type="button" onclick="del()" value="删除" />
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
							
						</div>
					</div>
				</div>
			</form>
		</div>
		<form action="<%=ctx %>/user/delete.action" method="post" name="delForm">
		<div class="container ">
			<table class="table table-hover table-striped  text-center" >
				<caption class="text-center"><strong>用户信息</strong></caption>
				<thead >
					<tr class="bg-primary">
						<td>序号</td>
						<td><a href="javascript:selectAll()"><font style="color: white;">全选</font></a></td>
						<td>显示名</td>
						<td>登录名</td>
						<td>关联人员</td>
						<td>用户信息</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="user" varStatus="i">
					<tr>
						<td>${i.count+(page.pageNo-1)*page.pageSize}</td>
						<td><input type="checkbox" name="ids" value="${user.userId}"></td>
						<td>${user.nickName}</td>
						<td>${user.userName}</td>
						<td>${user.sysPersonName}</td>
						<td>${user.userInfo}</td>
						<td>
							<a href="javascript:editpage('${user.userId }')">修改</a>&nbsp;&nbsp;
							<a href="javascript:userRole('${user.userId }')">授权</a>&nbsp;&nbsp;
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>	
		</div>
		</form>
			<page:page1 requestMappingString="user"></page:page1>
	</body>
	
</html>
<script type="text/javascript">
	$(function(){
		if("${page.userName}"!=""){
			$("input[name='userName']").val("${page.userName}");
		}
		if("${page.nickName}"!=""){
			$("input[name='nickName']").val("${page.nickName}");
		}
	});
	
	function addpage(){
		window.location.href = "<%=ctx%>/user/page.action";
	}
	
	function editpage(id){
		window.location.href = "<%=ctx%>/user/page.action?id="+id;
	}
	
	function del(){
		$("form[name='delForm']").submit();
	}
	
	function userRole(id){
		window.location.href = "<%=ctx%>/user/userRole.action?id="+id;
	}
	
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
	
	
</script>