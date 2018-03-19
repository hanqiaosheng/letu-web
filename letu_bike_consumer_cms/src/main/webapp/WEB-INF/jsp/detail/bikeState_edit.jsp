<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">车辆状态修改</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body form-horizontal">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">车辆编号：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${bike.bikeCode }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">车型：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${bike.models.modelsName }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">车辆状态选择：</label>
					    <div class="col-sm-10">
					    <form action="cms/bike/updateBikeState.action">
						<input type="hidden" name="bikeId" value="${bike.bikeId }">
							<select name="bikeState" class="form-control" style="width: 200px">
							  <option value="0"<c:if test="${bikeState==0 }">selected="selected"</c:if>>空闲中</option>
							  <option value="1"<c:if test="${bikeState==1 }">selected="selected"</c:if>>临时还车</option>
							  <option value="2"<c:if test="${bikeState==2 }">selected="selected"</c:if>>租借中</option>
							  <option value="3"<c:if test="${bikeState==3 }">selected="selected"</c:if>>维护中</option>
							  <option value="4"<c:if test="${bikeState==4 }">selected="selected"</c:if>>锁定中</option>
							  <option value="5"<c:if test="${bikeState==5 }">selected="selected"</c:if>>冻结中</option>
							</select>
							<br>
							<input class="btn btn-primary" value="提交" type="submit">
							<a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
						</form>		
					    </div>
					</div>
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
