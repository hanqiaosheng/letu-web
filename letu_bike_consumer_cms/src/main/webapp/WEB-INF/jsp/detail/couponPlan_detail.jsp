<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/datePicker.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">代金券方案详情</h1>
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
								<div class="form-group">
									<label>代金券方案名：</label>
									<div class="form-group">
										<input class="form-control" value="${couponPlan.couponPlanName }"  id="couponPlanName" name="couponPlanName"  type="text"
											readonly="readonly">
										</div>
										<label>代金券生成时间：</label>
										<div class="form-group">
											<input class="form-control" value="<fmt:formatDate value="${couponPlan.couponPlanCreateTime }" pattern="yyyy-MM-dd"/>" name="couponPlanCreateTime" readonly="readonly">
										</div>
										<c:forEach items="${couponPlansList}" varStatus="status" var="list">
											<dl>
												<dt>代金券${status.index+1}:</dt>
												<dd>
													<div class="form-group">
								      					<label>绑定代金券：</label>
								         				<input class="form-control" type="text"   value="${list.cashCoupon.couponName}" name="couponName" readonly="readonly">
								      				</div>
												</dd>
												<dd>
													<div class="form-group">
								      					<label>单次赠送数量：</label>
								         				<input class="form-control" type="text"   value="${list.num}" name="num" readonly="readonly">
								      				</div>
												</dd>
											</dl>
										</c:forEach>
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