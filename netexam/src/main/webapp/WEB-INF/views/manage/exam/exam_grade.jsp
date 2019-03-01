<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title>查询考试成绩</title>
    </head>
    <body style="background:linear-gradient(white,#ebebeb,white);">
    
        <div class="container" style="margin-top:10px;">
            <form name="searchForm" action="<%=ctx %>/paper/query.action" class="form form-horizontal" method="post">
                <div class="form-group">
                    
                </div>
                
            </form>
        </div>
        <form action="<%=ctx %>/jjfs/delete.action" method="post" name="delForm">
        <div class="container ">
            <table class="table table-hover table-striped  text-center" >
                <caption class="text-center" style="border: 0px solid;">
                   
                        <strong>试卷信息</strong>
                    
                </caption>
                <thead >
                    <tr class="bg-primary">
                        <td>序号</td>
                        <td>试卷名称</td>
                        <td>考试时间</td>
                        <td>得分</td>
                        <td>满分</td>
                        <td class="text-left">正确率</td>
                        <td>考试次数</td>
                        <td>操作</td>
                        <td>排名</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${list }" var="t" varStatus="i">
                    <tr>
                        <td>${i.count}</td>
                        <td>${t.paperName}</td>
                        <td><fmt:formatDate value="${t.studentpaperKaoshishijian }"/></td>
                        <td>${t.studentpaperScore }</td>
                        <td>${t.studentpaperZongfen }</td>
                        <td >
                            <div style="width: 100px;border: 1px solid gray;height: 20px;">
                                <div style="background-color: red;width: ${t.studentpaperScore/t.studentpaperZongfen*100}px;height: 20px;">${t.studentpaperScore/t.studentpaperZongfen*100 }%</div>
                            </div>
                        </td>
                        <td>${t.studentpaperCount }</td>
                        <td>
                            <button onclick="lookpaper('${t.studentpaperId }')" type="button" class="btn btn-sm btn-primary">查看试卷</button>
                        </td>
                        <td>
                            <button onclick="paiming('${t.studentpaperId }')" type="button" class="btn btn-sm btn-warning">班级排名</button>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>    
        </div>
        </form>
        
        
        <%-- 分页start --%>
        <div class="container text-center"  >
            
        </div>
        <%-- 分页end --%>
    </body>

</html>
<%@include file="/common/noty.jsp" %>
<script type="text/javascript">
    function lookpaper(id){
    	location.href = "<%=ctx%>/exam/lookpaper.action?id="+id;
    }
   
</script>