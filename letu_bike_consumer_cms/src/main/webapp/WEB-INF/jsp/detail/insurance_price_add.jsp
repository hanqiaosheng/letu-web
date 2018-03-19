<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<link rel="stylesheet" href="assets/dialog.css">
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加保险费用</h1>
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
							<form role="form" action="cms/insurancePrice/add.action" method="post" id="inform">
									<label>保险费用名称：</label>
									<div class="form-group">
		                     	       <input class="form-control " id="inPriceName" placeholder="保险费用名称" name="inPriceName" type="text" required="required">
									</div>
									<br>
									<br>
									<label>保险费用：</label>
									<div class="form-group">
										<input class="form-control " id="inPrice" placeholder="保险费用" name="inPrice" type="text" required="required">
									</div>
								<div style="text-align: center;">
									<input type="button" value="提交" class="btn btn-primary" onclick="check()">
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
<script type="text/javascript">
function check(){
	var flag = true;
	  $.ajax({
			type:'post',
			async:false,
			data:'inModelName='+$("#inModelName").val(),
			url:'cms/insurancePrice/getInPrice.action',
			success:function(data){
				if(data!=null&&data!=""){
					flag = false;
				}
			}
	   });
	  
	  if($("#inPrice").val()==""){
		   $.alert("请填写保险费用");
		   return;
	  }
	  if(!flag){
		  $.alert("已有该车型");
		  return;
	  }
	  $("#inform").submit();
}
</script>
<%@include file="../common/buttom.jsp"%>
</html>