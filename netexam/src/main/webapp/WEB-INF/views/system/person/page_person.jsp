<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<label class="control-label label-default col-xs-12 " style="margin-bottom: 30px;">
			<h4>当前位置：人员维护 － <c:if test="${requestScope.person==null}">增加</c:if><c:if test="${requestScope.person!=null}">修改</c:if>人员</h4>
		</label>
		<div class="container">
			<hr>
			<form class="form-horizontal text-center" style="position:relative;"  name="infoForm" action="javascript:doSub()" method="post" enctype="multipart/form-data">
				<input type="hidden" name="ppId" value="${requestScope.person.ppId}">	
				<input type="hidden" name="ppState" value="${requestScope.person.ppState}">	
				<input type="hidden" name="userId" value="${requestScope.person.userId}">	
				<div class="container " >
					<div class="form-group" style="position:absolute;right: 0px;top: 30px">
						<c:if test="${requestScope.person.ppPic==null}">
							<img id="image" src="<%=request.getContextPath()%>/images/menu/kehu.png" width="130" height="150">
						</c:if>
						<c:if test="${requestScope.person.ppPic!=null}">
							<img id="image" src="${requestScope.person.ppPic}" width="200" height="200">
						</c:if>
						<input type="file" name="pic" onchange="yuLan(this,'image','preview')" >
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">姓名:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppName" value="${requestScope.person.ppName}" placeholder="请输入姓名" />
						</div>	
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">编号:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppNo" value="${requestScope.person.ppNo}" placeholder="请输入编号" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">性别:</label>
						<div class="col-md-4 col-sm-4">
							<div class="col-md-4 col-sm-4">
								<input type="radio"  ${requestScope.person.ppSex=="1"?"checked":"" } class="col-md-1 col-sm-1"  name="ppSex"  value="1" /><span class="col-md-1 col-sm-1">男</span>&nbsp;&nbsp;
							</div>
							<div class="col-md-4 col-sm-4">
								<input type="radio" ${requestScope.person.ppSex=="0"?"checked":"" } class="col-md-1 col-sm-1"  name="ppSex"  value="0" /><span class="col-md-1 col-sm-1">女</span>
							</div>	
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">基本工资:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppPay" value="${requestScope.person.ppPay}" placeholder="请输入基本工资" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">民族:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppMinzu" value="${requestScope.person.ppMinzu}" placeholder="请输入民族" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">身份证号:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppShenfenzheng" value="${requestScope.person.ppShenfenzheng}" placeholder="请输入身份证号" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">所属部门:</label>
						<div class="col-md-4 col-sm-4">
							<select name="deptId" class="form-control"  onchange="querySp()">
								<option value="">===请选择===</option>
								<c:forEach items="${deptList}" var="dept">
									<option value="${dept.deptId }"  ${dept.deptId==requestScope.person.deptId?"selected":"" }>${dept.deptName }</option>
								</c:forEach>
							</select>
						</div>
						
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">职务信息:</label>
						<div class="col-md-4 col-sm-4">
							<select name="spId" class="form-control">
								<option value="">===请选择===</option>
							</select>
						</div>
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">电话:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppPhone" value="${requestScope.person.ppPhone}" placeholder="请输入电话" />
						</div>				
					</div>
					<div class="form-group">
						<label for="dtp_input1"  class="control-label col-sm-2 col-md-2">生日:</label>
                		<div class="input-group date form_date col-md-4 col-sm-4" data-date-format="yyyy年mm月dd日" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
		                    <input class="form-control" id="showTime1"  type="text" value="${ppBirth}" readonly>
		                    <span class="input-group-addon"><span  onclick="clearTime('showTime1','dtp_input1')" class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							<input type="hidden" id="dtp_input1"  name="birth" value="${ppBirth}"/>
		                </div>
					</div>
					<div class="form-group">
						<label for="dtp_input2" class="control-label col-sm-2 col-md-2">入职时间:</label>
                		<div class="input-group date form_date col-md-4 col-sm-4" data-date-format="yyyy年mm月dd日" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
		                    <input class="form-control" id="showTime2"  type="text" value="${ppInTime}" readonly>
		                    <span class="input-group-addon"><span  onclick="clearTime('showTime2','dtp_input2')" class="glyphicon glyphicon-remove"></span></span>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							<input type="hidden" id="dtp_input2"  name="inTime" value="${ppInTime}"/>
		                </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">住址:</label>
						<div class="col-md-6 col-sm-6">
							<textarea class="form-control" name="ppAdds" rows="3" placeholder="请输入用户住址" >${requestScope.person.ppAdds}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">信息:</label>
						<div class="col-md-6 col-sm-6">
							<textarea class="form-control" name="ppInfo" rows="3" placeholder="请输入用户信息" >${requestScope.person.ppInfo}</textarea>
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
		
		<!-- 模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header" style="background-color: rgba(0,0,0,0.1)">
		            <button type="button" class="close" data-dismiss="modal" 
		               aria-hidden="true">×
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		              	
		            </h4>
		         </div>
		         <div class="modal-body text-center" id="modalBody">
		           	
		         </div>
		         <div class="modal-footer" id="modalFooter2">
		            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		         </div>
		      </div>
		   </div>
		</div>
	</body>
</html>
<script type="text/javascript">
	
	$('.form_date').datetimepicker({
		language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	
	$(function(){
		if("${requestScope.person!=null}"){
			var deptId = $("select[name='deptId']").val();
			if(deptId!=""){
				$("select[name='spId']").load("<%=ctx%>/person/querySp.action?deptId="+deptId,function(msg){
					this.value = "${requestScope.person.spId}";
				});
			}else{
				$("select[name='spId']").html("<option value='''>===请选择===</option>");
			}
		}
	});
	
	function querySp(){
		var deptId = $("select[name='deptId']").val();
		if(deptId!=""){
			$("select[name='spId']").load("<%=ctx%>/person/querySp.action?deptId="+deptId);
		}else{
			$("select[name='spId']").html("<option value='''>===请选择===</option>");
		}
		
	}
	
	function clearTime(id1,id2){
		$("#"+id1).val("");
		$("#"+id2).val("");
	}
	
	function doSub(){
		$("#myModalLabel").html("确认提交");
		$("#modalBody").html("是否确定提交?");
		$("#modalFooter2").html("<button type='button' class='btn btn-primary' data-dismiss='modal' onclick='tijiao()'>提交</button><button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
		$("#myModal").modal('show');
	}
	
	function tijiao(){
		$("form[name='infoForm']").attr("action","<%=ctx%>/person/save.action");
		$("form").submit();
	}
	
	function goBack(){
		history.go(-1);
	}
</script>