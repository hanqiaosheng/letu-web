<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">还车点</h1>
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
							<form role="form" action="cms/fixedReturn/perfectDetail.action"  method="post">
							<input type="hidden" name="fixedReturnId" value="${fixedReturn.fixedReturnId }">
							<label>还车点名字：</label>
								<div class="form-group">
									<input class="form-control"  placeholder="还车点名字" name="fixedReturnName" type="text"
										required="required">
								</div>
								<br>
							<%-- 	<label>渠道名 ：</label>
							    <select class="form-control" name="fixedReturnChannelId">
							    <c:forEach var="channel" items="${channelList }">
							    <option value="${channel.channelId }" >${channel.channelName }</option>
							    </c:forEach>
							    </select>
							    <br> --%>
								<label>简介：</label>
								<div class="form-group">
									<textarea class="form-control" name="fixedReturnBrief" rows="" cols=""></textarea>
								</div>
								<br>
								<label>管理员号码：</label><a style="color: red">多个号码请用逗号分隔</a>
								<div class="form-group">
									<input class="form-control" placeholder="管理员号码" type="text" name="fixedReturnTel" required="required">
								</div>
								<br>
								<label>还车点位置：</label>
								<div class="form-group">
									<input class="form-control go-back" placeholder="还车点位置" type="text" name="latlng" value="${fixedReturn.fixedReturnLng },${fixedReturn.fixedReturnLat }" required="required">
								</div>
								<br>
								<label>可用车数量：</label>
								<div class="form-group">
									<input class="form-control" placeholder="可用车数量" type="text" name="fixedReturnBikeNum" required="required" >
								</div>
								<br>
								<label>还车点范围距离（米）：</label>
								<div class="form-group">
									<input class="form-control" placeholder="还车点范围距离（米）" type="text" name="fixedReturnDistance" required="required">
								</div>
								
								<div style="text-align: center;">
									<input type="submit" value="确认并审核通过" class="btn btn-primary">
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
<%@include file="../common/maskMap.jsp"%>

</html>
