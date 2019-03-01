<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询计价方式信息</title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
	
		<div class="container" style="margin-top:10px;">
			<form action="" class="form form-horizontal" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2">班级名称:</label>
					<div class="col-sm-3">
						<input class="form-control" name="className" value="${classspage.className }" placeholder="请输入班级名称" />
					</div>
					<div class="col-sm-1">
   						<input class="form-control btn-primary" type="submit" value="查询" />
   					</div>
					<div class="col-sm-1">
						<input class="form-control btn-primary" type="button" onclick="cleanUp()" value="清空" />
					</div>
				</div>
				
			</form>
		</div>
		<form action="<%=ctx %>/jjfs/delete.action" method="post" name="delForm">
		<div class="container ">
			<table class="table table-hover table-striped  text-center" >
				<caption class="text-center" style="border: 0px solid;">
					<div class="col-sm-3" style="margin-left:-15px;">
						<div class="btn-group" style="display:block;">
							<button type="button" class="btn btn-primary" onclick="addpage()">添加<span class="glyphicon glyphicon-plus"></span></button>
							<button type="button" class="btn btn-danger" onclick="del()">删除<span class="glyphicon glyphicon-minus"></span></button>
						</div>
					</div>
					<div class="col-sm-5 text-center">
						<strong>班级信息</strong>
					</div>
				</caption>
				<thead >
					<tr class="bg-primary">
						<td>序号</td>
						<td><a href="javascript:selectAll()"><font style="color: white;">全选</font></a></td>
						<td>班级名称</td>
						<td>是否为默认班级</td>
						<td>操作</td>
						<td>设置</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pageInfo.list}" var="t" varStatus="i">
					<tr>
						<td>${i.count+pageInfo.startRow-1}</td>
						<td><input type="checkbox" name="ids" value="${t.classId}"></td>
						<td>${t.className}</td>
						<td>${t.classState}</td>
						<td>
							<button onclick="editpage('${t.classId }')" type="button" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-edit"></span> 修改</button>
						</td>
						<td>
							<c:if test="${t.classState =='N' }">
								<button onclick="moren('${t.classId }')" type="button" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-edit"></span> 设置默认</button>
							</c:if>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>	
		</div>
		</form>
		
		
		<%-- 分页start --%>
		<div class="container text-center" >
			<page:mypage3 pageName="pageInfo" uri="classs/query.action"></page:mypage3>
		</div>
		<%-- 分页end --%>
	</body>

</html>
<script type="text/javascript">
	function addpage(){
		window.location.href = "<%=ctx%>/classs/addpage.action";
	}
	
	function editpage(id){
		window.location.href = "<%=ctx%>/classs/editpage.action?id="+id;
	}
	
	function moren(id){
		window.location.href = "<%=ctx%>/classs/moren.action?id="+id;
	}
	//全选
	function selectAll(){
		var inpts = $("input[name='ids']");
		for(var i=0;i<inpts.length;i++){
			if(inpts[i].checked!=true){
				inpts[i].checked=true;
			}else{
				inpts[i].checked=false;
			}
		}
	}
	
	function del(){	
		//先判断 有无被选中的记录
		var fxks = $("input[name='ids']:checked");
		if(fxks.length==0){
			bootbox.alert("请先选择要删除的记录");
			return;
		}
		bootbox.confirm("确定要删除选中的记录吗?",function(r){
			if(r){
				var ids=[];
				$.each(fxks,function(i,fxk){
					ids.push(fxk.value);
				});
				$.ajax({
					type: "post", //请求方式
					url :"<%=ctx%>/classs/delete.action",  //请求路径
					data : {"ids":ids.join(",")}, //给服务器传的数据
					dataType :"json",  //预期服务器返回数据类型 
					async :true ,      //是否是 异步
					cache : false ,	   //是否要缓存
					success :function (r){  //请求成功后的回调函数 
						if(r.success){
							location.reload();
						}else{
							//bootbox.alert("添加失败");
							bootbox.dialog({  
								title: "提示",  
								message: r.msg,  
								buttons: {  
									OK: {  
										label: "确定",  
										className: "btn-primary",  
										callback: function () {  
											location.reload();//确定
										}  
									}  
								}  
							});
						}
					},
					error: function (){  
						bootbox.alert("请求出现异常,请稍后再试！");			
					}
				})
			}
		})
	}
	function moren(id){	
		$.ajax({
			type:"POST",
			url:"<%=ctx%>/classs/moren.action",
			data:{"id":id},
			dataType:"json",
			chache: false,
			async: true, //同步验证
			success:function(r){
				if(r.success){
					location.href="<%=ctx%>/classs/query.action";
				}else{
					boot.alert("设置失败");
				}
			},
			error:function(){
				bootbox.alert("请求出现异常,请稍后再试!");
			}
		});
		
	}
	//清空
	function cleanUp(){
		$("input[name='className']").val("");
		$("select[name='classId']").val("");
	}
	
</script>