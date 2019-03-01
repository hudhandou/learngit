<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="margin-top: 10px;width: 95%">
		<form id="sys_dept_addForm" method="post" class="form form-horizontal">
			<div class="form-group">
				<label class="control-label col-md-2">上级名称:</label>
				<div class="col-md-10 text-left">
					<label class="control-label">${parentName }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">部门名称:</label>
				<div class="col-md-4">
					<input name="deptName" class="easyui-validatebox form-control" data-options="required:true" onblur="changePinyinEdit(this.value)" tabindex="1">
				</div>
				<label class="control-label col-md-2">部门编号:</label>
				<div class="col-md-4">
					<input name="deptCode" class="form-control" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">部门电话:</label>
				<div class="col-md-4">
					<input name="deptPhone" class="form-control" tabindex="2">
				</div>
				<label class="control-label col-md-2">部门顺序:</label>
				<div class="col-md-4">
					<input name="deptSequ" class="form-control" tabindex="3">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">备注:</label>
				<div class="col-md-10">
					<textarea name="deptInfo" tabindex="4" rows="4" cols="50" class="form-control"></textarea>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	//根据汉字生成拼音
	function changePinyinEdit(data){
	    var pingyin = Pinyin.GetJP(data);
	    $("input[name='deptCode']").val(pingyin.toUpperCase());
	}
	</script>
</body>
</html>