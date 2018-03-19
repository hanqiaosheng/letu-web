<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>

<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<a class="btn btn-danger"
	   href="javascript:history.go(-1)">返回</a>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<h2 class="page-header">旅行社详情</h2>
						</div>
						<div class="panel-body form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">旅行社名字:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${guideAgency.guideAgencyName}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">旅行社景区:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${guideAgency.guideAgencyScenic}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">旅行社地址:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${guideAgency.guideAgencyAddr}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">所属省:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${province.provinceName}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">所属城市:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${city.cityName}</p>
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
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<h2 class="page-header">旅行社负责人详情</h2>
						</div>
						<div class="panel-body form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">负责人名字:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${principalAdmin.adminRealname}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">负责人电话:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${principalAdmin.adminTel}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">负责人邮箱:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${principalAdmin.adminCreateEmail}</p>
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
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<h2 class="page-header">管理者详情</h2>
						</div>
						<div class="panel-body form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">管理者名字:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${manageAdmin.adminRealname}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">管理者电话:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${manageAdmin.adminTel}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">管理者邮箱:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${manageAdmin.adminCreateEmail}</p>
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
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<h2 class="page-header">创建人详情</h2>
						</div>
						<div class="panel-body form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">创建人名字:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${createAdmin.adminRealname}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">创建人电话:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${createAdmin.adminTel}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">创建人邮箱:</label>
								<div class="col-sm-10">
									<p class="form-control-static">${createAdmin.adminCreateEmail}</p>
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