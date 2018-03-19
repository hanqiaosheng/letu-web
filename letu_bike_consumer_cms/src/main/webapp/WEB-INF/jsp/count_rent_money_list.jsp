<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<%@include file="common/datePicker.jsp"%>
<script type="text/javascript">
  function sumMoney(){
	  var channelId = $("#channelId").val();
	  var returnFromTime = $("#cStarttime").val();
	  var returnToTime = $("#cEndtime").val();
	  $.ajax({
		 url:'cms/moneyWater/sumRentCountMoney.action',
		 data:'channelId='+channelId+"&returnFromTime="+returnFromTime+"&returnTotime="+returnToTime,
		 type:'post',
		 success:function(data){
			 $("#sumRentMoney").text(data.sumRentMoney);
			 $("#sumInPriceMoney").text(data.sumInPriceMoney);
			 $("#sumCouponMoney").text(data.sumCouponMoney);
			 $("#sumMoney").text((data.sumRentMoney+data.sumInPriceMoney-data.sumCouponMoney).toFixed(2));
		 }
	  });
  }
</script>
<script type="text/javascript">
function exportExcel(){
	$("#exportFlag").val(1);
	$("#searchForm").submit();
}
function search(){
	$("#exportFlag").val(0);
	$("#searchForm").submit();
}
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">租赁结算流水</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix ">
                            <div class="row">
                                <div class="caption font-red-intense col-md-12">
                                    <i class="fa fa-search font-red-intense"></i>
                                    <span class="caption-subject bold uppercase">查询</span>
                                    <button class="btn btn-default hide-search pull-right" type="button">隐藏</button>
                                </div>
                            </div>
                            <br>
                            <div class="row tables-btn-box">
                            	<form action="cms/moneyWater/rentCountMoneyWater.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <input type="hidden" id="exportFlag" name="flag">
                                <div class="col-md-2">
                                   <span class="">渠道</span>
                                   <select id="channelId" class="form-control" name="channelId">
									<option value="-1">全部</option>
									<c:forEach items="${channelList }" var="channel">
									<option value="${channel.channelId }" <c:if test="${channel.channelId==channelId }">selected="selected"</c:if>>${channel.channelName }</option>
									</c:forEach>
								   </select>
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="returnFromTime" value='<fmt:formatDate value="${returnFromTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="returnTotime" value='<fmt:formatDate value="${returnTotime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                            <input class="btn btn-default pull-right search-btn btn-success" type="button" onclick="sumMoney()" value="合计">
                            <shiro:hasPermission name="orderFlowExportButton">
                            <a style="margin-top: 10px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
                       <div style="margin-top:15px;">
                        <span style="margin-left: 50px;" id="c">
						<label>租赁费用总额：</label><label id="sumRentMoney"></label>元
						</span>
						<span style="margin-left: 50px;" id="d">
						<label>保险费用总额：</label><label id="sumInPriceMoney"></label>元
						</span>
						<span style="margin-left: 50px;" id="d">
						<label>优惠金额总额：</label><label id="sumCouponMoney"></label>元
						</span>
						<div style="float: right; margin-right: 50px;">
						<label>合计实际支付费用：</label><label id="sumMoney"></label>元
						</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>租赁账号</th>
											<th>时间</th>
											<th>车辆编号</th>
											<th>渠道</th>
											<th>租赁费用</th>
											<th>保险费用</th>
											<th>优惠金额</th>
											<th>实际支付费用</th>
										</tr>
									</thead>
									<tbody>
									        <c:forEach var="bikeRentInfo" items="${bikeRentInfos }">
											<tr class="odd gradeA">
												<td>${bikeRentInfo.rentInfoId }</td>
												<td>${bikeRentInfo.user.userTel }</td>
												<td><fmt:formatDate value="${bikeRentInfo.rentPayTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${bikeRentInfo.bike.bikeCode }</td>
												<td>${bikeRentInfo.channel.channelName }</td>					
												<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentPrice }"></fmt:formatNumber></td>
												<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentInsurancePrice }"></fmt:formatNumber></td>
												<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentCouponMoney }"></fmt:formatNumber></td>
												<c:if test="${bikeRentInfo.rentPrice+bikeRentInfo.rentInsurancePrice-bikeRentInfo.rentCouponMoney<=0 }"><td>0.00</td></c:if>
												<c:if test="${bikeRentInfo.rentPrice+bikeRentInfo.rentInsurancePrice-bikeRentInfo.rentCouponMoney>0 }">
												<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentPrice+bikeRentInfo.rentInsurancePrice-bikeRentInfo.rentCouponMoney }"></fmt:formatNumber></td>	
											    </c:if>
											</tr>
											</c:forEach>
									</tbody>
								</table>
							</div>
							
							<%@include file="common/pageUtil.jsp"%>
							
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>
	<%@include file="common/buttom.jsp"%>
	<%@include file="common/maskMap.jsp"%>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#channelId").select2();
        })
	</script>
</body>
</html>

