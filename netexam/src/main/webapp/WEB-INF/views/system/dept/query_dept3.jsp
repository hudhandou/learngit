<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<% String ctx = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.5-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap-3.3.5-dist/js/jquery-2.2.4.min .js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap-3.3.5-dist/js/bootstrap.min.js" ></script>
		<style type="text/css">
			.tree {
				min-height: 20px;
				padding: 19px;
				margin-bottom: 20px;
				background-color: #fbfbfb;
				border: 1px solid #999;
				-webkit-border-radius: 4px;
				-moz-border-radius: 4px;
				border-radius: 4px;
				-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
				-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05);
				box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.05)
			}
			
			.tree li {
				list-style-type: none;
				margin: 0;
				padding: 10px 5px 0 5px;
				position: relative
			}
			
			.tree li::before,.tree li::after {
				content: '';
				left: -20px;
				position: absolute;
				right: auto
			}
			
			.tree li::before {
				border-left: 1px solid #999;
				bottom: 50px;
				height: 100%;
				top: 0;
				width: 1px
			}
			
			.tree li::after {
				border-top: 1px solid #999;
				height: 20px;
				top: 25px;
				width: 25px
			}
			
			.tree li span {
				-moz-border-radius: 5px;
				-webkit-border-radius: 5px;
				border: 1px solid #999;
				border-radius: 5px;
				display: inline-block;
				padding: 3px 8px;
				text-decoration: none
			}
			
			.tree li.parent_li>span {
				cursor: pointer
			}
			
			.tree>ul>li::before,.tree>ul>li::after {
				border: 0
			}
			
			.tree li:last-child::before {
				height: 30px
			}
			
			.tree li.parent_li>span:hover,.tree li.parent_li>span:hover+ul li span {
				background: #eee;
				border: 1px solid #94a0b4;
				color: #000
			}
		</style>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);visibility: hidden;">
		<label class="control-label label-default col-xs-12 ">
			<h4>当前位置：系统维护 - 部门维护</h4>
		</label>
		<div class="tree well">
				<ul>
					<li>
						<span><i class="fa fa-minus-circle"></i> 部门列表</span>
						<button type="button" onclick="addDept('0')" class="btn btn-primary">增加</button>
						<ul>
						<c:forEach  items="${topList }" var="m1">
							<li>
								<span>${m1.deptName }</span>
								<button type="button" onclick="addDept('${m1.deptId}')" class="btn btn-primary">增加</button>
								<c:if test="${m1.deptList=='[]'}">
									<button type="button" onclick="deleteDept('${m1.deptId}')" class="btn btn-danger">删除</button>
								</c:if>
								<button type="button" onclick="updateDept('${m1.deptId}')" class="btn btn-info">修改</button>
								<ul>
								<c:forEach  items="${m1.deptList }" var="m2">
									<li>
										<span>${m2.deptName }</span>
										<button type="button" onclick="addDept('${m2.deptId}')" class="btn btn-primary">增加</button>
										<c:if test="${m2.deptList=='[]'}">
											<button type="button" onclick="deleteDept('${m2.deptId}')" class="btn btn-danger">删除</button>
										</c:if>
										<button type="button" onclick="updateDept('${m2.deptId}')" class="btn btn-info">修改</button>
	                    			</li>
								</c:forEach>
								</ul>
							</li>
						</c:forEach>
						</ul>
					</li>
				</ul>
			</div>
			<!-- 模态框 增加或修改-->
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
			           	<div class="container">
							<form class="form-horizontal text-center" name="pageForm" action="<%=ctx %>/dept/save.action" method="post">
								<input type="hidden" name="deptId" >	
								<input type="hidden" name="deptState" >	
								<div class="container ">
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">上级部门:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="parDeptName"   readonly="readonly"/>
											<input class="form-control" name="deptPar"  type="hidden" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">部门名称:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="deptName"  placeholder="请输入部门名称"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">部门代号:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="deptCode"  placeholder="请输入部门代号"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">部门序号:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="deptSequ"  placeholder="请输入部门序号"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">部门电话:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="deptPhone"  placeholder="请输入部门电话"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">部门信息:</label>
										<div class="col-md-4 col-sm-4">
											<textarea class="form-control" rows="3" name="deptInfo" placeholder="请输入部门信息"></textarea>
										</div>
									</div>
								</div>
							</form>
						</div>
			         </div>
			         <div class="modal-footer" id="modalFooter">
			            <button type="button" class="btn btn-primary" onclick="save()" data-dismiss="modal">保存</button>
			            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			         </div>
			      </div>
			   </div>
			</div>
			
			<!-- 模态框 删除-->
			<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog">
			      <div class="modal-content">
			         <div class="modal-header" style="background-color: rgba(0,0,0,0.1)">
			            <button type="button" class="close" data-dismiss="modal" 
			               aria-hidden="true">×
			            </button>
			            <h4 class="modal-title" id="myModalLabel2">
			              	请谨慎操作!
			            </h4>
			         </div>
			         <div class="modal-body text-center" id="modalBody2">
			           	确定要删除?
			         </div>
			         <div class="modal-footer" id="modalFooter2">
			            <button type="button" class="btn btn-primary" name="delBt" onclick="del(this.id)" data-dismiss="modal">删除</button>
			            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			         </div>
			      </div>
			   </div>
			</div>
	</body>
	<script type="text/javascript">
		
		function addDept(deptId){
			$("input[name='deptId']").val("");
			$("input[name='deptState']").val("");
			$("input[name='deptName']").val("");
			$("input[name='deptCode']").val("");
			$("input[name='deptSequ']").val("");
			$("input[name='deptPhone']").val("");
			$("textarea[name='deptInfo']").html("");
			if(deptId!="0"){
				$.post("<%=ctx%>/dept/addpage.action?id="+deptId,function(msg){
					$("input[name='parDeptName']").val(msg.deptName);
					$("input[name='deptPar']").val(msg.deptId);
					$("#myModalLabel").html("增加菜单");
					$("#myModal").modal('show');
				},"json");
			}else{
				$("input[name='parDeptName']").val("部门列表");
				$("input[name='deptPar']").val("0");
				$("#myModalLabel").html("增加菜单");
				$("#myModal").modal('show');
			}
		}
		
		function updateDept(deptId){
			$.post("<%=ctx%>/dept/editpage.action?id="+deptId,function(msg){	
				if(msg[1]!=null){
					$("input[name='parDeptName']").val(msg[1].deptName);
					$("input[name='deptPar']").val(msg[1].deptId);
				}else{
					$("input[name='parDeptName']").val("部门列表");
					$("input[name='deptPar']").val("0");
				}
				$("input[name='deptId']").val(msg[0].deptId);
				$("input[name='deptState']").val(msg[0].deptState);
				$("input[name='deptCode']").val(msg[0].deptCode);
				$("input[name='deptName']").val(msg[0].deptName);
				$("input[name='deptSequ']").val(msg[0].deptSequ);
				$("input[name='deptPhone']").val(msg[0].deptPhone);
				$("textarea[name='deptInfo']").html(msg[0].deptInfo);
				$("#myModalLabel").html("修改部门");
				$("#myModal").modal('show');
			},"json");
		}
		
		function deleteDept(deptId){
			$("button[name='delBt']").attr("id",deptId);
			$("#myModal2").modal('show');
		}
		
		function del(deptId){
			window.location.href = "<%=ctx%>/dept/delete.action?id="+deptId;
		}
		
		function save(){
			$("form[name='pageForm']").submit();
		}
		
		//$(function () {
		//如果有子菜单，隐藏其子菜单
		var lis = $('.tree li:has(ul)');
		for(var i=0;i<lis.length;i++){
			var parli = $(lis[i]);
			var children = parli.find('>ul>li');
			if(children.is(":visible")){
				$(lis[i]).find('>span').removeClass('glyphicon-minus').addClass('glyphicon-plus');
				children.hide('fast');
			}
			//如果有子菜单，在本身span上增加点击事件
			parli.find('>span').on('click',function(){
				var children = $(this).parent('li').find('>ul>li');
				if(children.is(":visible")){
					$(this).removeClass('glyphicon-minus').addClass('glyphicon-plus');
					children.hide('fast');
				}else{
					$(this).removeClass('glyphicon-plus').addClass('glyphicon-minus');
					children.show('fast');
				}
			});
			//如果有子菜单，改变鼠标样式
			parli.find('>span').on('mouseover',function(){
				$(this).css("cursor","pointer");
			});
		}
		//});
		$("body").css("visibility","visible");
	</script>
</html>
