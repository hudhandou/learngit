<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
	
		<div class="container">
			<hr>
			<form class="form-horizontal text-center" name="infoForm" action="javascript:doSub()" method="post">
				
				<div class="container ">
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">所属类别:</label>
						<div class="col-md-9 col-sm-9">
							<select name="subjectPid" class="form-control">
								<option value="">===请选择===</option>
								<c:forEach items="${sjblist}" var="sjb">
								<option value="${sjb.subjectId}" ${sj.subjectPid == sjb.subjectId?"selected":""}>${sjb.subjectName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">学科名称:</label>
						<div class="col-md-9 col-sm-9">
						    <input type="hidden" name="subjectId" value="${sj.subjectId}">
							<input class="form-control" noinput="subjectnameone(this.value)" name="subjectName" value="${sj.subjectName}" placeholder="请输入学科名称" onblur="blurckName(this.value)"/>
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
		
	</body>
</html>
<script type="text/javascript">
	var bj = false;
	//验证名称是否重复
	function blurckName(subjectName){
		subjectName = $.trim(subjectName);
		//获取仓库名称的简拼
		if(subjectName!=""){			
			$.ajax({
				type: "post", //请求方式
				url :"<%=ctx%>/subject/subjectname.action",  //请求路径
				data :{"subjectName":subjectName},  //给服务器传的数据
				dataType :"json",  //预期服务器返回数据类型  
				async :false ,      //是否是 异步
				cache : false ,	   //是否要缓存
				success :function (r){  //请求成功后的回调函数 
					$("input[name='subjectName'] + span").remove();
					if(r.success){
						bj=true;
						$("input[name='subjectName']").after("<span style='color:red;'>仓库名称重复，请换一个!</span>")
					}else{
						bj=false;
					}
				},
				error: function (){  
					bootbox.alert("请求出现异常,请稍后再试！");			
				}
			});					
		}
	}
	//修改保存信息	
	function doSub(){
		if(bj){
		 return;	
		}
	 bootbox.confirm("确认修改吗?",function(r){
		 if(r){
			 $.ajax({
				type:"POST",
				url:"<%=ctx%>/subject/editSave.action",
				data:$("form[name='infoForm']").serialize(),
				dataType:"json",
				cache:false,
				async:true,
				success:function(r){
					if(r.success){
						location.href="<%=ctx%>/subject/query.action";
					}else{
						boot.alert("修改失败");
					}
				},
				arror:function(){
				  bootbox.alert("请求出现异常,请稍后再试!");	
				}
			 });
		 }
	 });
	}
	
	function goBack(){
		location.href = "<%=ctx%>/subject/query.action";
	}
</script>