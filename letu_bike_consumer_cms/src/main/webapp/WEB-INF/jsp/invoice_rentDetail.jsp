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
				$.ajax({
					url:"${basePath}/cms/user/account/delete.action?moneyLogId="+objA+"&moneyLogAccountId="+objB,
					type:'post',
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
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">开票行程</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						
						<div class="panel-heading clearfix ">
                            <!-- <div class="row">
                                <div class="caption font-red-intense col-md-12">
                                    <i class="fa fa-search font-red-intense"></i>
                                    <span class="caption-subject bold uppercase">查询</span>
                                    <button class="btn btn-default hide-search pull-right" type="button">隐藏</button>
                                </div>
                            </div>
                            <br> -->
                            <div class="row tables-btn-box">
                            	<form action="cms/invoice/bikeRentInfo.action?invoiceId=${invoiceId}" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                              	</form>
                            </div>
                           <!--  <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button> -->
                       </div>
						
						
						
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<div class="panel-heading">
								<label>账号：${user.userTel}</label>
								<label>姓名 ：${user.userRealname }</label>
								
							</div>
							 
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>骑行日期</th>
											<th>车辆编号</th>
											<th>骑行时间</th>
											<th>骑行金额</th>
											
											
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${bikeRentInfoList}" var="bikeRentInfo">
											<tr class="odd gradeA">
												<td><fmt:formatDate value='${bikeRentInfo.rentPayTime }' pattern='yyyy-MM-dd '/></td>
												<td>${bikeRentInfo.bike.bikeCode }</td>
												<td>${bikeRentInfo.time }分钟</td>
												<%-- <td>${bikeRentInfo.rentEndtime-bikeRentInfo.rentStarttime }</td> --%>
												<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentPrice }"/>元</td>
												
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
</body>
</html>

