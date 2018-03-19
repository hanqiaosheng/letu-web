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
	  var FromTime = $("#cStarttime").val();
	  var ToTime = $("#cEndtime").val();
	  $.ajax({
		 url:'cms/moneyWater/sumInsuranceMoney.action',
		 data:'channelId='+channelId+"&returnFromTime="+FromTime+"&returnTotime="+ToTime,
		 type:'post',
		 success:function(data){
			 $("#sumMoney").text(data);
			 
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
				<h1 class="page-header">保险费用流水</h1>
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
                            	<form action="cms/moneyWater/insuranceLogList.action" method="post" id="searchForm">
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
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="FromTime" value='<fmt:formatDate value="${FromTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="ToTime" value='<fmt:formatDate value="${ToTime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>                             
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                            <input class="btn btn-default pull-right search-btn btn-success" type="button" onclick="sumMoney()" value="合计">
                            <shiro:hasPermission name="insuranceFlowExportButton">
                            <a style="margin-top: 10px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
						<div style="float: right; margin-right: 50px;margin-top: 20px"">
						<label>合计：</label><label id="sumMoney"></label>元
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>时间</th>
											<th>项目</th>
											<th>账号</th>
											<th>渠道</th>
											<th>金额</th>
										</tr>
									</thead>
									<tbody>
									        <c:forEach var="bikeRentInfo" items="${bikeRentInfos }">
											<tr class="odd gradeA">
												<td>${bikeRentInfo.rentInfoId }</td>
												<td><fmt:formatDate value="${bikeRentInfo.rentPayTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>保险缴纳</td>
												<td>${bikeRentInfo.user.userTel }</td>
												<td>${bikeRentInfo.channel.channelName }</td>					
												<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentInsurancePrice }"></fmt:formatNumber></td>	
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

