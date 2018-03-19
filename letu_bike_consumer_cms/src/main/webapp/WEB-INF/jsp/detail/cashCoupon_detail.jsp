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
			<h1 class="page-header">代金券详情</h1>
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
									<label>代金券名：</label>
									<div class="form-group">
										<input class="form-control" value="${cashCoupon.couponName }"  id="couponName" name="couponName"  type="text"
											readonly="readonly">
										</div>
										<label>代金券金额：</label>
										<div class="form-group">
											<input class="form-control" type="text"
												name="couponMoney" readonly="readonly" value="${cashCoupon.couponMoney }">
										</div>
										<label>是否有效期：</label>
										<div class="form-group">
											<input disabled="disabled" type="radio" <c:if test="${cashCoupon.couponIsValidity==0 }">checked="checked"</c:if> name="couponIsValidity" value="1"> 无 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<input disabled="disabled" type="radio" <c:if test="${cashCoupon.couponIsValidity==1 }">checked="checked"</c:if> name="couponIsValidity" value="0"> 有效期  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<input disabled="disabled" type="radio" <c:if test="${cashCoupon.couponIsValidity==2 }">checked="checked"</c:if> name="couponIsValidity" value="2"> 有效时长
										</div>
										<c:if test="${cashCoupon.couponIsValidity==1 }">
										<label>有效期起始时间：</label>
										<div class="form-group">
											<input class="form-control" value="<fmt:formatDate value="${cashCoupon.couponStartTime }" pattern="yyyy-MM-dd"/>" placeholder="有效期起始时间" name="couponStartTime" readonly="readonly">
										</div>
										
										<label>有效期结束时间：</label>
										<div class="form-group">
											<input class="form-control" value=" <fmt:formatDate value="${cashCoupon.couponEndTime }" pattern="yyyy-MM-dd"/>" placeholder="有效期结束时间"  name="couponEndTime" readonly="readonly">
										</div>
										</c:if>
										<c:if test="${cashCoupon.couponIsValidity==2 }">
										<label>有效时长：</label>
										<div class="form-group clearfix">
											<input class="form-control" style="width:30%; float:left;" type="text"
												name="couponValidityTime" readonly="readonly" value="${cashCoupon.couponValidityTime }">
												<div style=" float:left; line-height:20px; padding: 6px 16px;">天</div>
										</div>
										</c:if>
										
										<label>使用条件：</label>
										<div class="form-group">
											<input disabled="disabled" type="radio" <c:if test="${cashCoupon.couponIsCondition==1 }">checked="checked"</c:if> name="couponIsCondition" value="1"> 是 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<input disabled="disabled" type="radio" <c:if test="${cashCoupon.couponIsCondition==0 }">checked="checked"</c:if>  name="couponIsCondition" value="0"> 否
										</div>
										<c:if test="${cashCoupon.couponIsCondition==1 }">
										<label>最低余额：</label>
										<div class="form-group">
											<input class="form-control" id="couponStartMoney" value="${cashCoupon.couponStartMoney }"   placeholder="最低余额,单位（元）"  name="couponStartMoney" readonly="readonly">
										</div>
										</c:if>
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