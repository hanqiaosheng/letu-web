<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" type="text/css" href="datepicker/jquery.datetimepicker.css"/>
<style>
.col-lg-6 {
    width: 90%;
}
#add{background:dodgerblue; border:none; border-radius:5px; color:#fff; width:100px; height:30px; margin-bottom:20px;}
.del{ margin-top:26px; background: #d9534f;border:none; border-radius:5px; color:#fff; width:100px; height:30px; margin-bottom:20px;}
.list-item .form-group{ width:35%; float:left; margin-right:40px;}
</style>
<%@include file="../common/body.jsp"%>



<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">新增代金券方案</h1>
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
							<form role="form" action="cms/couponplan/addCouponPlan.action"  method="post">
								<label>代金券方案名称：</label>
								<div class="form-group">
									<input class="form-control" type="text" name="couponPlanName" required="required">
								</div>
								
								<br>
								<button id='add'>添加代金券</button>
								<br>
								<div class="form-list">
								   <div class="list-item clearfix">
								      <div class="form-group">
								      <label>绑定代金券 ：</label>
								         <select name="cashCouponIds" class="form-control">
								         <option value="">请选择</option>
								         <c:forEach items="${cashCouponList }" var="cashCoupon">
								            <option value="${cashCoupon.couponId }">${cashCoupon.couponName }</option>
								         </c:forEach>
								         </select>
								      </div>
								      <div class="form-group">
								      	<label>单次赠送数量：</label>
								         <input class="form-control" type="text" name="couponSendNums" required="required">
								      </div>
								      <!-- <button class="del">删除</button> -->
								   </div>
								</div>
								
								
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
$('#add').click(function(e){
	e.preventDefault();
   var length=$('.list-item').length;
   $('.form-list').append('<div class="list-item clearfix">'+
		      '<div class="form-group">'+
		      '<label>绑定优惠券：</label>'+
		         '<select name="cashCouponIds" class="form-control">'+
		         '<option value="">请选择</option>'+
		         '<c:forEach items="${cashCouponList }" var="cashCoupon">'+
		            '<option value="${cashCoupon.couponId }">${cashCoupon.couponName }</option>'+
		         '</c:forEach>'+
		         '</select>'+
		      '</div>'+
		      '<div class="form-group">'+
		      	'<label>单次赠送数量：</label>'+
		         '<input class="form-control" type="text" name="couponSendNums" required="required">'+
		      '</div>'+
		      '<button class="del">删除</button>'+
		   '</div>');
})
 
	
	
   $(function(){
		$('.form-list').on({
			'click':function(){
				$(this).parents('.list-item').remove()	
			}
		},'.del');
	})




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
