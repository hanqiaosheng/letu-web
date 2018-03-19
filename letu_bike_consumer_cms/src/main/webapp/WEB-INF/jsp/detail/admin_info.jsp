<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">编辑信息</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/admin/editInfo.action" method="post">
								<input type="hidden" name="stuId" value="${student.stuId }">
								<div class="form-group">
										<input class="form-control" placeholder="用户名" name="adminId" type="hidden"
												required="required" value="${admin.adminId }">
										<label>用户名:</label>
										<div class="form-group">
											<input class="form-control" placeholder="用户名" name="adminUsername" type="text"
												required="required" value="${admin.adminUsername }" disabled="disabled">
										</div>
										<label>姓名:</label>
										<div class="form-group">
											<input class="form-control" placeholder="姓名" name="adminRealname" type="text"
												required="required" value="${admin.adminRealname }">
										</div>
										<label>角色:</label>
										<div class="form-group">
											<c:forEach var="role" items="${admin.roles }">
												<input class="form-control" placeholder="角色" type="text"
													value="${role.roleName }" disabled="disabled">
											</c:forEach>
										</div>
										<shiro:hasRole name="公司">
											<input class="form-control" placeholder="用户名" name="companyId" type="hidden"
												required="required" value="${admin.company.companyId }">
											<label>公司名称：</label> 
											<div class='form-group'>
												<input class='form-control' placeholder='公司名称' type='text' name='companyName' 
												required='required' value="${admin.company.companyName }">
											</div>
											<label>公司地址：</label> 
											<div class='form-group'>
												<input class='form-control' placeholder='公司地址' type='text' name='companyAddress' 
												required='required' value="${admin.company.companyAddress }">
											</div>
											<label>联系方式：</label> 
											<div class='form-group'>
												<input class='form-control' placeholder='联系方式' type='text' name='cpmpanyTel' 
												required='required' value="${admin.company.cpmpanyTel }">
											</div>
											<label>公司邮箱：</label> 
											<div class='form-group'>
												<input class='form-control' placeholder='公司邮箱' type='text' name='companyEmail' 
												required='required' value="${admin.company.companyEmail }">
											</div>
										</shiro:hasRole>
									</div>
								<div style="text-align: center;">
									<input type="submit" value="提交" class="btn btn-primary">
								</div>
							</form>
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
