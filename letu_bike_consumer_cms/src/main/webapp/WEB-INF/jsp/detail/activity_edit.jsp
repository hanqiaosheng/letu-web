<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">修改活动</h1>
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
							<form role="form" action="cms/activity/edit.action" method="post" enctype="multipart/form-data">
							<input type="hidden" value="${activity.activityId }" name="activityId">
								<div class="form-group">
									<label>活动标题：</label>
									<div class="form-group">
										<input class="form-control" placeholder="活动名称" value="${activity.activityName }" name="activityName"  type="text"
											required="required">
								    </div>
									<br>
									<label>置顶号：</label>
									<div class="form-group">
										<input class="form-control" placeholder="置顶号" value="${activity.activityTopnum }" name="activityTopnum"  type="text"
											required="required">
								    </div>
									<br>
									<label>所属城市：</label>
									<div class="form-group">
									   <select id="activityCityId" class="form-control" name="activityCityId" required="required">
			                     		<c:forEach var="city" items="${cityList}">
		                   					<option value="${city.cityId }" <c:if test="${city.cityId==activity.activityCityId }">selected</c:if>>
		                     					${city.cityName }
			                     			</option>
			                     		</c:forEach>
		                    	       </select>
								    </div>
									<br>
									<label>活动简介：</label>
									<div class="form-group">
										<textarea name="activityInstruction" rows="10" cols="20" class="form-control">${activity.activityInstruction }</textarea>
									</div>
									<br>
									<label>封面：</label>
									<div class="form-group">
									     <img alt="" src="upload/${activity.activityImage }">
										 <input class="form-control" type="file" name="file">
								    </div>
									<br>
									<label>链接：</label>
									<div class="form-group">
										<input class="form-control" placeholder="链接" value="${activity.activityUrl }" name="activityUrl"  type="text"
											required="required">
								    </div>
									<br>
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