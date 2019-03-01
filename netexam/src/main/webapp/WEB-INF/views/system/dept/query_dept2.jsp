<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<hr>
		<div class="row" style="margin-left: 10px;">
			<c:forEach items="${topList }" var="t">
			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-heading">${t.deptName }</div>
					<div class="panel-body">
						
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<script type="text/javascript">
			
		</script>
	</body>
</html>
