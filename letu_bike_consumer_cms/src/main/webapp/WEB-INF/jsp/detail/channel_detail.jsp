<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<body>
	<%@include file="../common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">渠道详情</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					<form action="cms/channel/channelDetail.action" method="post" id="searchForm" class="form-inline">
					
					<input type="hidden" id="channelId" name="channelId" value="${channel.channelId }">
					<input type="hidden" id="pageIndex" name="pageIndex">
					</form>
						<div class="panel-heading">
						渠道编号:${channel.channelId }&nbsp;&nbsp;&nbsp;渠道负责人:${channel.channelChargerName }
						&nbsp;&nbsp;&nbsp;联系方式: ${channel.channelChargerTel }&nbsp;&nbsp;&nbsp;渠道名：${channel.channelName }
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTab
							le_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>车辆编号</th>
											<th>车辆型号</th>
											<th>负责人</th>
											<th>联系电话</th>
											<th>投放时间</th>
											<th>投放地点</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${bikeList }" var="bike">
											<tr class="odd gradeA">
												<td>${bike.bikeCode }</td>
												<td>${bike.models.modelsName }</td>
												<td>${bike.bikeManagerName }</td>
												<td>${bike.bikeManagerTel }</td>
												<td><fmt:formatDate value="${bike.bikePutTime}" pattern="yyyy-MM-dd"/> </td></td>
												<td>${bike.bikeAddress }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<%@include file="../common/pageUtil.jsp"%>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>

	<%@include file="../common/buttom.jsp"%>
</body>
</html>

