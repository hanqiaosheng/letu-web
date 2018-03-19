<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">修改保险费用</h1>
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
							<form role="form" action="cms/insurancePrice/edit.action"  method="post" >
								<div class="form-group">
									<label>保险费用名称：</label>
									<div class="form-group">
		                     	       <input class="form-control " id="inPriceName" placeholder="保险费用名称" value="${insurancePrice.inPriceName }" name="inPriceName" type="text" required="required">
									</div>
									<br>
										<input type="hidden" value="${insurancePrice.inPriceId }" name="inPriceId">
										<br>
										<br>
									<label>保险费用：</label>
									<div class="form-group">
										<input class="form-control" placeholder="保险费用" value="${insurancePrice.inPrice }" name="inPrice" type="text"
											required="required">
									</div>
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