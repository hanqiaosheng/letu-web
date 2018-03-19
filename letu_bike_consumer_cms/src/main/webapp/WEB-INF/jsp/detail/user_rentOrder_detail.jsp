<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
		    <c:if test="${flag==1 }">
			<h1 class="page-header">租赁地点</h1>
			</c:if>
			<c:if test="${flag==2 }">
			<h1 class="page-header">归还地点</h1>
			</c:if>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
								<div class="form-group">
									<label>租赁编号：${bikeRentInfo.rentInfoId }</label>
									<label>账号：${user.userTel }</label>
									<label>姓名：${user.userRealname }</label>
									<label>租赁时间：<fmt:formatDate value='${bikeRentInfo.rentStarttime}' pattern='yyyy-MM-dd HH:mm'/></label>
									<label>归还时间：<fmt:formatDate value='${bikeRentInfo.rentEndtime}' pattern='yyyy-MM-dd HH:mm'/></label>
								</div>
								
						</div>
						<!-- /.col-lg-6 (nested) -->
					</div>
					<!-- /.row (nested) -->
					<div id="container" class="form-control"></div>
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
<%@include file="../common/routeMap.jsp"%>
</html>
