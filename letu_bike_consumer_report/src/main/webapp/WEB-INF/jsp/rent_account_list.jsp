<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<%@include file="common/datePicker.jsp"%>
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
<script type="text/javascript">
function checkAll() {
	if($("#checkAllBox").val()=='1'){
		$(":checkbox").prop("checked",true);
		$("#checkAllBox").val("0");
	}else if($("#checkAllBox").val()=='0'){
		$(":checkbox").prop("checked",false);
		$("#checkAllBox").val("1");
	}
}
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">租赁消费报表</h1>
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
                          
                            	<form action="report/moneyWater/rentMoneyWater.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <input type="hidden" id="exportFlag" name="flag">
                                <div class="row tables-btn-box">
	                                <div class="col-md-10" style="margin-left: 20px;margin-bottom: 30px">
	                                    <span>渠道选择：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="checkAllBox" type="hidden" value="1" />
							            <input  type="checkbox"  onclick="checkAll();" />全选/全不选</span>
	                                    <div class="checkedChannelId checkbox" style="margin-left: 30px">
	                                     <%-- <input checked="checked" type="checkbox" name="channelId" value="${channel.channelId }"><span>${channel.channelName }</span>  --%>
	                                    </div>
	                                </div>
                                </div>
                                <div class="col-md-2">
                                    <span>用户账号</span>
                                   	<input placeholder="用户账号" id="tel" type="text" name="tel" value="${tel }"  class="form-control">
                                </div> 
                                <div class="col-md-2">
                                    <span>用户姓名</span>
                                   	<input placeholder="用户姓名" id="name" type="text" name="name" value="${name }"  class="form-control">
                                </div> 
                                <div class="col-md-2">
                                    <span>车辆编号</span>
                                   	<input placeholder="车辆编号" id="bikeCode" type="text" name="bikeCode" value="${bikeCode }"  class="form-control">
                                </div> 
                                <div class="col-md-2 time-box">
                                    <span class="tit">支付时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="fromtime" value='<fmt:formatDate value="${fromtime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="totime" value='<fmt:formatDate value="${totime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>
                              	</form>
                           
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                            <shiro:hasPermission name="orderFlowExportButton">
                            <a style="margin-top: 19px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
						<div style="margin-left: 20px;margin-top: 20px"">
						<label>租赁消费总值：</label><label><fmt:formatNumber pattern="0.00" value="${sumMoney }"></fmt:formatNumber></label>元
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
											<th>用户账号</th>
											<th>用户姓名</th>
											<th>车辆编号</th>
											<th>渠道</th>
											<th>实付金额</th>
										</tr>
									</thead>
									<tbody>
									        <c:forEach var="bikeRentInfo" items="${bikeRentInfos }">
											<tr class="odd gradeA">
												<td>${bikeRentInfo.rentInfoId }</td>
												<td><fmt:formatDate value="${bikeRentInfo.rentPayTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${bikeRentInfo.user.userTel }</td>
												<td>${bikeRentInfo.user.userRealname }</td>
												<td>${bikeRentInfo.bike.bikeCode }</td>
												<td>${bikeRentInfo.channel.channelName }</td>					
												<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentPrice }"></fmt:formatNumber></td>	
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
		<script type="text/javascript">
  var channelIdList = new Array();   
  var channelList = new Array();
  <c:forEach items="${channelList }" var="channel">
  channelList.push("${channel.channelId}");
  </c:forEach>
  <c:forEach items="${channelIdList }" var="channelId">
  channelIdList.push("${channelId}");
  </c:forEach>
 <c:forEach items="${channelList }" var="channel">
 var checked = "";
 for(var k=0;k<channelIdList.length;k++){   
       if("${channel.channelId}"==channelIdList[k]) {  
         checked="checked='checked'";  
       }  
   } 
 $(".checkedChannelId").append(  
	        "<label><input type='checkbox' name='channelId' value='"+"${channel.channelId}"+  
	        "'"+checked+">"+"${channel.channelName}"+"</label>&nbsp;&nbsp;");  
 </c:forEach>
  
</script>
</body>
</html>

