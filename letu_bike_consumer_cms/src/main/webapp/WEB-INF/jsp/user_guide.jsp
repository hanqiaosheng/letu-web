<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">用户指南</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>类别</th>
											<th>编辑人</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userGuideList}" var="userGuide">
											<tr class="odd gradeA">
												<td>${userGuide.userGuideId }</td>
												<td class="center">
                                                   ${userGuide.userGuideType }
												</td>
												<td class="center">${userGuide.editAdminName }</td>
												<td class="center">
													<a class="btn btn-primary" href="cms/user/guide/editJsp.action?userGuideId=${userGuide.userGuideId }" >修改</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							
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

