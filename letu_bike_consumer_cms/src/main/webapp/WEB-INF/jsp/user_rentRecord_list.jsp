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
	function checkDel(objA,objB){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				if(objA!=''){
					$.ajax({
						url:"${basePath}/cms/user/deleteBikeRentInfo.action?rentInfoId="+objA+"&userId="+objB,
						type:'post',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
				}
				else{
					deleteAll(objB);
				}
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
    function deleteAll(obj) {
    	$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:"${basePath}/cms/user/deleteBikeRentInfo.action?userId="+obj,
			success:function(data){	
					window.location.reload();
			}
		});
	}
</script>

<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">用户租赁记录</h1>
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
                            	<form action="cms/user/bikeRentInfoList.action?rentInfoUserId=${bikeRentInfo.rentInfoUserId }" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">租赁编号</span>
                                    <input type="text" class="form-control" placeholder="请输入租赁编号" value="${bikeRentInfo.rentInfoId}" name="rentInfoId" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">车辆编号</span>
                                    <input type="text" class="form-control" placeholder="请输入车辆编号" value="${bikeRentInfo.bBikeCode}" name="bBikeCode" >
                                </div>
								<div class="col-md-2">
                                    <span class="">租赁地点</span>
                                    <input type="text" class="form-control go-back" placeholder="点击选择租赁地点" value="${bikeRentInfo.rentLatLng}" name="rentLatLng" >
                                </div>   
                                <div class="col-md-2">
                                    <span class="">归还地点</span>
                                    <input type="text" class="form-control go-back" placeholder="点击选择归还地点" value="${bikeRentInfo.returnLatLng}" name="returnLatLng" >
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">租赁时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="rentFromTime" value='<fmt:formatDate value="${bikeRentInfo.rentFromTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="rentTotime" value='<fmt:formatDate value="${bikeRentInfo.rentTotime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div> 
                                <div class="col-md-2 time-box">
                                    <span class="tit">归还时间</span>
                                   	<input placeholder="开始时间" id="cStarttimeB" type="text" name="returnFromTime" value='<fmt:formatDate value="${bikeRentInfo.returnFromTime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtimeB" type="text" name="returnTotime" value='<fmt:formatDate value="${bikeRentInfo.returnTotime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>                             
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<div>账号:${user.userTel}</div>
							<div>姓名:${user.userRealname }</div>
							<div>租赁状态:
							<c:if test="${user.userState==0 }">空闲</c:if>
							<c:if test="${user.userState==1 }">正在租借</c:if>
							</div>
							<form action="cms/user/deleteBikeRentInfo.action?userId=${user.userId}" id="checkAllForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th></th>
											<th>租赁编号</th>
											<th>租赁时间</th>
											<th>租赁地点</th>
											<th>租赁车辆牌号</th>
											<th>租赁状态</th>
											<th>归还时间</th>
											<th>归还地点</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
											<c:forEach items="${bikeRentInfoList }" var="bikeRentInfo">
											<tr class="odd gradeA">
												<td>
													<input type="checkbox" name="rentInfoIds" value="${bikeRentInfo.rentInfoId }">
												</td>
												<td>${bikeRentInfo.rentInfoId }</td>
												<td><fmt:formatDate value='${bikeRentInfo.rentStarttime}' pattern='yyyy-MM-dd HH:mm'/></td>
												<td>
													<a href="cms/user/bikeRentInfoDetail.action?rentInfoId=${bikeRentInfo.rentInfoId }&userId=${user.userId}&flag=1">
														<span class="lnglatStr" data-lat="${bikeRentInfo.rentStartlat }" data-lng="${bikeRentInfo.rentStartlng }"></span>
													</a>
												</td>
												<td>${bikeRentInfo.bike.bikeCode }</td>
												<td class="center">
												<c:if test="${bikeRentInfo.rentState==1 }">
												已完成
												</c:if>
												<c:if test="${bikeRentInfo.rentState==2 }">
												未完成
												</c:if>
												<c:if test="${bikeRentInfo.rentState==3 }">
												订单失败
												</c:if>
												</td>
												<td class="center"><fmt:formatDate value='${bikeRentInfo.rentEndtime}' pattern='yyyy-MM-dd HH:mm'/></td>
												<td class="center">
													<c:if test="${bikeRentInfo.rentEndlng!=null }">
														<a href="cms/user/bikeRentInfoDetail.action?rentInfoId=${bikeRentInfo.rentInfoId }&userId=${user.userId}&flag=2">
															<span class="lnglatStr2" data-lat2="${bikeRentInfo.rentEndlat }" data-lng2="${bikeRentInfo.rentEndlng }"></span>
														</a>
													</c:if>
													<c:if test="${bikeRentInfo.rentEndlng==null }">
														
													</c:if>
												</td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/user/bikeRentInfoDetail.action?rentInfoId=${bikeRentInfo.rentInfoId }&userId=${user.userId}">详情</a>
													<a class="btn btn-danger"
													href="javascript:void(0)"onclick="checkDel('${bikeRentInfo.rentInfoId }','${user.userId}')">删除</a>
												</td>
											</tr>
											</c:forEach>
									</tbody>
								</table>
								
								</form>
								<input id="checkAllBox" type="hidden" value="1" />
								<input  type="checkbox"  onclick="checkAll();" />全选
								<button class="btn btn-danger" onclick="checkDel('','${user.userId}')">批量删除</button>
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

