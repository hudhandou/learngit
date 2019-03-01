<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>查询试卷信息</title>
 <style type="text/css">
	  label.error {
		color: red;
		margin-left: 10px;
		width: auto;
		display: inline;
	   }
    </style>
</head>
<body style="background: linear-gradient(white, #ebebeb, white);">

	<div class="container" style="margin-top: 10px;">
		<form name="searchForm" action="<%=ctx %>/paper/query.action"
			class="form form-horizontal" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2 col-md-2">试卷名称:</label>
				<div class="col-sm-3">
					<input class="form-control" name="paperName"
						value="" placeholder="请输入班级名称" />
				</div>
				<div class="col-sm-1">
					<input class="form-control btn-primary" type="submit" value="查询" />
				</div>
				<div class="col-sm-1">
					<input class="form-control btn-primary" type="button"
						onclick="cleanUp()" value="清空" />
				</div>
			</div>

		</form>
	</div>
	<form action="<%=ctx %>/jjfs/delete.action" method="post"
		name="delForm">
		<div class="container ">
			<table class="table table-hover table-striped  text-center">
				<caption class="text-center" style="border: 0px solid;">
					<div class="col-sm-3" style="margin-left: -15px;">
						<div class="btn-group" style="display: block;">
							<button type="button" class="btn btn-primary" onclick="addpage()">
								添加<span class="glyphicon glyphicon-plus"></span>
							</button>
							<button type="button" class="btn btn-danger" onclick="del()">
								删除<span class="glyphicon glyphicon-minus"></span>
							</button>
						</div>
					</div>
					<div class="col-sm-5 text-center">
						<strong>试卷信息</strong>
					</div>
				</caption>
				<thead>
					<tr class="bg-primary">
						<td>序号</td>
						<td><a href="javascript:selectAll()"><font
								style="color: white;">全选</font></a></td>
						<td>试卷名称</td>
						<td>创建时间</td>
						<td>需要时间(分钟)</td>
						<td>每题分数</td>
						<td>题目数量</td>
						<td>状态</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageInfo.list}" var="t" varStatus="i">
						<tr>
							<td>${i.count+pageInfo.startRow-1}</td>
							<td><input type="checkbox" name="ids" value="${t.paperId}"></td>
							<td>${t.paperName}</td>
							<td><fmt:formatDate value="${t.paperCreatetime }" /></td>
							<td>${t.paperNeedtime }</td>
							<td>${t.paperScore }</td>
							<td>${t.paperTmsl }</td>
							<td><c:choose>
									<c:when test="${t.paperState == 0 }">
										<button onclick="examyesorno('yes','${t.paperId }')"
											type="button" class="btn btn-sm btn-primary">
											<span class="glyphicon glyphicon-edit"></span> 设置为考试
										</button>

									</c:when>
									<c:otherwise>
										<button onclick="examyesorno('no','${t.paperId }')"
											type="button" class="btn btn-sm btn-primary">
											<span class="glyphicon glyphicon-edit"></span> 取消
										</button>
									</c:otherwise>
								</c:choose></td>
							<td>
								<button onclick="editpage('${t.paperId }')" type="button"
									class="btn btn-sm btn-primary">
									<span class="glyphicon glyphicon-edit"></span> 修改
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>


	<%-- 分页start --%>
	<div class="container text-center">
		<page:mypage3 pageName="pageInfo" uri="paper/query.action"></page:mypage3>
	</div>
	<%-- 分页end --%>
</body>

</html>
<%@include file="/common/noty.jsp"%>
<script type="text/javascript">
    function examyesorno(state,paperId){
    	//var state = "yes";
    	var zhi = state +","+paperId;
    	$.ajax({
			type: "POST",
			url : "<%=ctx%>/paper/kaoshiquxiao.action",
			data : {"zhi":zhi},			
			cache : false,
			async : true,
			success : function(r){
				if(r.success){
				 location.reload();
				}else{
					bootbox.dialog({  
						title: "提示",  
						message: r.msg,  
						buttons: {  
							 OK: {  
								label: "确定",  
								className: "btn-primary",  
								callback: function () {  
									window.location.href = "<%=ctx%>/paper/query.action";	
								}  
							}  
						}  
					});
				}
			},
			error:function(){
				bootbox.alert("请求异常,请稍后重试!")
			}
		});
    }   	
    function addpage(){
        window.location.href = "<%=ctx%>/paper/addpage.action";
    }
    
    function editpage(id){
        window.location.href = "<%=ctx%>/paper/editpage.action?id="+id;
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
    
    
    //删除
   function del(){	
		//alert(0)
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
					url :"<%=ctx%>/paper/delete.action",  //请求路径
					data : {"ids":ids.join(",")}, //给服务器传的数据
					dataType :"json",  //预期服务器返回数据类型  
					async :true ,      //是否是 异步
					cache : false ,	   //是否要缓存
					success :function (r){  //请求成功后的回调函数 
						if(r.success){
							location.reload();
						}else{
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
	//清空
	function cleanUp() {
		$("input[name='paperName']").val("");
	}
</script>