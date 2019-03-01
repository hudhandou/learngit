<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center" style="margin-top: 10px;">
		<form id="sys_tree_editForm" method="post" class="form form-horizontal">
		<input type="hidden" name="menuId" value="${menu.menuId }">
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td align="right">菜单名称：</td> -->
<%-- 					<td style="width: 600px;"><input name="menuName" value="${menu.menuName }" class="easyui-validatebox" data-options="required:true"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="right">菜单顺序号：</td> -->
<%-- 					<td><input name="menuSequ" value="${menu.menuSequ }"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td align="right">菜单URL：</td> -->
<%-- 					<td><input name="menuUrl" value="${menu.menuUrl }" style="width: 600px;"></td> --%>
<!-- 				</tr> -->
<!-- 			</table> -->
			<div class="form-group">
				<label class="control-label col-sm-2 col-md-2">菜单名称：</label>
				<div class="col-md-9 col-sm-9">
					<input class="form-control" name="menuName" value="${menu.menuName }" class="easyui-validatebox" data-options="required:true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 col-md-2">菜单顺序号：</label>
				<div class="col-md-9 col-sm-9">
					<input class="form-control" name="menuSequ" value="${menu.menuSequ }" class="easyui-validatebox" data-options="required:true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2 col-md-2">菜单URL：</label>
				<div class="col-md-9 col-sm-9">
					<input class="form-control" name="menuUrl" value="${menu.menuUrl }" class="easyui-validatebox" data-options="required:true"/>
				</div>
			</div>
		</form>
	</div>
</body>
</html>