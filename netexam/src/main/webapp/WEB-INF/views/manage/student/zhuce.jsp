<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    <style type="text/css">
	  label.error {
		color: red;
		margin-left: 10px;
		width: auto;
		display: inline;
	 }
   </style>
    </head>
    <body style="background:linear-gradient(white,#ebebeb,white);">
    
        <div class="container">
            <hr>
            <form id="registerForm" class="form-horizontal text-center" name="infoForm" action="#" method="post">
                
                <div class="container ">
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">账号:</label>
                        <div class="col-md-9 col-sm-9">
                            <input class="form-control" name="studentAccount" value=""  placeholder="请输入手机号码" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">密码:</label>
                        <div class="col-md-9 col-sm-9">
                            <input id="studentPassword" class="form-control" type="password" name="studentPassword" value="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">确认密码:</label>
                        <div class="col-md-9 col-sm-9">
                            <input class="form-control" type="password" name="studentPassword2" value="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">真实姓名:</label>
                        <div class="col-md-9 col-sm-9">
                            <input class="form-control" name="studentName" value="" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">班级:</label>
                        <div class="col-md-9 col-sm-9 text-left">
                            <input class="form-control" type="hidden" name="classId" value="${classs.classId }"  />
                            <label class="control-label">${classs.className }</label>
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
                                <input class="form-control btn-primary" type="button" onclick="goBack()" value="关闭" />
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
  
    function doSub(){
		 bootbox.confirm("确认提交吗?",function(r){
		
			 if(r){
				 $.ajax({
					type:"POST",
					url:"<%=ctx%>/student/save.action",
					data:$("form[name='infoForm']").serialize(),					
					cache:false,
					async:true,
					success:function(r){
						if(r.success){
							parent.closeZhuceDialog();
						}else{
							bootbox.alert("注册失败");
						}
					},
					arror:function(){
					  bootbox.alert("请求出现异常,请稍后再试!");	
					}
				 });
			 }
		 });
		}	
    //不能空验证
    $(function(){
    	 $("#registerForm").validate({
        	 rules:{
        		 studentAccount : {
        			 required : true,
        		     telphone : true
        		 },
        		 studentPassword : {
        			 required : true,
        			 rangelength : [6,12]
        		 },
        		 studentPassword2 : {
        			 required : true,
        			 equalTo: '#studentPassword'
        		 },
        		 studentName : {       			
        			 required : true,	
        			 rangelength : [2,8] ,
        		     chinese : true 
        	     }
        	    },
        	 messages:{
        		 studentAccount : {
        			 required : "账号不能为空"       		     
        		 },
        		 studentPassword : {
        			 required : "密码不能为空",      			
        		 },
        		 studentPassword2 : {
        			 required : "确认密码不能为空",
        			 equalTo : "两次密码不一致"
        		 },
        		 
        	 },
        	 submitHandler: function(form){
        		 doSub();
        	 },
         studentName :{
        	
          }
        });
    });
        
    //返回
   function goBack(){
	    parent.closeZhuceDialog();
	 }
</script>