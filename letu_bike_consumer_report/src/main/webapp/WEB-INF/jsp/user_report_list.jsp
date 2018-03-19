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


<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">注册用户报表</h1>
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
                            	<form action="report/user/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <input type="hidden" id="exportFlag" name="flag">
                                <div class="col-md-2">
                                    <span class="">用户账号：</span>
                                    <input placeholder="用户账号" type="text" name="tel" value="${tel }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">用户姓名：</span>
                                    <input placeholder="用户姓名" type="text" name="name" value="${name }" class="form-control">
                                </div> 
                                <div class="col-md-2">
                                    <span class="">用户身份证：</span>
                                    <input placeholder="用户身份证" type="text" name="idcard" value="${idcard }" class="form-control">
                                </div>        
                                 
                                
                                <div class="col-md-2 time-box">
                                    <span class="tit">注册时间：</span>
                                   	<input placeholder="起始时间" id="cStarttime" type="text" name="fromtime" value='<fmt:formatDate value="${fromtime }"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="截止时间" id="cEndtime" type="text" name="totime" value='<fmt:formatDate value="${totime }"/>' class="form-control">
                                </div>
                                
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                            <shiro:hasPermission name="refundExportButton">
                            <a style="margin-top: 19px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
                      
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="refundForm" method="post">
							<input type="hidden" id="hideRefundState" name="refundState">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
									<label>新增用户量：${todayCount } &nbsp;&nbsp;&nbsp; 总用户量：${totalCount } &nbsp;&nbsp;&nbsp; 新增用户量/总用户量：${result }%</label>
										<tr>
										    
											<th>账号</th>
											<th>姓名</th>
											<th>身份证</th>
											<th>注册时间</th>
											<th>最后使用车辆时间</th>
											
										</tr>
									</thead>
									<tbody>
									<c:forEach var="user" items="${userList }">
											<tr class="odd gradeA">
											    <td>${user.userTel }</td>
											    <td>${user.userRealname }</td>
												<td>${user.userIdcard }</td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${user.userCreatetime }"/></td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${user.userLastusebiketime }"/></td>
												
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
</body>
</html>

