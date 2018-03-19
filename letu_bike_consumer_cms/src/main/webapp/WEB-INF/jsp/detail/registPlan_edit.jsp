<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" type="text/css" href="datepicker/jquery.datetimepicker.css"/>
<%@include file="../common/body.jsp"%>



<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">兑换方案添加</h1>
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
							<form role="form" action="cms/redeemplan/editRedeemPlan.action"  method="post">
							<input type="hidden" value="${redeemPlan.planId }" name="planId">
								<label>标题：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="planName" value="${redeemPlan.planName }" required="required">
								</div>
								
								<label>绑定代金券方案：</label>
								<div class="form-group">
									<select name="planCouponPlanId" class="form-control">
									<c:forEach items="${couponPlanList }" var="couponPlan">
								     <option value="${couponPlan.couponPlanId }"<c:if test="${couponPlan.couponPlanId==redeemPlan.planCouponPlanId }">selected="selected"</c:if>>${couponPlan.couponPlanName }</option>
								    </c:forEach>
									</select>
								</div>
								<br>
								<div style="text-align: center;">
									<input type="submit" value="确定" class="btn btn-primary">
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



<script src="datepicker/jquery.datetimepicker.full.js"></script>

<script type="text/javascript">
 	
$('#datetimepicker5').datetimepicker({
	datepicker:false,
	allowTimes:['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00','23:59'],
	step:5
});
$('#datetimepicker6').datetimepicker({
	datepicker:false,
	allowTimes:['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00','23:59'],
	step:5
});
</script>
<!-- 引入css和js即可 -->
</html>
