<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<%@ include file="/common/easyui.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title>考试页面</title>
    </head>
    <body style="background:linear-gradient(white,#ebebeb,white);">
    
        <div class="container">
            <div style="background-color: gray;">
                 <div class="col-sm-8"><h3 >试卷名称: ${paper.paperName } , 剩余时间:</h3></div>
                 <div class="col-sm-4"><h3 style="color:red" id="yongshi">${paper.paperNeedtime }</h3></div>
            </div>
            <form class="form-horizontal text-center" name="infoForm" action="javascript:doSub()" method="post">
                <input type="hidden" name="paperId" value="${paper.paperId }">
                <div class="container ">
                    <c:forEach items="${list}" var="t" varStatus="i">
                    <input type="hidden" name="topicId" value="${t.topicId }">
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2"></label>
                        <div class="col-md-9 col-sm-9 text-left">
                            <h3>题目${i.count }: ${t.topicName }</h3>
                            <div>答案A: ${t.topicAnswerA }</div>
                            <div>答案B: ${t.topicAnswerB }</div>
                            <div>答案C: ${t.topicAnswerC }</div>
                            <div>答案D: ${t.topicAnswerD }</div>
                            <div>选择答案:
                                <input type="radio" name="topicAnswerTrue${i.count }" value="A">A &nbsp;&nbsp;
                                <input type="radio" name="topicAnswerTrue${i.count }" value="B">B &nbsp;&nbsp;
                                <input type="radio" name="topicAnswerTrue${i.count }" value="C">C &nbsp;&nbsp;
                                <input type="radio" name="topicAnswerTrue${i.count }" value="D">D
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    
                    <div class="form-group">
                        <div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
                            
                        </div>
                        <div class=" col-lg-6 col-md-6 col-sm-8 col-xs-8" >
                            <div class="col-md-6 col-sm-6">
                                <input class="form-control btn-primary" type="submit" value="交卷" />
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
    	
    	var paperId = "${paper.paperId }";
    	var studentId = "${sessionScope.user.userId}";
    	var topicIdinputs = $("input[name='topicId']");
    	//用了多长时间
    	var yongshi = $("#yongshi").html();
    	//收集所有题目id
    	var topicIds = [];
    	for (var i = 0; i < topicIdinputs.length; i++) {
			var id = topicIdinputs[i].value;
			topicIds.push(id);
		}
    	//判断是否有没做完的题
    	var daaninputs = $("input[name^='topicAnswerTrue']:checked");
    	if(topicIdinputs.length != daaninputs.length){
    		bootbox.alert("有题没有选择答案");
    		return;
    	}
    	//收集所有答案
    	var daans = [];
    	for (var i = 0; i < daaninputs.length; i++) {
    		daans.push( daaninputs[i].value );
		}
    	// 封装参数
    	var data = {
    			"studentId" : studentId,
    			"topicIds" : topicIds.join(","),
    			"yongshi" : yongshi,
    			"paperId" : paperId,
    			"daans" : daans.join(",")
    	};
    	bootbox.confirm("确定要交卷吗?",function(r){
            if (r) {
                $.ajax({
					type : "POST",
					url : "<%=request.getContextPath() %>/exam/jiaojuan.action",
					data : data,
					dataType : "json",
					cache : false,
					async : true,
					success : function(r) {
					    bootbox.dialog({
							message : "恭喜你,考试得分"+r.score+"分",
							title : "提示",
							buttons : {
								OK : {
									label : "OK",
									className : "btn-primary",
									callback : function() {
										parent.closeModal();
									}
								}
							}
						});
					},
					error : function() {
						bootbox.alert("请求出现异常，请稍后再试！");
					}
				});
            }
        });
    }
    
    function goBack(){
        location.href = "<%=ctx%>/paper/query.action";
    }
</script>