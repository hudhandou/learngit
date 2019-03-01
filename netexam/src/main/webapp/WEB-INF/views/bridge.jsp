<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<html>
  <head>
  </head>

  <body>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" 
		               aria-hidden="true">×
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		              	登录失败
		            </h4>
		         </div>
		         <div class="modal-body text-center">
		           	${message }
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-primary" 
		               data-dismiss="modal" onclick="goIndex()">关闭
		            </button>
		         </div>
		      </div>
		   </div>
		</div>
  </body>
</html>
<script type="text/javascript">
	if("${loginState}"=="ok"){
		window.location.href = "<%=ctx%>/menu/main.action";
	}else{
		$("#myModal").modal('show');
	}
	function goIndex(){
		window.location.href="<%=ctx%>/index.jsp";
	}
	
</script>