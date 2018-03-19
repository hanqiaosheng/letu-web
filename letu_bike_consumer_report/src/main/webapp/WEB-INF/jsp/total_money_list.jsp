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
				<h1 class="page-header">平台总收益报表</h1>
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
                            
                            	<form action="report/moneyWater/totalMoneyList.action" method="post" id="searchForm">
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
                                    <span class="">充值类型：</span>
                                    <select class="form-control" name="type" value="${type }" id="type">
                                      <option value="-2">请选择充值类型</option>
                                      <option value="3"<c:if test="${type==3 }">selected="selected"</c:if>>预付款充值</option>
                                      <option value="4"<c:if test="${type==4 }">selected="selected"</c:if>>余额充值</option>
                                      
                                      
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <span class="">用户账号：</span>
                                    <input placeholder="用户账号" type="text" name="tel" value="${tel }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">用户姓名：</span>
                                    <input placeholder="用户姓名" type="text" name="name" value="${name }" class="form-control">
                                </div>       
                                <div class="col-md-2 time-box">
                                    <span class="tit">充值时间</span>
                                   	<input placeholder="起始时间" id="cStarttime" type="text" name="fromtime" value='<fmt:formatDate value="${fromtime }"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="截止时间" id="cEndtime" type="text" name="totime" value='<fmt:formatDate value="${totime }"/>' class="form-control">
                                </div>
                              	</form>
                            
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                            <shiro:hasPermission name="refundExportButton">
                            <a style="margin-top: 19px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
                      
						
						<%-- <span style="margin-left: 50px;margin-top: 20px" id="c">
						<label>余额充值总值：</label><label id="sumBalanceMoney">${sumBalanceMoney }</label>元
						</span>
						<span style="margin-left: 50px;margin-top: 20px" id="d">
						<label>预付款充值总值：</label><label id="sumBuchongMoney">${sumBuchongMoney }</label>元
						</span>
						
						<div style="float: right; margin-right: 50px;margin-top: 20px"">
						<label>平台进账总值：</label><label id="totalMoney">${totalMoney }</label>元
						</div> --%>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="refundForm" method="post">
							<input type="hidden" id="refuseReason" name="refundRefuseReason">
							<input type="hidden" id="hideRefundState" name="refundState">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
									<label>余额充值总值：<fmt:formatNumber pattern="0.00" value="${sumBalanceMoney }"></fmt:formatNumber>元  &nbsp;&nbsp;&nbsp;  预付款充值总值：<fmt:formatNumber pattern="0.00" value="${sumBuchongMoney }"></fmt:formatNumber>元   &nbsp;&nbsp;&nbsp;  平台进账总值：<fmt:formatNumber pattern="0.00" value="${totalMoney }"></fmt:formatNumber>  </label>
										<tr>
										    
											<th>编号</th>
											<th>时间</th>
											<th>账号</th>
											<th>姓名</th>
											<th>渠道</th>
											<th>类型</th>
											<th>金额（元）</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="moneyLog" items="${moneyLogList }">
											<tr class="odd gradeA">
											    <td>${moneyLog.moneyLogId }</td>								
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${moneyLog.moneyLogCreateTime  }"/></td>
												<td>${moneyLog.account.aUser.userTel }</td>
												<td>${moneyLog.account.aUser.userRealname }</td>
												<td>${moneyLog.channelName }</td>
												<td>
												<c:if test="${moneyLog.moneyLogStreamType==3 }">
												预付款补充
												</c:if>
												<c:if test="${moneyLog.moneyLogStreamType==4 }">
												余额充值
												</c:if>
												</td>
												<td>
												<c:if test="${moneyLog.moneyLogStreamType==3 }">
												<fmt:formatNumber pattern="0.00" value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
												</c:if>
												<c:if test="${moneyLog.moneyLogStreamType==4 }">
												<fmt:formatNumber pattern="0.00" value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
												</c:if>
												</td>
											</tr>
									</c:forEach>
									</tbody>
								</table>
								</form>
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

