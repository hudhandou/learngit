<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.turingedu.com/tag/page" prefix="page" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/js/bootstrap-3.3.5-dist/css/bootstrap.min.css" />
    <link href="<%=request.getContextPath()%>/static/js/bootstrap-3.3.5-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-3.3.5-dist/js/jquery-2.2.4.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-3.3.5-dist/js/bootstrap.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-3.3.5-dist/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/bootstrap-3.3.5-dist/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <%-- 图片预览 --%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/preview/preview.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/bootstrap-3.3.5-dist/js/bootbox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/cookie.js"></script>
    <%-- jquery-easyui-1.3.6 --%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/js/noty/noty_theme_default.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/noty/jquery.mynoty.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ChinesePY.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/localization/messages_zh.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/additional-methods-zhaogang.js?v=1"></script>
</head>
<%
	String ctx = request.getContextPath();
%>
