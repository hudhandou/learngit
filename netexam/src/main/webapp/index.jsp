<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<style type="text/css">
			html,body{
				height: 100%;
				display: flex;
				width: 100%;
				background-image: url(static/image/login/1349109563392088389.jpg);
			}
			.login{
			
				height: auto;
				margin: auto;
			}
			.tab-content{
				margin-top: 15px;
				
			}
			.form-control{
				background-color: rgba(0,0,30,0.2);
				color: white;
			}
			.iframe{
			
			width:100%;
			height:360px;
			border:0;
			}
			
		</style>
	</head>
	<body>
		<div class="login col-xs-12 col-md-5">
			<div class="  panel " style="background-color: rgba(0,0,30,0.2);" >
				<div class="panel-heading">
					<div class="panel-title text-center" style="color: white;font-size: x-large;">
						系统登录
					</div>
				</div>
				<div class="tab-content">
					<form class="form-horizontal" name="userLoginForm" action="<%=ctx %>/login/login.action" onsubmit="jizhumima()"  method="post">
						<div class="form-group   ">
							<div class="input-group col-xs-offset-1 col-xs-10">
								<label class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</label>
								<input type="text" id="userName" name="userName" value="" class="form-control" required placeholder="请输入用户账号" />
							</div>
						</div>
						<div class="form-group">
							<div class="input-group col-xs-offset-1 col-xs-10">
								<label class="input-group-addon">
									<span class="glyphicon glyphicon-pencil"></span>
								</label>
								<input type="password" id="userPass" name="userPass" value="" required placeholder="请输入用户密码" class="form-control"  />
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-offset-1 col-sm-3">
								<div class="checkbox">
									<label>
										<input type="checkbox" id="jizhu" value="1"> 记住密码
									</label>
								</div>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="submit" class="btn btn-default" >登录</button>
							<button type="reset" class="btn btn-primary" >清空</button>
							<button type="button" class="btn btn-warning" onclick="zhuce()">注册</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	$(function(){
		var arr=["1349109563392088389.jpg"
				,"1375005261331698523.jpg"
				,"1467047578716070913.jpg"
				,"161848111708759040.jpg"
				,"2020427382929234967.jpg"
				,"2029716057160704972.jpg"
				,"2065744854179650341.jpg"
				,"26177172851989173.jpg"
				,"2874422462269319179.jpg"
				,"3093409994068022529.jpg"
				,"3857051605966499771.jpg"
				,"4856287773289047193.jpg"
				,"6597261081681170076.jpg"
				,"6598134093912150825.jpg"
				,"711005791271265196.jpg"
				,"729583139734395697.jpg"];
		$("body").css("background-image","url(static/image/login/"+arr[parseInt(Math.random()*arr.length)]+")");
		var username = zg.getCookie("username");
		var password = zg.getCookie("password");
		var checked = zg.getCookie("checked");
		$("input[name='userName']").val(username);
		$("input[name='userPass']").val(password);
		document.getElementById("jizhu").checked = checked;
	});
	//登录验证
	document.getElementById("userName").oninput = function(){
		document.getElementById("userName").setCustomValidity("");
	};
	document.getElementById("userPass").oninput = function(){
		document.getElementById("userPass").setCustomValidity("");
	};
	document.getElementById("userName").oninvalid = function(){
		document.getElementById("userName").setCustomValidity("登录名不可为空!");
	};
	document.getElementById("userPass").oninvalid = function(){
		document.getElementById("userPass").setCustomValidity("登录密码不可为空!");
	};
	//记住密码
	function jizhumima(){
		var check = document.getElementById("jizhu").checked;
		if(check){
			//记住密码
			zg.setCookie("username",$("input[name='userName']").val(),1000*60*60*24);
			zg.setCookie("password",$("input[name='userPass']").val());
			zg.setCookie("checked",check);
		}else{
			zg.delCookie("username");
			zg.delCookie("password");
			zg.delCookie("checked");
		}
	}
	function zhuce(){
		bootbox.dialog({  
			title: "学员注册",  
			message: "<iframe src='<%=ctx%>/student/zhuce.action' class=\"iframe\"></iframe>",  
			size: "large",
			buttons: {  
				Cancel: {  
					label: "取消",  
					className: "btn-default",  
					callback: function () {  
						//取消 
					}  
				}  
 
			}  
		}); 
	}
	//关闭注册页**
	var zhucedialog = null;
	function zhuce(){	
		zhucedialog=bootbox.dialog({  
			title: "学员注册",  
			message: "<iframe src='<%=ctx%>/student/zhuce.action' class=\"iframe\"></iframe>",  
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
	//关闭模态框
	function closeZhuceDialog(){
		zhucedialog.modal("hide");	
	}

</script>