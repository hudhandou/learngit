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
			<h4>当前位置：人员维护 － <c:if test="${requestScope.person.userId==null}">关联</c:if><c:if test="${requestScope.person.userId!=null}">修改</c:if>用户</h4>
		</label>
		<div class="container">
			<hr />
			
			<form class="form-horizontal text-center" action="javascript:saveUser()" method="post">
				<div class="container ">
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">人员姓名:</label>
						<div class="col-md-4 col-sm-4">
							<label class="control-label">${person.ppName}</label>
						</div>
						
						<label class="control-label col-sm-2 col-md-2">人员职务:</label>
						<div class="col-md-4 col-sm-4">
							<label class="control-label">${person.spName}</label>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">当前用户:</label>
						<div class="col-md-4 col-sm-4">
							<select class="form-control" name="userId">
								<option value="">===请选择===</option>
								<c:if test="${person.userId!=null }">
									<option value="${person.userId }" selected="selected">${person.nickName }</option>
								</c:if>
								<c:forEach items="${userList}" var="user">
									<option value="${user.userId }">${user.nickName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<c:if test="${person.userId!=null}">
							<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
							
							</div>
							<div class=" col-lg-6 col-md-6 col-sm-8 col-xs-8" >
								<div class="col-md-6 col-sm-6">
									<input class="form-control btn-primary" type="submit" value="保存" />
								</div>
								<div class="col-md-6 col-sm-6">
									<input class="form-control btn-primary" type="button" onclick="disconnect()" value="取消关联" />
								</div>
							</div>
							<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
								
							</div>
						</c:if>
						
						<c:if test="${person.userId==null}">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
							
							</div>
							<div class=" col-lg-3 col-md-3 col-sm-4 col-xs-4" >
									<input class="form-control btn-primary" type="submit" value="保存" />
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
								
							</div>
						</c:if>
					</div>
				</div>
			</form>
		</div>
		<hr/>
		<div class="container">
			<form class="form-horizontal text-center" name="infoForm" action="javascript:doSub()" method="post">
			<input type="hidden" name="ppId" value="${person.ppId}">
				<div class="container ">
					<div class="form-group">
						<label class="control-label" style="font-size: 20px">创建并关联新用户</label>
					</div>
					<hr/>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">登录名称:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="userName" placeholder="请输入登录名称" />
						</div>
						<label class="control-label col-sm-2 col-md-2">显示名称:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="nickName" placeholder="请输入显示名称" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">登录密码:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" type="password" name="userPass" placeholder="请输入登录密码" required/>
						</div>
						<label class="control-label col-sm-2 col-md-2">验证密码:</label>
						<div class="col-md-4 col-sm-4">
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
			$("#modalFooter2").html("<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
		}else{
			$("#myModalLabel").html("确认提交");
			$("#modalBody").html("是否确定提交?");
			$("#modalFooter2").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='tijiao()'>提交</button><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
		}
		$("#myModal").modal('show');
	}
	
	function tijiao(){
		$("form[name='infoForm']").attr("action","<%=ctx%>/person/saveNewUser.action");
		$("form").submit();
	}
	
	function goBack(){
		history.go(-1);
	}
	
	function disconnect(){
		$("#myModalLabel").html("确认取消关联");
		$("#modalBody").html("是否取消关联?");
		$("#modalFooter2").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='qxgl()'>提交</button><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
		$("#myModal").modal('show');
	}
	
	function qxgl(){
		window.location.href = "<%=ctx%>/person/disconnect.action?ppId=${person.ppId}";
	}
	
	function saveUser(){
		var userId = $("select[name='userId']").val();
		if(userId==""){
			$("#myModalLabel").html("保存失败");
			$("#modalBody").html("尚未选择关联用户!");
			$("#modalFooter2").html("<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
			$("#myModal").modal('show');
		}else{
			$("#myModalLabel").html("确认保存");
			$("#modalBody").html("确定保存?");
			$("#modalFooter2").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='saveuser()'>保存</button><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
			$("#myModal").modal('show');
		}
	}
	
	function saveuser(){
		var userId = $("select[name='userId']").val();
		window.location.href = "<%=ctx%>/person/saveUser.action?ppId=${person.ppId}&userId="+userId;
	}
</script>