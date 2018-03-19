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
  function sumMoney(){
	  var item = $("#item").val();
	  var FromTime = $("#cStarttime").val();
	  var ToTime = $("#cEndtime").val();
	  $.ajax({
		 url:'cms/moneyWater/sumTotalMoney.action',
		 data:'item='+item+"&FromTime="+FromTime+"&ToTime="+ToTime,
		 type:'post',
		 success:function(data){
			 var arr = data.split(",");
			 $("#sumBuquanMoney").text(parseFloat(arr[0]));
			 $("#sumBalanceMoney").text(parseFloat(arr[2]));
			 $("#sumRefundMoney").text(parseFloat(arr[1]));
			 $("#sumTotalMoney").text((parseFloat(arr[0])+parseFloat(arr[2])+parseFloat(arr[1])).toFixed(2));
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
function hideOther(obj){
	var item = obj.value;
	if(item==2){
		$("#c").hide();
		$("#d").show();
	}else if(item==3){
		$("#c").show();
		$("#d").hide();
	}else if(item==-1){
		$("#c").show();
		$("#d").show();
	}
}
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">充值退款流水</h1>
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
                            	<form action="cms/moneyWater/moneyLogList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <input type="hidden" id="exportFlag" name="flag">
                                <div class="col-md-2">
                                    <span class="">项目名称</span>
                                   <select id="item" class="form-control" name="item" onchange="hideOther(this)">
									<option value="-1">全部</option>
									<option value="2"<c:if test="${item==2 }">selected="selected"</c:if>>预付款退款</option>
									<option value="3"<c:if test="${item==3 }">selected="selected"</c:if>>预付款补充</option>
									<option value="4"<c:if test="${item==4 }">selected="selected"</c:if>>余额充值</option>
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
                            <shiro:hasPermission name="tRefundFlowExportButtom">
                            <a style="margin-top: 10px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
                       <div style="margin-top:15px;">
						<span style="margin-left: 50px;" id="c">
						<label>预付款补充总额：</label><label id="sumBuquanMoney"></label>元
						</span>
						<span style="margin-left: 50px;" id="d">
						<label>余额充值总额：</label><label id="sumBalanceMoney"></label>元
						</span>
						<span style="margin-left: 50px;" id="d">
						<label>预付款退款总额：</label><label id="sumRefundMoney"></label>元
						</span>
						<div style="float: right; margin-right: 50px;">
						<label>合计：</label><label id="sumTotalMoney"></label>元
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
											<th>时间</th>
											<th>项目</th>
											<th>账号</th>
											<th>金额</th>
											<!-- <th>操作人</th> -->
										</tr>
									</thead>
									<tbody>
									        <c:forEach var="moneyLog" items="${moneyLogs }">
											<tr class="odd gradeA">
												<td>${moneyLog.moneyLogId }</td>
												<td><fmt:formatDate value="${moneyLog.moneyLogCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>
												<c:if test="${moneyLog.moneyLogStreamType==2 }">
												预付款退款
												</c:if>
												<c:if test="${moneyLog.moneyLogStreamType==3 }">
												预付款补充
												</c:if>
												<c:if test="${moneyLog.moneyLogStreamType==4 }">
												余额充值
												</c:if>
												</td>
												<td>${moneyLog.account.aUser.userTel }</td>					
												<td>
												<c:if test="${moneyLog.moneyLogStreamType==2 }">
												<fmt:formatNumber pattern="-0.00" value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
												</c:if>
												<c:if test="${moneyLog.moneyLogStreamType!=2 }">
												<fmt:formatNumber pattern="0.00" value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
												</c:if>
												</td>	
												<%-- <td>${moneyLog.admin.adminRealname }</td> --%>
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
</body>
</html>

