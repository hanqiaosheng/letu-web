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

</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">操作日志</h1>
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
                            	<form action="cms/operate/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <%-- <div class="col-md-2">
                                    <span class="">标题</span>
                                    <input type="text" class="form-control" placeholder="请输入标题" value="${sysMsg.sysMsgTitle}" name="sysMsgTitle" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">发布状态</span>
                                    <select name="sysMsgIsonline" class="form-control" id="selectedState">
                                    	<option selected value="">全部</option>
										<option value="0">未发布</option>
										<option value="1">已发布</option>
                                    </select>
                                </div>     --%>
                                <div class="col-md-2 time-box">
                                    <span class="tit">时间：</span>
                                   	<input placeholder="创建时间" id="cStarttime" type="text" name="fromTime" value="${operateLog.fromTime}" class="form-control">
                                    <span class="line">-</span><input placeholder="创建时间" id="cEndtime" type="text" name="toTime" value="${operateLog.toTime}" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">操作人：</span>
                                    <input type="text" class="form-control" placeholder="请输入操作人" value="${adminRealname}" name="adminRealname" >
                                </div>  
                                 <div class="col-md-2">
                                    <span class="">内容：</span>
                                    <input type="text" class="form-control" placeholder="请输入查询内容" value="${operateLog.operateRemark}" name="operateRemark" >
                                </div>                    
                              	</form>
                            </div>
                            <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button> 
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>操作人</th>
											<th>时间</th>
											<th>内容</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${operateLogList}" var="operate">
											<tr class="odd gradeA">
												<td>${operate.operateId }</td>
												<td>${operate.admin.adminRealname }</td>
												<td><fmt:formatDate value='${operate.operateTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td class="center">${operate.operateRemark }</td>
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

