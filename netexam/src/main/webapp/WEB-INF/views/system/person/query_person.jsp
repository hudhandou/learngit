<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询人员</title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<label class="control-label label-default col-xs-12 " style="margin-bottom: 30px;">
			<h4>当前位置：人员维护 － 查询人员</h4>
		</label>
		<div class="container">
			<hr />
			<form class="form-horizontal text-center" action="<%=ctx%>/person/query.action?pageNo=1" method="post">
				<div class="container ">
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">人员名:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppName" placeholder="请输入人员名" />
						</div>
						<label class="control-label col-sm-2 col-md-2">所属部门:</label>
						<div class="col-md-4 col-sm-4">
							<select name="deptId" class="form-control">
								<option value="">===请选择===</option>
								<c:forEach items="${deptList}" var="dept">
									<option value="${dept.deptId }">${dept.deptName }</option>
								</c:forEach>
							</select>
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
		<form action="<%=ctx %>/person/delete.action" method="post" name="delForm">
		<div class="container ">
			<table class="table table-hover table-striped  text-center" >
				<caption class="text-center"><strong>人员信息</strong></caption>
				<thead >
					<tr class="bg-primary">
						<td>序号</td>
						<td><a href="javascript:selectAll()"><font style="color: white;">全选</font></a></td>
						<td>人员姓名</td>
						<td>所属部门</td>
						<td>人员职务</td>
						<td>人员编号</td>
						<td>所用用户</td>
						<td>信息</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="person" varStatus="i">
					<tr>
						<td>${i.count+(page.pageNo-1)*page.pageSize}</td>
						<td><input type="checkbox" name="ids" value="${person.ppId}"></td>
						<td>${person.ppName}</td>
						<td>${person.deptName}</td>
						<td>${person.spName}</td>
						<td>${person.ppNo}</td>
						<td>${person.nickName}</td>
						<td>${person.ppInfo}</td>
						<td>
							<a href="javascript:editpage('${person.ppId }')">修改</a>&nbsp;&nbsp;
							<c:if test="${person.nickName==null }">
								<a href="javascript:user_person('${person.ppId }')">关联用户</a>&nbsp;&nbsp;
							</c:if>
							<c:if test="${person.nickName!=null }">
								<a href="javascript:user_person('${person.ppId }')">修改用户</a>&nbsp;&nbsp;
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>	
		</div>
		</form>
			<page:page1 requestMappingString="person"></page:page1>
	</body>
	
</html>
<script type="text/javascript">
	$(function(){
		if("${page.ppName}"!=""){
			$("input[name='ppName']").val("${page.ppName}");
		}
		if("${page.deptId}"!=""){
			$("select[name='deptId']").val("${page.deptId}");
		}
	});
	
	function addpage(){
		window.location.href = "<%=ctx%>/person/page.action";
	}
	
	function editpage(id){
		window.location.href = "<%=ctx%>/person/page.action?id="+id;
	}
	
	function del(){
		$("form[name='delForm']").submit();
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
	
	function user_person(ppId){
		window.location.href = "<%=ctx%>/person/userPerson.action?ppId="+ppId;
	}
</script>