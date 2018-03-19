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
			<h1 class="page-header">租赁详情</h1>
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
									<label>租赁编号：${bikeRentInfo.rentInfoId }</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>租赁账号：${user.userTel }</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>车辆编号：${bike.bikeCode }</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>租赁状态：
									<c:if test="${bikeRentInfo.rentState==1 }">已完成</c:if>
									<c:if test="${bikeRentInfo.rentState==2 }">未完成</c:if>
									<c:if test="${bikeRentInfo.rentState==3 }">订单失败</c:if>
									</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>租赁费用：<fmt:formatNumber minFractionDigits="2" value="${bikeRentInfo.rentPrice }" /></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>租赁时间：<fmt:formatDate value='${bikeRentInfo.rentStarttime}' pattern='yyyy-MM-dd HH:mm:ss'/></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label>归还时间：<fmt:formatDate value='${bikeRentInfo.rentEndtime}' pattern='yyyy-MM-dd HH:mm:ss'/></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
