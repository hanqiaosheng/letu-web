<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>


<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">租赁费用修改</h1>
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
							<form role="form" action="cms/order/editRentPlan.action"  method="post">
							<input type="hidden"   name="rentPlanQuantum"  value="00:00-24:00">
								<input type="hidden" value="${rentPlan.rentPlanId }" name="rentPlanId">
								<label>渠道号 ： ${channel.channelId }</label><br>
							
								<label>渠道名称 ：${channel.channelName }</label><br>
								<label>是否为活动 ：</label>
								<input type="radio" disabled value="0" <c:if test="${rentPlan.rentDefault!=2 }">checked="checked"</c:if> > 否
								<input type="radio" disabled  value="2" <c:if test="${rentPlan.rentDefault==2 }">checked="checked"</c:if>> 是
								<input type="hidden" name="rentDefault" value="${rentPlan.rentDefault}">
								<br>
								<label>方案名：</label>
								<div class="form-group">
									<input class="form-control" value="${rentPlan.rentPlanName }" type="text" name="rentPlanName" required="required">
								</div>
								<label>时间段 ：</label>
								<br>
								原时间段为：${startTime }~${endTime }
								<c:if test="${0==rentPlan.rentDefault }">
								<div class="form-group">
								
								<select name="activityStart" >
								<c:forEach var="i" begin="0" end="24" varStatus="status">
								<option value='<c:if test="${status.index<10 }">0${status.index}:00</c:if><c:if test="${status.index>=10 }">${status.index}:00</c:if>' >${status.index}:00</option>
								</c:forEach>
								</select>
								~
								<select name="activityEnd" >
								<c:forEach var="i" begin="0" end="24" varStatus="status">
								<option value='<c:if test="${status.index<10 }">0${status.index}:00</c:if><c:if test="${status.index>=10 }">${status.index}:00</c:if>' >${status.index}:00</option>
								</c:forEach>
								</select>
								<br>
								</div>
								</c:if>
								<c:if test="${2==rentPlan.rentDefault }">
								<div class="form-group">
								<input class="form-control" type="text" name="rentActivityStart" value="<fmt:formatDate type="both"  pattern="yyyy-MM-dd HH:mm"   value="${rentPlan.rentActivityStart }"/>" id="datetimepicker5"/>
								~
								<input class="form-control" type="text"  name="rentActivityEnd" value="<fmt:formatDate type="both"   pattern="yyyy-MM-dd HH:mm"  value="${rentPlan.rentActivityEnd }"/>" id="datetimepicker6"/>
								<br>
								</div>
								</c:if>
								<label>租赁费用：</label>
								<div class="form-group">
									<input class="form-control" value="${rentPlan.rentPlanRentMoney }" type="text" name="rentPlanRentMoney" required="required">
								<label>元/小时</label>
								</div>
								
								<div style="text-align: center;">
									<input type="submit" value="提交" class="btn btn-primary">
									<input type="reset" class="btn btn-primary">
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


<link rel="stylesheet" type="text/css" href="js/jedate/jedate.css"/>
<script src="js/jedate/jedate.js"></script>


<script type="text/javascript">
	
var start = {
	    dateCell: '#datetimepicker5',
	    format: 'YYYY-MM-DD hh:mm',
	    minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
		festival:true,
	    maxDate: '2099-06-16 23:59:59', //最大日期
	   isTime: true,
	    choosefun: function(datas){
	         end.minDate = datas; //开始日选好后，重置结束日的最小日期
	    }
	};
	var end = {
	    dateCell: '#datetimepicker6',
	    format: 'YYYY-MM-DD hh:mm',
	    minDate: jeDate.now(0), //设定最小日期为当前日期
		festival:true,
	    maxDate: '2099-06-16 23:59:59', //最大日期
	    isTime: true,
	    choosefun: function(datas){
	         start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
	    }
	};
	jeDate(start);
	jeDate(end);
	
</script>
<!-- 引入css和js即可 -->
</html>
