<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>\
<script type="text/javascript" src="js/search.js"></script>
<%@include file="common/datePicker.jsp"%>

<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">app版本</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/user/updateBlacklist.action?userIsblacklist=0" id="checkAllForm" method="post">
								<input type="hidden" name="defriendReason" id="defriendReason">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>平台</th>
											<th>版本号</th>
											<th>是否强制更新</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
											<tr class="odd gradeA">
												<td>安卓</td>
												<td>${androidApp.sysParamentValue }</td>
												<td><c:if test="${androidApp.sysParamentIsUpdate==0 }">不强制</c:if>
												<c:if test="${androidApp.sysParamentIsUpdate==1 }">强制</c:if></td>
												<td><a class="btn btn-primary"
															href="cms/app/editJsp.action?sysParamentId=${androidApp.sysParamentId }" >修改</a></td>
											</tr>
											<tr class="odd gradeA">
												<td>IOS</td>
												<td>${iosApp.sysParamentValue }</td>
												<td><c:if test="${iosApp.sysParamentIsUpdate==0 }">不强制</c:if>
												<c:if test="${iosApp.sysParamentIsUpdate==1 }">强制</c:if></td>
												<td><a class="btn btn-primary"
															href="cms/app/editJsp.action?sysParamentId=${iosApp.sysParamentId }" >修改</a></td>
											</tr>
									</tbody>
								</table>
								
							</form>
							
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>
	<%@include file="common/buttom.jsp"%>
</body>
</html>

