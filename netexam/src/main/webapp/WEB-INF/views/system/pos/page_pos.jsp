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
			<h4>当前位置：职务维护 － <c:if test="${requestScope.pos==null}">增加</c:if><c:if test="${requestScope.pos!=null}">修改</c:if>职务</h4>
		</label>
		<div class="container">
			<hr>
			<form class="form-horizontal text-center" style="position:relative;"  name="infoForm" action="javascript:doSub()" method="post" enctype="multipart/form-data">
				<input type="hidden" name="spId" value="${requestScope.pos.spId}">	
				<div class="container" >
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">职务名称:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="spName" value="${requestScope.pos.spName}" placeholder="请输入职务名称" />
						</div>	
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">所属部门:</label>
						<div class="col-md-4 col-sm-4">
							<select name="deptId" class="form-control">
								<option value="">===请选择===</option>
								<c:forEach items="${deptList}" var="dept">
									<option value="${dept.deptId }"  ${dept.deptId==requestScope.pos.deptId?"selected":"" }>${dept.deptName }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">职务信息:</label>
						<div class="col-md-6 col-sm-6">
							<textarea class="form-control" name="spInfo" rows="3" placeholder="请输入职务信息" >${requestScope.pos.spInfo}</textarea>
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
		$("#myModalLabel").html("确认提交");
		$("#modalBody").html("是否确定提交?");
		$("#modalFooter2").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='tijiao()'>提交</button><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
		$("#myModal").modal('show');
	}
	
	function tijiao(){
		$("form[name='infoForm']").attr("action","<%=ctx%>/pos/save.action");
		$("form").submit();
	}
	
	function goBack(){
		history.go(-1);
	}
</script>