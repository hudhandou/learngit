<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
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
		         <div class="modal-footer" id="modalFooter">
		         </div>
		      </div>
		   </div>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			if("${msg}"=="relogin"){
				$.post("<%=ctx%>/login/layout.action",function(){
					$("#myModalLabel").html("重新登录");
					$("#modalBody").html("您还没有登录或登录已超时，请重新登录!");
					$("#modalFooter").html("<button type='button' class='btn btn-primary' onclick='relogin()' data-dismiss='modal'>确定</button>");
					$("#myModal").modal('show');
				},"text");
			}
		});
		
		function relogin(){
			parent.location.href="<%=ctx%>/index.jsp";
		}
	</script>
</html>
