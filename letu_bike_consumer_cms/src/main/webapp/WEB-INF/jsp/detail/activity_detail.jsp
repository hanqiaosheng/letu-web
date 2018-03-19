<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<!-- <script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'bikeId='+obj,
					url:'cms/bike/deleteBike.action',
					success:function(data){
						if(data!=""){
							window.location.href="${basePath}/cms/bike/bikeList.action";
						}
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
</script> -->
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">活动详情</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
				
					<div class="row">
					
						<div class="panel-body form-horizontal">
						<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">活动标题：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${activity.activityName  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">置顶号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${activity.activityTopnum }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">城市：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${city.cityName }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">活动简介：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${activity.activityInstruction }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">封面：</label>
								<div class="col-sm-10">
								<img alt="" src="upload/${activity.activityImage }">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">链接：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${activity.activityUrl }</p>
								</div>
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
</html>
