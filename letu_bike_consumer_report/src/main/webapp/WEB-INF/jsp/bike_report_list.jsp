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
	function checkAll(){
    	if($("#selectAll").is(':checked')){
    		$(":checkbox").prop("checked",true);
    	}else{
    		$(":checkbox").prop("checked",false);
    	}
    }
	
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
				<h1 class="page-header">车辆骑行报表</h1>
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
                            
                            	<form action="report/bikeRentInfo/bikeRentInfoList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <input type="hidden" id="exportFlag" name="flag">
                                <div class="row tables-btn-box">
	                                <div class="col-md-10" style="margin-left: 20px;margin-bottom: 30px">
	                                    <span>渠道选择：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="checkAllBox" type="hidden" value="1" />
							            <input  type="checkbox"  onclick="checkAll();" />全选/全不选</span>
	                                    <div class="checkedChannelId checkbox" style="margin-left: 30px">
	                                    </div>
	                                </div>
                                </div>
                                <div class="col-md-2">
                                    <span class="">车辆编号</span>
                                    <input placeholder="车辆编号" type="text" name="bikeId" value="${bikeId }" class="form-control">
                                </div>  
                                <div class="col-md-2">
                                    <span class="">骑行时长</span>
                                    <select name="x"  class="form-control">
								    <option value="-2"<c:if test="${x==-2 }">selected="selected"</c:if>>全部</option>
									<option value="0"<c:if test="${x==0 }">selected="selected"</c:if>>5~10分钟</option> 
									<option value="1"<c:if test="${x==1 }">selected="selected"</c:if>>5~30分钟</option>
									<option value="2"<c:if test="${x==2 }">selected="selected"</c:if>>10~20分钟</option>
									<option value="3"<c:if test="${x==3 }">selected="selected"</c:if>>20~30分钟</option>
									<option value="4"<c:if test="${x==4 }">selected="selected"</c:if>>30~40分钟</option>
									<option value="5"<c:if test="${x==5 }">selected="selected"</c:if>>30~60分钟</option>
									<option value="6"<c:if test="${x==6 }">selected="selected"</c:if>>40~50分钟</option>
									<option value="7"<c:if test="${x==7 }">selected="selected"</c:if>>50~60分钟</option>
									<option value="8"<c:if test="${x==8 }">selected="selected"</c:if>>60~90分钟</option>
									<option value="9"<c:if test="${x==9 }">selected="selected"</c:if>>90~120分钟</option>
									<option value="10"<c:if test="${x==10 }">selected="selected"</c:if>>60分钟及以上</option>
									<option value="11"<c:if test="${x==11 }">selected="selected"</c:if>>120分钟及以上</option>
								</select>
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">租车时间</span>
                                   	<input placeholder="起始时间" id="cStarttime" type="text" name="fromtime" value='<fmt:formatDate value="${fromtime }"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="截止时间" id="cEndtime" type="text" name="totime" value='<fmt:formatDate value="${totime }"/>' class="form-control">
                                </div>
                              	</form>
                            
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                            <shiro:hasPermission name="refundExportButton">
                            <a style="margin-top: 19px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
                      
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="refundForm" method="post">
							<input type="hidden" id="refuseReason" name="refundRefuseReason">
							<input type="hidden" id="hideRefundState" name="refundState">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
									<label>租车次数：${totalCount }</label>
										<tr>
										    
											<th>渠道</th>
											<th>借车点</th>
											<th>车辆编号</th>
											<th>租车时间</th>
											<th>骑行时间（分）</th>
											<th>还车点</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="bikeRentInfo" items="${bikeRentInfoList }">
											<tr class="odd gradeA">
											    <td>${bikeRentInfo.channel.channelName }</td>
												<td>${bikeRentInfo.startFixedName }</td>
												<td>${bikeRentInfo.bike.bikeCode }</td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${bikeRentInfo.rentStarttime }"/></td>
												<td>${bikeRentInfo.rentLongtime }</td>
												<td>${bikeRentInfo.endFixedName }</td>
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

