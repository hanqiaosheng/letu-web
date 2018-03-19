<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">用户账户编辑</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" id="Form" action="cms/user/account/editAccount.action" method="post">
							<label style="font-size: medium;">用户帐号：${user.userTel }</label>
								
								<br>
								
								<input type="hidden" name="accountId" value="${account.accountId }">
								<%-- <label>余额：</label>
								<div class="form-group">
									<input class="form-control" placeholder="余额" value="${account.accountAvailableBalance }" type="text" name="accountAvailableBalance" required="required">
							    </div> --%>
								
								<label>预付款金额：</label>
								<div class="form-group">
									<input class="form-control" placeholder="预付款金额" value="${account.accountDeposit }" type="text" name="accountDeposit" required="required">
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
