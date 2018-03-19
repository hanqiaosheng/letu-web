<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">用户状态修改</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
						<form action="cms/user/editState.action">
						<input type="hidden" name=userId value="${user.userId }">
								<div class="form-group">
									<select name="userState" class="form-control">
									 <option value="0"<c:if test="${user.userState==0 }">selected="selected"</c:if>>空闲</option>
									 <option value="1"<c:if test="${user.userState==1 }">selected="selected"</c:if>>租借中</option>	
									 <%--  <option value="3"<c:if test="${user.userState==3 }">selected="selected"</c:if>>临时租借</option> --%>					
									</select>
								</div>
								<input class="btn btn-primary" value="提交" type="submit">
								<a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
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
