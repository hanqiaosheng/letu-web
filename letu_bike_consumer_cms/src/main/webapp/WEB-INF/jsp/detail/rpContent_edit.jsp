<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript">
function editContent(){
	var pContent = $("#pContent").val();
	$.ajax({
		type:'post',
		data:'pContent='+pContent,
		url:'cms/rechargeplan/editpContent.action',
		success:function(data){
			alert("修改成功");
				window.location.reload();
		}
	});
}

</script>
<%@include file="../common/body.jsp"%>

<link rel="stylesheet" type="text/css" href="datepicker/jquery.datetimepicker.css"/>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">优惠内容显示</h1>
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
								<div class="form-group">
									<textarea style="text-align: center;overflow-x:hidden; resize : none; width:500px;height:260px;" id="pContent" rows="15" cols="50" >              ${preferential.preferentialContent }</textarea>
								</div>
								<div style="text-align: center;">
									<input onclick="editContent()" type="button" value="修改" class="btn btn-primary">
								</div>
						
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
