<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/include.jsp" %>
    <%@ include file="/common/easyui.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background:linear-gradient(white,#ebebeb,white);">
	<table class="depttreestyle" width="100%" style="margin-top: 10px;">
		<tr>
			<td >
				<ul id="depttree"></ul>
			</td>
			<td style="vertical-align: top;">
				<a id="depttree_add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="javascript:dept_add()">添加新部门</a>
				&nbsp;&nbsp;
				<a id="depttree_edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="javascript:dept_edit()">修改部门</a>
				&nbsp;&nbsp;
				<a id="depttree_delete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="javascript:dept_delete()">删除部门</a>
			</td>
		</tr>
	</table>
	<%-- 载入菜单的右键菜单 --%>
	<jsp:include page="dept_contextManu.jsp"></jsp:include>
	<script type="text/javascript">
		$("#depttree").tree({
			url:'<%=request.getContextPath() %>/dept/querySysDept.action',
			lines:true,
			onContextMenu: function(e, node){
				e.preventDefault();
				// 选择节点
				$('#depttree').tree('select', node.target);
				//判断是几级菜单
				$('#dept_contextMenu').menu('show', {left: e.pageX,top: e.pageY});
			}
		});
		function dept_add(){
			var treenode = $("#depttree").tree("getSelected");
			if (treenode == null || treenode.attributes.level=="3") {
				$.messager.alert('提示','请选择一个1级或者2级菜单!','info');
			} else {
				var id = treenode.id;
				var level=parseInt(treenode.attributes.level)+1;
	        	var dd = $('<div/>').dialog({
					href:"${pageContext.request.contextPath}/dept/toaddpage.action?deptPar="+id,
					width : 650,
					height: 330,
					modal : true,
					title : '添加信息',
					buttons : [ {
						text : '增加',
						iconCls : 'icon-add',
						handler : function() {
							$('#sys_dept_addForm').form('submit', {
								url : '${pageContext.request.contextPath}/dept/deptsave.action?deptPar='+id,
								onSubmit: function(){       
									 return $(this).form("validate");
								},    
								success : function(result) {
									try {
										var r = $.parseJSON(result);
										if (r.success) {
											//添加一些节点到选择的节点
											var selected = $('#depttree').tree('getSelected');
											$('#depttree').tree('append', {
												parent: selected.target,
												data:[{
													id:r.obj.deptId,
													text:r.obj.deptName,
													state:'open',
													attributes:{level:level}
												}]
											});
											dd.dialog('destroy');
										}
										$.messager.show({
											title : '提示',
											msg : r.msg
										});
									} catch (e) {
										$.messager.alert('提示', result);
									}
								}
							});
						}
					} ],
					onClose : function() {
						$(this).dialog('destroy');
					}
				});
			}
		}
		function dept_edit(){
			var treenode = $("#depttree").tree("getSelected");
			if (treenode.id==0) {
				$.messager.alert('提示','系统菜单不能修改','info');
				return;
			}
	        if(treenode!=null){
	        	var id = treenode.id;
	        	var level=parseInt(treenode.attributes.level);
	        	var dd = $('<div/>').dialog({
					href:"${pageContext.request.contextPath}/dept/toeditpage.action?id="+id,
					width : 650,
					height: 330,
					modal : true,
					title : '编辑信息',
					buttons : [ {
						text : '修改保存',
						iconCls : 'icon-add',
						handler : function() {
							$('#sys_dept_editForm').form('submit', {
								url : '${pageContext.request.contextPath}/dept/deptedit.action',
								onSubmit: function(){       
									 return $(this).form("validate");
								},    
								success : function(result) {
									try {
										var r = $.parseJSON(result);
										if (r.success) {
											//更新选择的节点文本
											$('#depttree').tree('update', {
												target: treenode.target,
												text:r.obj.deptName,
												attributes:{level:level}
											});
											dd.dialog('destroy');
										}
										$.messager.show({
											title : '提示',
											msg : r.msg
										});
									} catch (e) {
										$.messager.alert('提示', result);
									}
								}
							});
						}
					} ],
					onClose : function() {
						$(this).dialog('destroy');
					}
				});
	        }else{
	        	$.messager.alert('提示','请先选择一个部门!','info');
	        }
		}
		function dept_delete(){
			var treenode = $("#depttree").tree("getSelected");
	    	if(treenode!=null){
	    		var id = treenode.id;
	    		if( treenode.children != null && treenode.children.length>0 ){
	    			$.messager.alert('提示','请先删除下级部门再删除当前部门!','info');
	    			return ;
	    		}
	    		$.messager.confirm('确认提示','确定删除选中的菜单吗？',function(r){   
				    if (r){
				    	$.post("${pageContext.request.contextPath}/dept/deptdelete.action","id="+id,function(data){
				    		if(data.success){
				    			$.messager.show({ title:'提示', msg:data.msg });
				    			$("#depttree").tree("remove",treenode.target);
				    		}else{
				    			$.messager.show({ title:'提示', msg:data.msg });
				    		}
				    	},"json");
				    }   
				});
	    	}else{
	    		$.messager.alert('提示','请先选择一个要删除的部门!','info');
	    	}
		}
	</script>
</body>
</html>