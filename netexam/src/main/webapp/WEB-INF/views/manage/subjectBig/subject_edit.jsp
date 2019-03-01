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
						<label class="control-label col-sm-2 col-md-2">学科名称:</label>
						<div class="col-md-9 col-sm-9">
							<input type="hidden" name="subjectId" value="${sj.subjectId}" />
							<input class="form-control" name="subjectName" oninput="bigName(this.value)" value="${sj.subjectName}" placeholder="请输入学科名称" />
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
//验证大类别名称是否重复
function bigName(subjectName){
	var subjectName=$.trim(subjectName);
	var ymj = "${sj.subjectName}";
	if(subjectName == ymj){
		$("input[name='subjectName']+span").remove();
		bj=false;
		return;
	}
	if(subjectName != ""){
		$.ajax({
			type:"POST",
			url:"<%=ctx%>/big/subjectBigname.action",
			data:{"subjectName":subjectName},
			dataType:"json",
			chache: false,
			async: false, //同步验证
			success:function(r){
			$("input[name='subjectName']+span").remove();
			if(r.success){
				bj=true;
			   $("input[name='subjectName']").after("<span style='color:red;'>名称重复，请换一个!</span>")
			}else{
				bj=false;
			   }
			},
			error:function(){
				bootbox.alert("请求出现异常,请稍后再试!");
			}
		});
	}
	
}
	
function doSub(){
	if(bj){
	 return;	
	}
 bootbox.confirm("确认提交吗?",function(r){
	 if(r){
		 $.ajax({
			type:"POST",
			url:"<%=ctx%>/big/editSave.action",
			data:$("form[name='infoForm']").serialize(),
			dataType:"json",
			cache:false,
			async:true,
			success:function(r){
				if(r.success){
					location.href="<%=ctx%>/big/query.action";
				}else{
					boot.alert("添加失败");
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
		location.href = "<%=ctx%>/big/query.action";
	}
</script>