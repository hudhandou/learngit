<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
	
		<label class="control-label label-default col-xs-12 ">
			<h4>当前位置：常用操作 - 修改密码</h4>
		</label>
		<form action="javascript:doSub()" method="post" class="form-horizontal col-xs-12" name="changePass" style="margin-top: 25px;">
			<div class="container ">
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2">用户ID:</label>
					<div class=" col-sm-9">
						<input class="form-control " disabled="disabled" value="${sessionScope.user.userName}"  />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2">用户名:</label>
					<div class=" col-sm-9">
						<input class="form-control " disabled="disabled" value="${sessionScope.user.nickName}"  />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2">原密码:</label>
					<div class=" col-sm-9">
						<input class="form-control" name="oldPass" id="oldPass" type="password" placeholder="请输入原密码" required/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2">新密码:</label>
					<div class=" col-sm-9">
						<input class="form-control" type="password" id="newPass" name="newPass" required placeholder="请输入新密码" required/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2">确认新密码:</label>
					<div class=" col-sm-9">
						<input class="form-control" id="newPass2" name="newPass2" type="password" required placeholder="请确认新密码" required />
					</div>
				</div>
				<div class="form-group text-center">
					<button type="submit" class="btn btn-default" >提交</button>
					<button type="reset" class="btn btn-primary">重置</button>
				</div>
			</div>
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog">
			      <div class="modal-content">
			         <div class="modal-header" style="background-color: rgba(0,0,0,0.1)">
			            <button type="button" class="close" data-dismiss="modal" 
			               aria-hidden="true">×
			            </button>
			            <h4 class="modal-title" id="myModalLabel">
			              	
			            </h4>
			         </div>
			         <div class="modal-body text-center" id="modalBody">
			           	
			         </div>
			         <div class="modal-footer" id="modalFooter">
			            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			         </div>
			      </div>
			   </div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	
		document.getElementById("oldPass").oninput = function(){
			document.getElementById("oldPass").setCustomValidity("");
		};
		document.getElementById("newPass").oninput = function(){
			document.getElementById("newPass").setCustomValidity("");
		};
		document.getElementById("newPass2").oninput = function(){
			document.getElementById("newPass2").setCustomValidity("");
		};
		document.getElementById("oldPass").oninvalid = function(){
			document.getElementById("oldPass").setCustomValidity("密码不能为空!");
		};
		document.getElementById("newPass").oninvalid = function(){
			document.getElementById("newPass").setCustomValidity("密码不能为空!");
		};
		document.getElementById("newPass2").oninvalid = function(){
			document.getElementById("newPass2").setCustomValidity("密码不能为空!");
		};
		//对输入内容进行验证
		function doSub(){
			var oldPass = $("input[name='oldPass']").val();
			var newPass = $("#newPass").val();
			var newPass2 = $("#newPass2").val();
			//首先验证两次输入的新密码是否一致
			if(newPass == newPass2 && newPass!=null){
				//若两次输入的新密码一致，则再判断输入的原密码是否一致
				if("${sessionScope.stu}"!=""){
					if(oldPass != "${sessionScope.user.userPass}"){
						$("#modalBody").html("输入的密码不正确！");
						$("#myModal").modal('show');
						$("input[name='oldPass']").val("");
						return;
					}
				}else{
					if(oldPass != "${sessionScope.user.userPass}"){
						$("#modalBody").html("输入的密码不正确！");
						$("#myModal").modal('show');
						$("input[name='oldPass']").val("");
						return;
					}
				}
				
			}else{
				$("#modalBody").html("两次输入的新密码必须相同！");
				$("#myModal").modal('show');
				$("#newPass").val("");
				$("#newPass2").val("");
				return;
			}
			$("#modalBody").html("确认修改？");
			$("#modalFooter").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='tijiao()'>确定</button><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
			$("#myModal").modal('show');
		};
		
		function tijiao(){
			var data="newPass="+$("#newPass").val();
			$.post("<%=ctx%>/user/saveNewPass.action",data,function(){
				$("#modalBody").html("密码修改成功！");
				$("#modalFooter").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='goIndex()'>确定</button>");
				$("#myModal").modal('show');
			},"text");
		}
		
		function goIndex(){
			parent.location.href = "<%=ctx%>/index.jsp";
		}
	</script>
</html>
