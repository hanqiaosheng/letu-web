<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<%@include file="../common/datePicker.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加代金券</h1>
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
							<form role="form" action="cms/cashcoupon/addCashCoupon.action" id="couponForm" method="post">
								<div class="form-group">
									<label>代金券名字：</label>
									<div class="form-group">
										<input class="form-control" placeholder="代金券名字"  id="couponName" name="couponName"  type="text"
											required="required">
										</div>
										<label>代金券金额：</label>
										<div class="form-group">
											<input class="form-control" id="couponMoney" placeholder="代金券金额" type="text"
												name="couponMoney" required="required">
										</div>
										<label>是否有效期：</label>
										<div class="form-group">
											<input type="radio" name="couponIsValidity" checked="checked" onclick="showTime(0)" value="0"> 无 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<input type="radio"  onclick="showTime(1)" name="couponIsValidity" value="1"> 有效期&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<input type="radio"  onclick="showTime(2)" name="couponIsValidity" value="2"> 有效时长
										</div>
										<div  id="timeShow">
										
										</div>
										
										
										<label>使用条件：</label>
										<div class="form-group">
											<input type="radio" onclick="showCodition(1)" name="couponIsCondition" value="1"> 是 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<input type="radio" onclick="showCodition(0)" checked="checked" name="couponIsCondition" value="0"> 否
										</div>
										<div id="showCodition">
										
										
										
										
										</div>
										
									
								<div style="text-align: center;">
									<input type="button" onclick="subCoupon()" value="提交" class="btn btn-primary">
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
	function showTime(obj){
		
		var html1 = '<div>'+
					'<label>有效期起始时间：</label>'+
					'<div class="form-group">'+
					'<input class="form-control" id="cStarttime1" placeholder="有效期起始时间" readonly="readonly" name="couponStartTime">'+
					'</div>'+
					'<label>有效期结束时间：</label>'+
					'<div class="form-group">'+
					'<input class="form-control" id="cEndtime1" placeholder="有效期结束时间" readonly="readonly" name="couponEndTime">'+
					'</div>'+
					'</div>'
		
					
		var html2 = 	'<div>'+
						'<label>有效时长：</label>'+
						'<div class="form-group clearfix">'+
						'<input class="form-control" id="couponValidityTime" style="width:30%; float:left;" placeholder="有效时长" type="text" name="couponValidityTime" >'+
						'<div style=" float:left; line-height:20px; padding: 6px 16px;">天</div>'+
						'</div>'+
						'</div>	'	
		if(0==obj){
			$("#timeShow").empty();
		}else if(1==obj){
			$("#timeShow").empty();
			$("#timeShow").html(html1);
		}else if(2==obj){
			$("#timeShow").empty();
			$("#timeShow").html(html2);
		}
						
		$('#cStarttime1').datepicker({
			changeMonth: true, 
			changeYear: true, 
			showMonthAfterYear: true,
			dateFormat: "yy-mm-dd",
			onSelect:function(dateText,inst){
	           $("#cEndtime1").datepicker("option","minDate",dateText);
			}
			
		});
		$('#cEndtime1').datepicker({
			changeMonth: true, 
			changeYear: true, 
			showMonthAfterYear: true,
			dateFormat: "yy-mm-dd",
			onSelect:function(dateText,inst){
	            $("#cStarttime1").datepicker("option","maxDate",dateText);
	        }
		});
	}
	


	function showCodition(obj){
		if(0==obj){
			$("#showCodition").empty();
		}else{
			var html = '<label>最低余额：</label>'+
						'<div class="form-group">'+
						'<input class="form-control" id="couponStartMoney"   placeholder="最低余额,单位（元）"  name="couponStartMoney" required="required">'+
						'</div>'
			$("#showCodition").html(html);	
		}
	}
	
										
	function subCoupon(){
		if($("#couponName").val()==""){
			$.alert("请输入代金券名字");
			return;
		}
		if($("#couponMoney").val()==""){
			$.alert("请输入代金券金额");
			return;
		}
		if($("#cStarttime1").val()==""){
			$.alert("请输入有效期起始时间");
			return;
		}
		if($("#cEndtime1").val()==""){
			$.alert("请输入有效期结束时间");
			return;
		}
		if($("#couponValidityTime").val()==""){
			$.alert("请输入有效时长");
			return;
		} 
		if($("#couponStartMoney").val()==""){
			$.alert("请输入最低余额");
			return;
		} 
		
		$("#couponForm").submit();
		
	}




</script>
<%@include file="../common/buttom.jsp"%>
</html>