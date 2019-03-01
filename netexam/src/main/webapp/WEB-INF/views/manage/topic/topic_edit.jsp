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
                <input class="form-control" type="hidden" name="topicId" value="${t.topicId}">
                <div class="container ">
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">所属学科:</label>
                        <div class="col-md-9 col-sm-9">
                            <select name="subjectId" class="form-control">
                                <option value="">===请选择===</option>
                                <c:forEach items="${subList}" var="sub">
                                    <option value="${sub.subjectId }"${sub.subjectId==t.subjectId?"selected":"" }>${sub.subjectName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">题目名称:</label>
                        <div class="col-md-9 col-sm-9">
                            <textarea class="form-control" name="topicName" rows="3" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">答案A:</label>
                        <div class="col-md-9 col-sm-9">
                            <textarea class="form-control" name="topicAnswerA" rows="3" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">答案B:</label>
                        <div class="col-md-9 col-sm-9">
                            <textarea class="form-control" name="topicAnswerB" rows="3" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">答案C:</label>
                        <div class="col-md-9 col-sm-9">
                            <textarea class="form-control" name="topicAnswerC" rows="3" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">答案D:</label>
                        <div class="col-md-9 col-sm-9">
                            <textarea class="form-control" name="topicAnswerD" rows="3" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">正确答案:</label>
                        <div class="col-md-9 col-sm-9 text-left">
                            <label class="radio-inline">
							  <input type="radio" name="topicAnswerTrue" id="inlineRadio1" value="A" checked="checked"> A
							</label>
							<label class="radio-inline">
							  <input type="radio" name="topicAnswerTrue" id="inlineRadio2" value="B"> B
							</label>
							<label class="radio-inline">
							  <input type="radio" name="topicAnswerTrue" id="inlineRadio3" value="C"> C
							</label>
							<label class="radio-inline">
							  <input type="radio" name="topicAnswerTrue" id="inlineRadio3" value="D"> D
							</label>
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
    
var bj=false; //假设名称不重复,可以提交.
function doSub(){
	if (bj){
		return;
	}
	bootbox.confirm("确认提交保存吗？",function(r){
		if(r){
			$.ajax({
				type: "post", //请求方式
				url :"<%=ctx%>/topic/edit.action",  //请求路径
				data : $("form[name='infoForm']").serialize(),  //给服务器传的数据
				dataType :"json",  //预期服务器返回数据类型  
				async :true ,      //是否是 异步
				cache : false ,	   //是否要缓存
				success :function (r){  //请求成功后的回调函数 
					if(r.success){
						location.href="<%=ctx%>/topic/query.action";
					}else{
						bootbox.alert("修改失败");
					}
				},
				error: function (){  
					bootbox.alert("请求出现异常,请稍后再试！");			
				}
			});					
		}
	})
}
    
    function goBack(){
        location.href = "<%=ctx%>/topic/query.action";
    }
</script>