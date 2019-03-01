<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
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
			<h4>当前位置：系统维护 - 菜单维护</h4>
		</label>
		<div class="tree well">
				<ul>
					<li>
						<span>功能菜单</span>
						<button type="button" onclick="addMenu('0')" class="btn btn-primary">增加</button>
						<ul>
						<c:forEach  items="${topList}" var="m1">
							<li>
								<span>${m1.menuName}</span>
								<button type="button" onclick="addMenu('${m1.menuId}')" class="btn btn-primary">增加</button>
								<c:if test="${m1.menuList=='[]'}">
									<button type="button" onclick="deleteMenu('${m1.menuId}')" class="btn btn-danger">删除</button>
								</c:if>
								<button type="button" onclick="updateMenu('${m1.menuId}')" class="btn btn-info">修改</button>
								<ul>
								<c:forEach  items="${m1.menuList}" var="m2">
									<li>
										<span>${m2.menuName}</span>
										<button type="button" onclick="addMenu('${m2.menuId}')" class="btn btn-primary">增加</button>
										<c:if test="${m2.menuList=='[]'}">
											<button type="button" onclick="deleteMenu('${m2.menuId}')" class="btn btn-danger">删除</button>
										</c:if>
										<button type="button" onclick="updateMenu('${m2.menuId}')" class="btn btn-info">修改</button>
	                    				<ul>
                    					<c:forEach  items="${m2.menuList }" var="m3">
											<li>
												<span>${m3.menuName}</span>
												<button type="button" onclick="deleteMenu('${m3.menuId}')" class="btn btn-danger">删除</button>
												<button type="button" onclick="updateMenu('${m3.menuId}')" class="btn btn-info">修改</button>
											</li>
										</c:forEach>
	                    				</ul>
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
							<form class="form-horizontal text-center" name="pageForm" action="<%=ctx %>/menu/save.action" method="post">
								<input type="hidden" name="menuId" >	
								<input type="hidden" name="menuState" >	
								<div class="container ">
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">上级菜单:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="parMenuName"   readonly="readonly"/>
											<input class="form-control" name="menuPare"  type="hidden" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">菜单级别:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="menuIslink"  readonly="readonly"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">菜单名称:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="menuName"  placeholder="请输入菜单名称"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">菜单序号:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="menuSequ"  placeholder="请输入菜单序号"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">菜单URL:</label>
										<div class="col-md-4 col-sm-4">
											<input class="form-control" name="menuUrl"  placeholder="请输入菜单URL"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2 col-md-2">菜单信息:</label>
										<div class="col-md-4 col-sm-4">
											<textarea class="form-control" rows="3" name="menuInfo" placeholder="请输入菜单信息"></textarea>
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
		
		function addMenu(menuId){
			$("input[name='menuId']").val("");
			$("input[name='menuState']").val("");
			$("input[name='menuName']").val("");
			$("input[name='menuSequ']").val("");
			$("input[name='menuUrl']").val("");
			$("textarea[name='menuInfo']").html("");
			if(menuId!="0"){
				$.post("<%=ctx%>/menu/addpage.action?id="+menuId,function(msg){
					$("input[name='parMenuName']").val(msg.menuName);
					$("input[name='menuPare']").val(msg.menuId);
					$("input[name='menuIslink']").val((Number(msg.menuIslink)+1));
					$("#myModalLabel").html("增加菜单");
					$("#myModal").modal('show');
				},"json");
			}else{
				$("input[name='parMenuName']").val("功能菜单");
				$("input[name='menuPare']").val("0");
				$("input[name='menuIslink']").val(1);
				$("#myModalLabel").html("增加菜单");
				$("#myModal").modal('show');
			}
		}
		
		function updateMenu(menuId){
			$.post("<%=ctx%>/menu/editpage.action?id="+menuId,function(msg){	
				if(msg[1]!=null){
					$("input[name='parMenuName']").val(msg[1].menuName);
					$("input[name='menuPare']").val(msg[1].menuId);
				}else{
					$("input[name='parMenuName']").val("功能菜单");
					$("input[name='menuPare']").val("0");
				}
				$("input[name='menuId']").val(msg[0].menuId);
				$("input[name='menuState']").val(msg[0].menuState);
				$("input[name='menuIslink']").val(msg[0].menuIslink);
				$("input[name='menuName']").val(msg[0].menuName);
				$("input[name='menuSequ']").val(msg[0].menuSequ);
				$("input[name='menuUrl']").val(msg[0].menuUrl);
				$("textarea[name='menuInfo']").html(msg[0].menuInfo);
				$("#myModalLabel").html("修改菜单");
				$("#myModal").modal('show');
			},"json");
		}
		
		function deleteMenu(menuId){
			$("button[name='delBt']").attr("id",menuId);
			$("#myModal2").modal('show');
		}
		
		function del(menuId){
			window.location.href = "<%=ctx%>/menu/delete.action?id="+menuId;
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
