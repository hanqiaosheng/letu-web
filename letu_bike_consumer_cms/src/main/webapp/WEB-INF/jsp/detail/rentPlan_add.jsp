<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" type="text/css" href="datepicker/jquery.datetimepicker.css"/>
<script type="text/javascript">
  function changeChannelName(obj){
	  $.ajax({
		 type:'post',
		 url:'cms/channel/changeChannelName.action',
		 data:'channelId='+obj,
		 success:function(data){
			 $("#channelName").text(data.channelName);
		 }
	  });
  }
  function cancel(){
	  window.location.reload();
  }
 
  
</script>
<%@include file="../common/body.jsp"%>



<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加租赁费用方案</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/channel/addRentPlan.action"  method="post">
							    <label>渠道号 ： ${channel.channelId }
							    <select class="form-control" id="modelsChannelId" name="modelsChannelId" onchange="changeChannelName(this.value)">
							    <option value="">请选择</option>
							    <c:forEach var="channel" items="${channelList }">
							    <option value="${channel.channelId }">${channel.channelId }</option>
							    </c:forEach>
							    </select>
							    </label><br>
								<label>渠道名称 ：</label><label id="channelName"></label><br>
								
								<label>车辆型号：</label>
								<div class="form-group">
									<input class="form-control" type="text" id="modelsCode" name="modelsCode" onblur="checkCode()" required="required">
								<label>车辆型号名：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="modelsName" required="required">
								</div>
								<label>车辆预付款：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="modelsDeposit" required="required">
								</div>
								<label>租赁费用：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="modelsRentPrice" required="required">
								<label>元/小时</label>
								</div>
								<div style="text-align: center;">
									<input type="submit" value="提交" class="btn btn-primary">
									<input type="button" value="重置" onclick="cancel()" class="btn btn-primary">
								</div>
							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>

<%@include file="../common/buttom.jsp"%>
<script type="text/javascript">
	function checkCode(){
		var checkCode = $("#modelsCode").val();
		var modelsChannelId = $("#modelsChannelId").val();
		var dataStr = "checkCode="+checkCode+"&modelsChannelId="+modelsChannelId;
		$.ajax({
			 type:'post',
			 url:'cms/channel/checkCode.action',
			 data:dataStr,
			 success:function(data){
				if(data.message!="success"){
					alert(data.message);
				}				 
				 
			 }
		  });
		
		
		
	}



</script>
</html>
