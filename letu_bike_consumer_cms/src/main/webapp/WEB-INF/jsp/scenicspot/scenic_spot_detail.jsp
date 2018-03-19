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
			<h1 class="page-header">景区详情</h1>
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
								<label for="inputEmail3" class="col-sm-2 control-label">标题：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${scenicSpot.scenicSpotName  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">标签：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${scenicSpot.scenicSpotTag }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">城市：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${city.cityName }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">是否全国显示：</label>
								<div class="col-sm-10">
								<c:if test="${scenicSpot.scenicSpotIsAll==0 }">
									<p class="form-control-static">否</p>
								</c:if>
								<c:if test="${scenicSpot.scenicSpotIsAll==1 }">
									<p class="form-control-static">是</p>
								</c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">置顶号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${scenicSpot.scenicSpotTopNum }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点经纬度：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${scenicSpot.scenicSpotLng }</p>
								</div>
							</div>
							<%-- <div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点距离（km）：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${scenicSpot.scenicSpotDistance }</p>
								</div>
							</div> --%>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点图片：</label>
								<div class="col-sm-10">
								<img width="100px" alt="" src="${scenicSpot.scenicSpotImage }">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点详情图片：</label>
								<div class="col-sm-10">
								<img width="500px" alt="" src="${scenicSpot.scenicSpotDetailImage }">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点开放时间：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${scenicSpot.scenicSpotOpenTime }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点相关政策规定：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${scenicSpot.scenicSpotPolicy }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点温馨提示：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${scenicSpot.scenicSpotMsg }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">景点介绍：</label>
								<div class="col-sm-10">
								<p class="form-control-static">${scenicSpot.scenicSpotContent }</p>
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
