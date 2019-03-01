<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<%@ include file="/common/easyui.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title>查看试卷(考试完毕后查看试卷)</title>
    </head>
    <body style="background:linear-gradient(white,#ebebeb,white);">
    
        <div class="container">
            
            <form class="form-horizontal text-center" name="infoForm" action="javascript:doSub()" method="post">
                
                <div class="container ">
                    <c:forEach items="${map.topicList }" var="t" varStatus="i">
                    
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2"></label>
                        <div class="col-md-9 col-sm-9 text-left">
                            <h3>题目${i.count }: ${t.topicName }</h3>
                            <div>答案A: ${t.topicAnswerA }</div>
                            <div>答案B: ${t.topicAnswerB }</div>
                            <div>答案C: ${t.topicAnswerC }</div>
                            <div>答案D: ${t.topicAnswerD }</div>
                            <div>
                                                                            正确答案是:${t.topicAnswerTrue }
                            </div>
                            <div> 学生选择的答案是:
                                   <c:choose>
                                     <c:when test="${t.topicAnswerTrue eq map.studentanswer[i.index] }">
                                        <span>${map.studentanswer[i.index] }</span>
                                     </c:when>
                                     <c:otherwise>
                                        <span style="color:red;">${map.studentanswer[i.index] }</span>
                                     </c:otherwise>
                                   </c:choose>                                      
                                                         
                                
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    
                </div>
            </form>
                <hr/>
        </div>
        
    </body>
</html>
<script type="text/javascript">
    

    
    
</script>