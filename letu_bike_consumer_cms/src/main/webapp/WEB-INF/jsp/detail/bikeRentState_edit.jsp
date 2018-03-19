<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<%@include file="../common/datePicker.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">车辆租赁状态修改</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body form-horizontal">
					<form action="cms/order/editBikeRentState.action" >
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">租赁编号：</label>
						    <div class="col-sm-10">
						      <p class="form-control-static">${bikeRentInfo.rentInfoId }</p>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">租赁时间：</label>
						    <div class="col-sm-10">
						      <p class="form-control-static"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bikeRentInfo.rentStarttime }"/></p>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">租赁账号：</label>
						    <div class="col-sm-10">
						      <p class="form-control-static">${user.userTel }</p>
						    </div>
						</div>
						<c:if test="${!empty bikeRentInfo.rentPrice }">
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">租赁费用：</label>
						    <div class="col-sm-10">
						      <p class="form-control-static"><fmt:formatNumber minFractionDigits="2" value="${bikeRentInfo.rentPrice }" />元</p>
						    </div>
						</div>
						</c:if>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">车辆编号：</label>
						    <div class="col-sm-10">
						      <p class="form-control-static">${bikeRentInfo.bike.bikeCode }</p>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">车锁状态：</label>
						    <div class="col-sm-10">
						      <p class="form-control-static">
						       <c:if test="${bikeLockInfo.bikeLockStatus==0 }">已锁</c:if>
						       <c:if test="${bikeLockInfo.bikeLockStatus==1 }">未锁</c:if>
						       <c:if test="${bikeLockInfo.bikeLockStatus==2 }">受阻</c:if>
						      </p>
						    </div>
						</div>
						<div class="form-group">
						
							<input type="hidden" name=rentInfoId value="${bikeRentInfo.rentInfoId }">
							<input type="hidden" name=userId value="${user.userId }"> 
							<input type="hidden" name=bikeId value="${bike.bikeId }">  
							<input type="hidden" name=flag value="${gotoFlag }">
							<label for="inputEmail3" class="col-sm-2 control-label">车辆状态：</label>
							<div class="col-sm-10 form-inline">
								<select name="bikeState" class="form-control" style="width:200px">
									<option value="0"<c:if test="${bike.bikeState==0 }">selected="selected"</c:if>>空闲中</option>
									<option value="1"<c:if test="${bike.bikeState==1 }">selected="selected"</c:if>>临时还车</option>
									<option value="2"<c:if test="${bike.bikeState==2 }">selected="selected"</c:if>>租借中</option>
									<option value="3"<c:if test="${bike.bikeState==3 }">selected="selected"</c:if>>维护中</option>
									<option value="4"<c:if test="${bike.bikeState==4 }">selected="selected"</c:if>>锁定中</option>
								</select>
								<a class="btn btn-primary" href="cms/bike/bikeList.action?bikeId=${bike.bikeId }">详情</a>
								<br>	
							</div>
							<span style="color: red;margin-left: 115px">注：请查看车辆详情后，确保车锁状态为关闭状态，或者车辆已经被其他用户租借后，进行操作</span>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">用户状态：</label>
							<div class="col-sm-10 form-inline">
								<select name="userState" class="form-control" style="width:200px">
									<option value="0"<c:if test="${user.userState==0 }">selected="selected"</c:if>>空闲</option>
									<option value="1"<c:if test="${user.userState==1 }">selected="selected"</c:if>>租借</option>
								</select>
								<a class="btn btn-primary" href="cms/user/list.action?userId=${user.userId }">详情</a>
								<br>	
							</div>
							<span style="color: red;margin-left: 115px">注：请查看用户租赁详情后，确保该租赁订单为用户最后一条订单，再修改用户状态</span>
						</div>
						<div class="form-group">	
							<label for="inputEmail3" class="col-sm-2 control-label">订单状态：</label>
							<div class="col-sm-10 form-inline">
								<select name="rentState" class="form-control" style="width:200px">
									<option value="1"
										<c:if test="${bikeRentInfo.rentState==1 }">selected="selected"</c:if>>已完成</option>
									<option value="2"
										<c:if test="${bikeRentInfo.rentState==2 }">selected="selected"</c:if>>未完成</option>
									<option value="3"
										<c:if test="${bikeRentInfo.rentState==3 }">selected="selected"</c:if>>订单失败</option>
								</select>
								 <a class="btn btn-primary" href="cms/bikeRentInfo/bikeRentInfoDetail.action?bikeRentId=${bikeRentInfo.rentInfoId }">详情</a>
								<br>	
							</div>	
						</div>
						<div class="form-group">	
							<label for="inputEmail3" class="col-sm-2 control-label">租赁结束时间：</label>
							<div class="col-sm-10">
							  <input required="required" class="form-control" style="width:200px" type="text" id="cEndtimeD" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bikeRentInfo.rentEndtime }"/>" name="rentEndtime">
							  <br>
							  <br>
							  <input class="btn btn-primary" value="提交" type="submit">
							  <a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
							</div>			
						</div>
					</form>
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
