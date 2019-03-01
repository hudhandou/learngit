<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<label class="control-label label-default col-xs-12 " style="margin-bottom: 30px;">
			<h4>当前位置：用户维护 － <c:if test="${requestScope.user==null}">增加</c:if><c:if test="${requestScope.user!=null}">修改</c:if>用户</h4>
		</label>
		<div class="container">
			<hr>
			<form class="form-horizontal text-center" name="infoForm" action="javascript:doSub()" method="post">
				<input type="hidden" name="userId" value="${requestScope.user.userId}">	
				<input type="hidden" name="userState" value="${requestScope.user.userState}">	
				<div class="container ">
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">登录名称:</label>
						<div class="col-md-9 col-sm-9">
							<input class="form-control" name="userName" value="${requestScope.user.userName}" placeholder="请输入登录名称" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">显示名称:</label>
						<div class="col-md-9 col-sm-9">
							<input class="form-control" name="nickName" value="${requestScope.user.nickName}" placeholder="请输入显示名称" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">登录密码:</label>
						<div class="col-md-9 col-sm-9">
							<input class="form-control" type="password" name="userPass" placeholder="请输入登录密码" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">验证密码:</label>
						<div class="col-md-9 col-sm-9">
							<input class="form-control" type="password" name="userPass1" placeholder="请输入验证密码"  required/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">用户信息:</label>
						<div class="col-md-9 col-sm-9">
							<textarea class="form-control" name="userInfo" rows="3" placeholder="请输入用户信息" >${requestScope.user.userInfo}</textarea>
						</div>
					</div>
		
					<div class="form-group">
						<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
							
						</div>
						<div class=" col-lg-6 col-md-6 col-sm-8 col-xs-8" >
							<div class="col-md-6 col-sm-6">
								<input class="form-control btn-primary" type="submit" value="提交" />
							</div>
							<div class="col-md-6 col-sm-6">
								<input class="form-control btn-primary" type="button" onclick="goBack()" value="返回" />
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
							
						</div>
					</div>
				</div>
			</form>
				<hr/>
		</div>
		
		<!-- 模态框 -->
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
		         <div class="modal-footer" id="modalFooter2">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		         </div>
		      </div>
		   </div>
		</div>
	</body>
</html>
<script type="text/javascript">
	
	
	function doSub(){
		var p1 = $("input[name='userPass']").val();
		var p2 = $("input[name='userPass1']").val();
		if(p1!=p2){
			$("#myModalLabel").html("提交失败");
			$("#modalBody").html("输入密码与验证密码不一致!");
			
		}else{
			$("#myModalLabel").html("确认提交");
			$("#modalBody").html("是否确定提交?");
			$("#modalFooter2").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='tijiao()'>提交</button><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
		}
		$("#myModal").modal('show');
	}
	
	function tijiao(){
		$("form[name='infoForm']").attr("action","<%=ctx%>/user/save.action");
		$("form").submit();
	}
	
	function goBack(){
		history.go(-1);
	}
</script>