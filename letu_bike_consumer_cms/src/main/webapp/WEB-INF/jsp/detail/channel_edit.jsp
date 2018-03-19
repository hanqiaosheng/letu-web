<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">修改渠道</h1>
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
							<form role="form" action="cms/channel/channelUpdate.action"  method="post">
							<input type="hidden" name="channelId" value="${channel.channelId }">
							<div id="channnelStr"> 
							
							
							</div>
							<label>渠道级别：</label>
									<select class="form-control" id="channelLevel"  onchange="changeChannel()" readonly="readonly">
									<option <c:if test="${channel.channelLevel==1 }">selected="selected"</c:if> value="1">一级</option>
									<option <c:if test="${channel.channelLevel==2 }">selected="selected"</c:if> value="2">二级</option>
									</select>
								<br>
								<label>上级渠道选择：</label>
									<select class="form-control" id="parentChannel" readonly="readonly">
									<c:if test="${pChannel!=null }">
									<option>${pChannel.channelName }</option>
									</c:if>
									<c:if test="${pChannel==null }">
									<option>无</option>
									</c:if>
									</select>
								<br>
								<label>渠道名：</label>
								<div class="form-group">
									<input class="form-control"  placeholder="渠道名" value="${channel.channelName}" id="channelName" name="channelName" type="text"
										required="required">
								</div>
								<br>
								<label>渠道负责人：</label>
								<div class="form-group">
									<input class="form-control" placeholder="渠道负责人" value="${channel.channelChargerName}" type="text" name="channelChargerName" required="required">
								</div>
								<br>
								<%-- <label>所属城市：</label>
							    <select class="form-control" id="channelCityId" name="channelCityId">
								<c:forEach var="city" items="${cityList }">
								  <option value="${city.cityId }" <c:if test="${city.cityId == channel.channelCityId }"></c:if>>${city.cityName }</option>
								</c:forEach>
								</select>
								<br> --%>
								<label>联系方式：</label>
								<div class="form-group">
									<input class="form-control" placeholder="联系方式" value="${channel.channelChargerTel}" type="text" name="channelChargerTel" required="required">
								</div>
								<br>
								<label>简介：</label>
								<div class="form-group">
									<textarea class="form-control" name="channelIntroduction" rows="" cols="">${channel.channelIntroduction}</textarea>
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
