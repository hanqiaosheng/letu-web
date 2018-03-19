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
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">套餐详情</h1>
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
								<label for="inputEmail3" class="col-sm-2 control-label">景点门票：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${scenicSpot.scenicSpotName  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">套餐标题：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${discountPackage.discountPackageName  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">置顶号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${discountPackage.discountPackageTopNum  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">置顶号：</label>
								<div class="col-sm-10">
								   <c:if test="${discountPackage.discountPackageState==1  }">
									<p class="form-control-static">未上线</p>
									</c:if>
									<c:if test="${discountPackage.discountPackageState==2  }">
									<p class="form-control-static">已上线</p>
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">套餐标签：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${discountPackage.discountPackageTag }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">套餐价格（元）：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${discountPackage.discountPackagePrice }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">套餐优惠价格（元）：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${discountPackage.discountPackagePreferentialPrice }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">购买时间限制：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${discountPackage.discountPackageBuyLimit }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">详细说明：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${discountPackage.discountPackageDetail }</p>
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
