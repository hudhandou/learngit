<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<style type="text/css">
			html,body{
				width: 100%;
				height: 94%;
			}
			@media only screen and (min-width:768px ) {
				.navbar-side{
					height: 100%;
				}
				.navbar-side > .row{
					height: 100%;
				}
				.navbar-side > .row > .nav-pills{
					height: 100%;
				}
			}
			.nav-inside{
				padding-left: 15px;
 				/*background-color:white;*/
 				background:linear-gradient(white,#ebebeb,white);
			}
			.navbar-side > .row > .nav-pills >li a{
				color: blue;
			}
			.navbar-side > .row > .nav-pills > li > ul >li{
				transition: padding 0.3s;
			}
			.navbar-side > .row > .nav-pills li > ul >li:hover{
				padding-left: 10px;
			}
			#main-title{
				animation-name: xuanzhuan;
				animation-duration: 2s;
				animation-iteration-count: infinite;
				color: white;
			}
			@keyframes xuanzhuan{
				
				from{transform:perspective(100px) rotateY(0deg);}
				to{transform:perspective(100px) rotateY(360deg);}
			}
			.oa-content{
				padding: 0px;
			}
			iframe{
				margin: 0px;
				width: 100%;
				height: 100%;
				border:0;
				background:linear-gradient(white,#ebebeb,white);
			}
			#leftUl{
				/*background-color: white;*/
				color: white;
				background:linear-gradient(white,#ebebeb,white);
			}
			.topfont{
				color: white;
			}
			.leftnav{
				border: 1px solid lightgrey;
				/*圆角属性*/
				border-radius: 10px ;
				/*阴影属性 box-shadow:(inset) 水平偏移量   垂直偏移量  阴影虚化的直径 阴影的颜色 */
				box-shadow: 5px 5px 10px  gray;
			}
		</style>
	</head>
	<body>
		
		<nav class="nav " style="background-color:#34A6E8;color:white;">
			<div class="nav navbar-header">
				<a class="navbar-brand" id="main-title" href="#">Turing</a>
				<button class="navbar-toggle" data-toggle="collapse" data-target=".daohang">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse daohang" id="daohang" >
				
				<div class="navbar-form navbar-left" >
					<div class="form-group"   >
						<ul class="nav navbar-nav">
							<c:forEach items="${sessionScope.topList }" var="topMenu">
								<li>
									<a class="topfont" href="javascript:showMenu('${topMenu.menuId }')">
										${topMenu.menuName}
									</a>
								</li>
							</c:forEach>
							<li >
								<a href="#" class="text-muted" data-toggle="dropdown">
									<span class="glyphicon glyphicon-user topfont" >：${sessionScope.user.userName}</span>
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a target="right" href="<%=ctx %>/user/topasswordjsp.action">修改密码</a></li>
									<li class="divider"></li>
									<li><a href="#exit-modal" data-toggle="modal">退出登录</a></li>
								</ul>
							</li>
							<li  style="border: 0px solid red;margin-left: 250px;" >
								<a href="#" id="viewdatetime" style="color: white;background-color:#34A6E8 ">2018年4月20日 星期五 8:29:32</a>
							</li>
						</ul>
					</div>
				</div>
				
			</div>
		</nav>
		<div class="col-sm-3 col-md-2  navbar-side daohang leftnav" >
			<div class="row" style="overflow: auto;">
				<ul class="nav nav-pills nav-stacked" id="leftUl" >
				<c:forEach items="${oneList }" var="leftMenu" varStatus="i">
					<c:if test="${leftMenu.menuIslink=='2' && leftMenu.menuPare==pid}">
						<li>
							<a  href="#" data-toggle="collapse" data-target="#menu${i.count}" ><span class="glyphicon glyphicon-plus"></span>${leftMenu.menuName}</a>
							<ul class="collapse nav nav-pills nav-stacked nav-inside" id="menu${i.count}">
								<c:forEach items="${leftMenu.menuList }" var="leftMenu1" >
									<c:if test="${leftMenu1.menuIslink=='3' && leftMenu1.menuPare==leftMenu.menuId}">
										<li><a href="<%=ctx %>/${leftMenu1.menuUrl}" target="right">${leftMenu1.menuName}</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:if>
				</c:forEach>	
				</ul>
			</div>
		</div>
		<div class="col-sm-9 col-md-10 oa-content" style="height: 100%;">
				<iframe style="" name="right" type="text/html" data="<%=ctx %>/default.html"></iframe>
		</div>
		<div class="modal fade" id="exit-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="background-color: rgba(0,0,0,0.1)">
						<div class="modal-title">
							<span class="glyphicon glyphicon-log-out">退出登录</span>
							<span class="close" data-dismiss="modal">&times;</span>
						</div>
					</div>
					<div class="modal-body">
						<h1>确认退出吗？</h1>
					</div>
					<div class="modal-footer" >
						<button onclick="layout()" class="btn btn-primary">退出</button>
						<button class="btn btn-default" data-dismiss="modal">取消</button>
					</div>	
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		//退出登录
		function layout(){
			$.post("<%=ctx%>/login/layout.action",function(){
				location.href="<%=ctx%>/index.jsp";
			},"text");
		}
		
		window.onload=function(){
			if($(document).width()<=768){
				$(".navbar-side").addClass("collapse");
			}else{
				$(".navbar-side").removeClass("collapse");
			}
		};
		$("a").popover();
		
		function showMenu(pid){
			var url = "<%=ctx%>/login/menuLeft.action";
			data = "pid="+pid;
			$.post(url,data,function(msg){
				var ul = $("#leftUl");
				var info = "";
				for(i in msg){
					var leftMenu = msg[i];
					if(leftMenu.menuIslink=='2' && leftMenu.menuPare==pid){
						info+="<li><a  href='#' data-toggle='collapse' data-target='#menu"+(i+1)+"' ><span class=\"glyphicon glyphicon-plus\"></span>"+leftMenu.menuName+"</a><ul class='collapse nav nav-pills nav-stacked nav-inside' id='menu"+(i+1)+"'>";
						for(j in leftMenu.menuList){
							var leftMenu1 = leftMenu.menuList[j];
							info+="<li><a href='<%=ctx %>/"+leftMenu1.menuUrl+"' target='right'>"+leftMenu1.menuName+"</a></li>";
						}
						info+="</ul></li>";
					}
				}
				ul.html(info);
			},"json");
		}
		$(function(){
			setInterval("viewdatetime()",1000);
			
		});
		function viewdatetime(){
			var d  = new Date();
			var year = d.getFullYear();
			var month = d.getMonth()+ 1;
			var date = d.getDate();
			var dddd = d.getDay();
			var xq = "一";
			switch(dddd){
				case 0:xq="日";break;
				case 1:xq="一";break;
				case 2:xq="二";break;
				case 3:xq="三";break;
				case 4:xq="四";break;
				case 5:xq="五";break;
				case 6:xq="六";break;
			}
			var hour = d.getHours();
			var minute = d.getMinutes();
			var second = d.getSeconds();
			$("#viewdatetime").html(year+"年"+month+"月"+date+"日 星期"+xq+" "+hour+":"+minute+":"+second);
		}
	</script>
</html>
