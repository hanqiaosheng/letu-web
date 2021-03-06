<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<%@include file="common/datePicker.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
			$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
				if(type=='yes'){
					$.ajax({
						type:'post',
						data:'rentInfoId='+obj,
						url:'cms/order/deleteOrder.action',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
				}else if(type=='no'){
					this.hide();
				}
		    }) 
	}
	
	
    function checkAll() {
    	if($("#checkAllBox").val()=='1'){
    		$(":checkbox").prop("checked",true);
    		$("#checkAllBox").val("0");
    	}else if($("#checkAllBox").val()=='0'){
    		$(":checkbox").prop("checked",false);
    		$("#checkAllBox").val("1");
    	}
	}
    function deleteAll(){
    	var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:$('#checkAllForm').serialize(),
					url:'cms/order/deleteAllOrder.action',
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
				<h1 class="page-header">租赁订单列表</h1>
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
                            	<form action="cms/order/orderList.action?flag=0" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">租赁编号</span>
                                    <input type="text" class="form-control" placeholder="请输入租赁编号" value="${rentInfoId}" name="rentInfoId" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">车辆编号</span>
                                    <input type="text" class="form-control" placeholder="请输入车辆编号" value="${bikeCode}" name="bikeCode" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">租赁账号</span>
                                    <input type="text" class="form-control" placeholder="请输入租赁账号" value="${userTel}" name="userTel" >
                                </div>
								
                                <div class="col-md-2 time-box">
                                    <span class="tit">租赁时间</span>
                                   	<input placeholder="租赁时间" id="cStarttime" type="text" name=rentTime value='<fmt:formatDate value="${rentTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="租赁时间" id="cEndtime" type="text" name="rentTime2" value='<fmt:formatDate value="${rentTime2 }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">归还时间</span>
                                   	<input placeholder="归还时间" id="cStarttimeB" type="text" name=returnTime value='<fmt:formatDate value="${returnTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="归还时间" id="cEndtimeB" type="text" name="returnTime2" value='<fmt:formatDate value="${returnTime2 }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>                        
                                <div class="col-md-2">
                                    <span class="">订单状态</span>
		                                <select name="rentState" id="rentState" class="form-control">
											<option value="">全部</option>
											<option value="2" <c:if test="${rentState==2 }">selected="selected"</c:if>>未完成</option>
											<option value="1" <c:if test="${rentState==1 }">selected="selected"</c:if>>已完成</option>
											<option value="3" <c:if test="${rentState==3 }">selected="selected"</c:if>>订单失败</option>
										</select>
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
								     <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                              	</form>
                            </div>
                            
                       </div>
						
						
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form  id="checkAllForm" method="post">
							<input type="hidden" id="flag" name="flag" value="${flag }">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr><th></th>
											<th>租赁编号</th>
											<th>租赁时间</th>
											<th>租赁账号</th>
											<th>车辆编号</th>
											<th>渠道</th>
											<th>订单状态</th>
											<th>归还时间</th>
											
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${rentInfoList }" var="bikeRentInfo">
											<tr class="odd gradeA">
												<td>
													<input name="rentInfoIds" type="checkbox" value="${bikeRentInfo.rentInfoId }">
												</td>
												<td>${bikeRentInfo.rentInfoId }</td>
												<td><fmt:formatDate value="${bikeRentInfo.rentStarttime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${bikeRentInfo.user.userTel }</td>
												<td>${bikeRentInfo.bike.bikeCode }</td>
												<td>${bikeRentInfo.channel.channelName }</td>
												<td class="center">
												<c:if test="${bikeRentInfo.rentState==1 }">
												<span style="color:green">已完成</span>
												</c:if>
												<c:if test="${bikeRentInfo.rentState==2 }">
												<span style="color:orange;">未完成</span>
												</c:if>
												<c:if test="${bikeRentInfo.rentState==3 }">
												<span style="color:red">订单失败</span>
												</c:if>
												</td>
												<td class="center"><fmt:formatDate value="${bikeRentInfo.rentEndtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/order/rentDetail.action?rentInfoId=${bikeRentInfo.rentInfoId }&flag=1">详情</a>
													<shiro:hasPermission name="orderUpdateButton">
													<a class="btn btn-primary"
													href="cms/order/rentDetail.action?rentInfoId=${bikeRentInfo.rentInfoId }&flag=2&gotoFlag=${flag}">修改</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="orderDeleteButton">
													<a class="btn btn-danger" onclick="checkDel('${bikeRentInfo.rentInfoId }')" >删除</a>
												    </shiro:hasPermission>
												</td>
											</tr>
											</c:forEach>
									</tbody>
								</table>
								<input id="checkAllBox" type="hidden" value="1" />
								<input  type="checkbox"  onclick="checkAll();" />全选
								</form>
								<shiro:hasPermission name="orderDeleteButton">
								<button class="btn btn-danger" onclick="deleteAll()">批量删除</button>
								</shiro:hasPermission>
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
	<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
	<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#channelid").select2();
        })
	</script>
</body>

</html>

