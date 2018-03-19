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
	function checkDel(obj){
		var bikeId = '${bike.bikeId }';
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'bikeRentId='+obj+'&bikeId='+bikeId,
					url:'cms/bikeRentInfo/deleteBikeRentInfo.action',
					success:function(data){	
							window.location.reload();
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 			
	}
	
	
    function getFixedReturn(obj){
    	var rentStartFixedId = '${rentStartFixedId }';
    	var rentEndFixedId = '${rentEndFixedId }';
    	$.ajax({
			type:'post',
			data:'channelId='+obj,
			url:'cms/fixedReturn/getFixedByChannelId.action',
			success:function(data){	
				$("#rentStartFixedId").html('<option value="-1">全部还车点</option>');
				$("#rentEndFixedId").html('<option value="-1">全部还车点</option>');
				if(null!=data&&data!=''){
					var str1 = '';
					var str2 = '';
					 for(var index1 in data){
						 if(rentStartFixedId==data[index1].fixedReturnId ){
							 str1 += '<option value="'+data[index1].fixedReturnId+'" selected>'+data[index1].fixedReturnName+'</option>';
						 }else{
							 str1 += '<option value="'+data[index1].fixedReturnId+'">'+data[index1].fixedReturnName+'</option>';
						 }
				     }
					 
					 
					 for(var index2 in data){
						 if(rentEndFixedId==data[index2].fixedReturnId ){
							 str2 += '<option value="'+data[index2].fixedReturnId+'" selected>'+data[index2].fixedReturnName+'</option>';
						 }else{
							 str2 += '<option value="'+data[index2].fixedReturnId+'">'+data[index2].fixedReturnName+'</option>';
						 }
						 
					 }
					 $("#rentStartFixedId").append(str1); 
					 $("#rentEndFixedId").append(str2); 
				}
			}
		});
    }
	    
    $(function(){
    	getFixedReturn($("#channelid").val());
    })
    
</script>

<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">车辆租赁列表</h1>
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
                            	<form action="cms/bikeRentInfo/bikeRentInfoList.action?bikeId=${bike.bikeId }"" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">租赁编号</span>
                                    <input placeholder="租赁编号" type="text" name="bikerentId" value="${bikerentId }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">租赁账号</span>
                                    <input placeholder="租赁账号" type="text" name="userPhone" value="${userPhone }" class="form-control">
                                </div>
								<div class="col-md-2">
                                <span class="">渠道</span>
                                   <select name="channelid" id="channelid" class="form-control" onchange="getFixedReturn(this.value)">
								    <option value="-1">全部渠道</option>
									<c:forEach items="${channelList }" var="channel">
										<option value="${channel.channelId }"  <c:if test="${channel.channelId==channelid }">selected="selected"</c:if>>${channel.channelName }</option>
									</c:forEach>
								   </select>
                                </div>   
                                <div class="col-md-2">
                                   <span class="">租赁地点</span>
                                   <select name="rentStartFixedId"  class="form-control" id="rentStartFixedId">
								   </select>
                                </div>   
                                <div class="col-md-2">
                                   <span class="">归还地点</span>
                                   <select name="rentEndFixedId"  class="form-control" id="rentEndFixedId">
								   </select>
                                </div>    
                                <div class="col-md-2 time-box">
                                    <span class="tit">租赁时间</span>
                                   	<input placeholder="租赁时间" id="cStarttime" type="text" name=rentFromTime value='<fmt:formatDate value="${rentFromTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="租赁时间" id="cEndtime" type="text" name="rentTotime" value='<fmt:formatDate value="${rentTotime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">归还时间</span>
                                   	<input placeholder="归还时间" id="cStarttimeB" type="text" name=returnFromTime value='<fmt:formatDate value="${returnFromTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="归还时间" id="cEndtimeB" type="text" name="returnTotime" value='<fmt:formatDate value="${returnTotime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
                       <div class="panel-heading">
						<label>车辆编号：${bike.bikeCode }</label>
						<label style="margin-left: 15px;margin-top: 10px">车辆编号：${bike.bikeCode }</label>
						<label>车辆状态：
							<c:if test="${bike.bikeState==0 }">空闲中</c:if>
							<c:if test="${bike.bikeState==1 }">临时还车</c:if>
							<c:if test="${bike.bikeState==2 }">租借中</c:if>
							<c:if test="${bike.bikeState==3 }">维护中</c:if>
							<c:if test="${bike.bikeState==4 }">锁定中</c:if>	
							<c:if test="${bike.bikeState==5 }">冻结中</c:if>				
						</label>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>租赁编号</th>
											<th>租赁时间</th>
											<th>租赁地点</th>
											<th>租赁账号</th>
											<th>租赁状态</th>
											<th>归还时间</th>
											<th>归还地点</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									        <c:forEach var="bikeRentInfo" items="${bikeRentInfoList }">
											<tr class="odd gradeA">
												<td>${bikeRentInfo.rentInfoId }</td>
												<td><fmt:formatDate value="${bikeRentInfo.rentStarttime }" pattern="yyyy-MM-dd"/></td>
												<td class="center">
												<%-- <c:if test="${bikeRentInfo.bike.models.modelsIsfixedPoint==0 }">
												 <a href="cms/bikeRentInfo/bikeRentInfoDetail.action?bikeRentId=${bikeRentInfo.rentInfoId }"><span class="lnglatStr" data-lat="${bikeRentInfo.rentStartlat }" data-lng="${bikeRentInfo.rentStartlng }"></span></a>
												</c:if> --%>
												<%-- <c:if test="${bikeRentInfo.bike.models.modelsIsfixedPoint==1 }"> --%>
													<c:if test="${empty bikeRentInfo.startFixedName}">
													<a href="cms/bikeRentInfo/bikeRentInfoDetail.action?bikeRentId=${bikeRentInfo.rentInfoId }"><span class="lnglatStr" data-lat="${bikeRentInfo.rentStartlat }" data-lng="${bikeRentInfo.rentStartlng }"></span></a>
													</c:if>
													<c:if test="${!empty bikeRentInfo.startFixedName}">
													  ${bikeRentInfo.startFixedName}
													</c:if>
												<%-- </c:if> --%>
												</td>
												<td>${bikeRentInfo.user.userTel }</td>
												<td>
												<c:if test="${bikeRentInfo.rentState==1 }">已完成</c:if>
												<c:if test="${bikeRentInfo.rentState==2 }">未完成</c:if>
												<c:if test="${bikeRentInfo.rentState==3 }">订单失败</c:if>
												</td>
												<td class="center"><fmt:formatDate value="${bikeRentInfo.rentEndtime }" pattern="yyyy-MM-dd"/></td>
												<td>
												<%-- <c:if test="${bikeRentInfo.bike.models.modelsIsfixedPoint==0 }">
												 <a href="cms/bikeRentInfo/bikeRentInfoDetail.action?bikeRentId=${bikeRentInfo.rentInfoId }"><span class="lnglatStr2" data-lat2="${bikeRentInfo.rentEndlat }" data-lng2="${bikeRentInfo.rentEndlng }"></span></a>
												</c:if>
												<c:if test="${bikeRentInfo.bike.models.modelsIsfixedPoint==1 }"> --%>
												    <c:if test="${empty bikeRentInfo.endFixedName}">
													 <a href="cms/bikeRentInfo/bikeRentInfoDetail.action?bikeRentId=${bikeRentInfo.rentInfoId }"><span class="lnglatStr2" data-lat2="${bikeRentInfo.rentEndlat }" data-lng2="${bikeRentInfo.rentEndlng }"></span></a>
													</c:if>
													<c:if test="${!empty bikeRentInfo.endFixedName}">
													  ${bikeRentInfo.endFixedName}
													</c:if>
												<%-- </c:if> --%>
												</td>
												<td class="center">
												<a class="btn btn-info" href="cms/bikeRentInfo/bikeRentInfoDetail.action?bikeRentId=${bikeRentInfo.rentInfoId }">详情</a>
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${bikeRentInfo.rentInfoId }')">删除</a>
												</td>
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
<script type="text/javascript">
    $(document).ready(function() {
        $("#channelid").select2();
    })
</script>
</html>

