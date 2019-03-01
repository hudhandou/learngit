<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>
<%@ include file="/common/easyui.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body style="background:linear-gradient(white,#ebebeb,white);">
    
        <div class="container">
            <hr>
            <form id="paperAddForm" class="form-horizontal text-center" name="infoForm" action="javascript:doSub()" method="post">
                <input type="hidden" name="paperId" value="${p.paperId}" />
                <div class="container ">
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">试卷名次:</label>
                        <div class="col-md-9 col-sm-9">
                            <input class="form-control" name="paperName" value="${p.paperName }"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">题目数量:</label>
                        <div class="col-md-9 col-sm-9">
                            <input class="form-control" name="paperTmsl" value="${p.paperTmsl }"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">考试时长(分钟):</label>
                        <div class="col-md-9 col-sm-9">
                            <input class="form-control" name="paperNeedtime" value="${p.paperNeedtime }"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">每题分数:</label>
                        <div class="col-md-9 col-sm-9">
                            <input class="form-control" name="paperScore" value="${p.paperScore }"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2 col-md-2">选择学科:</label>
                        <div class="col-md-9 col-sm-9 text-left">
                            <ul id="subjectTree"></ul>
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
	$(function(){
		$("#subjectTree").tree({
			url:"<%=request.getContextPath()%>/paper/querySubjectTree.action",
			lines:true,
			onClick:function(node){
				 if(bj){
					 bj = false;
					 $("#subjectTree").tree("expand",node.target); 
				 }else{
					 bj = true;
					 $("#subjectTree").tree("collapse",node.target);
				 }
			},
			checkbox:true,
		    animate:true,
		    onLoadSuccess:function(node,date){
		    $("#subjectTree").tree("expandAll");
		    checkedSubjecttree();
		    }
		});	
	});
    
    function checkedSubjecttree(){
    	var paperId = $("input[name='paperId']").val();
    	var url = "<%=ctx%>/paper/checkedsjtree.action";
    	var data={"paperId":paperId};
    	$.post(url,data,function(r){
    		if(r != null && r.length > 0){
    			for (var i = 0; i < r.length; i++) {
					var node = $("#subjectTree").tree('find',r[i]);
					 $("#subjectTree").tree('check',node.target);
				}
    		}
    	},"json");
    }
    
    function doSub(){
        bootbox.confirm("确认要修改吗",function(r){
        	if(r){
        	var treeNodes =  $("#subjectTree").tree("getChecked"); //获取选中的
        	var ids = [];
        	for (var i = 0; i < treeNodes.length; i++) {
				var node = treeNodes[i];
				if(node.attributes && node.attributes.level == "2"){    
					ids.push(node.id);
				}
			}
        	  var url = "<%=ctx%>/paper/editsave.action";
        	  var data = $("#paperAddForm").serialize()+"&ids="+ids.join(",");
        	  $.post(url,data,function(r){
        		   if(r.success){
        			   location.href="<%=ctx%>/paper/query.action";
        		   }else{
        			   bootbox.alert("修改失败");
        		   }
        	  },"json");
        	}
        });
    	
    }
    
    function goBack(){
        location.href = "<%=ctx%>/paper/query.action";
    }
</script>