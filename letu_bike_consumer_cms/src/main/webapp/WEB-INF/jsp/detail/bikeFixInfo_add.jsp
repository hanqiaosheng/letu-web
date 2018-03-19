<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/datePicker.jsp"%>
<link rel="stylesheet" type="text/css" href="datepicker/jquery.datetimepicker.css"/>
<link rel="stylesheet" href="../map/maskMap.css">
<script type="text/javascript">
function newBatch(){
	  $.ajax({
			 type:'post',
			 url:'cms/bikeFixInfo/maxBatchNumber.action',
			 success:function(data){
				 if($("#batchButton").val()=="新建批次"){
					 $("#hideBatchNumber").attr("type","text");
					 $("#hideBatchNumber").val(data);
					 $("#fixBatchNumber").hide();
					 $("#fixBatchNumber").attr("name","");
					 $("#batchButton").val("重新选择");
				 }else{
					 $("#hideBatchNumber").attr("type","hidden");
					 $("#fixBatchNumber").show();
					 $("#hideBatchNumber").attr("name","fixBatchNumber");
					 $("#batchButton").val("新建批次");
				 }
				 
			 }
		 });
}
</script>


<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加维护信息</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/bikeFixInfo/addBikeFixInfo.action"  method="post">
								<label>车辆编号：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="bikeCode" required="required">
								</div>
								<label>批次号：</label>
								<div class="form-group">
									<select name="fixBatchNumber" id=fixBatchNumber class="form-control" required="required">
										<c:forEach items="${bikeBatchNumberList }" var="batchNumber">
											<option value="${batchNumber.fixBatchNumber }">${batchNumber.fixBatchNumber }</option>
										</c:forEach>
									</select>
									<input type="hidden" id="hideBatchNumber" name="fixBatchNumber" readonly="readonly" class="form-control" >
								</div>
								<div class="form-group">
								<input class="btn btn-success" id="batchButton" onclick="newBatch()" type="button" value="新建批次">
								</div>
								<label>修理人员姓名：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="fixMan" required="required">
								</div>
								<label>修理人员电话：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="fixTel" required="required">
								</div>
								<label>修理开始时间：</label>
								<div class="form-group">
									<input class="form-control" id="cStarttime" type="text" name="fixStarttime" required="required">
								</div>
								<label>修理结束时间：</label>
								<div class="form-group">
									<input class="form-control" id="cEndtime" type="text" name="fixEndtime" required="required">
								</div>
								<label>修理备注说明：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="fixRemark" required="required">
								</div>
								<label>维护地址：</label>
								<div class="form-group">
									<input class="form-control go-back" type="text" name="fixAddress" required="required">
								</div>
								<div style="text-align: center;">
									<input type="submit" value="添加" class="btn btn-primary">
									<a class="btn btn-danger" href="javascript:history.go(-1)">取消</a>
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
<%@include file="../common/maskMap.jsp"%>
</html>
