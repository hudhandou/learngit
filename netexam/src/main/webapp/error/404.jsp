<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
<title>404</title>
<%@ include file="style.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
<div class="not-found">
	<div class="notfound-top">
		<h1>404</h1>
	</div>
	<div class="content">
		<img src="${pageContext.request.contextPath }/static/image/green.png" alt="" title="">
		<h3>页面没找到，请联系管理员</h3>
		<ul class="social-icon">
			<li><a class="fa" href="#"> </a></li>
			<li><a class="tw" href="#"> </a></li>
			<li><a class="g" href="#"> </a></li>
		</ul>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>