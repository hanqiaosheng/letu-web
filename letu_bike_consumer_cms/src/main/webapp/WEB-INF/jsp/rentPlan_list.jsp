<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				location.href="${basePath}cms/admin/deleteAdmin.action?adminId="+obj;
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
				<h1 class="page-header">租赁费用列表</h1>
				
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							
								<a class="btn btn-success" href="cms/channel/addRentPlanJsp.action">添加租赁费用</a>
								<br>
								<br>
								<form action="cms/channel/rentMoney.action" method="post" id="form" class="form-inline">
								<input type="hidden" id="pageIndex" name="pageIndex">
								 <label>渠道号：</label>
								<select name="channelid"  class="form-control">
								<option value="">全部</option>
								<c:forEach var="channel" items="${channelList }">
								<option value="${channel.channelId}"<c:if test="${channel.channelId==channelid }">selected="selected"</c:if>>${channel.channelId }</option>
								</c:forEach>
								</select>
							   <label>渠道名称：</label>
								<select name="channelname"  class="form-control">
								<option value="">全部</option>
								<c:forEach var="channel" items="${channelList }">
								<option value="${channel.channelName }"<c:if test="${channel.channelName==channelname }">selected="selected"</c:if>>${channel.channelName }</option>
								</c:forEach>
								    
								</select>					
								
                                <button class="btn btn-primary" type="submit">查询</button>
							</form>
						</div>
						<!-- /.panel-heading -->
						
						
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>渠道号</th>
											<th>型号</th>
											<th>型号名</th>
											<th>渠道名称</th>
											<th>费用</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${modelList }" var="models">
											<tr class="odd gradeA">
												<td>${models.modelsId }</td>
												<td>${models.channel.channelId }</td>
												<td>${models.modelsCode }</td>
												<td>${models.modelsName }</td>
												<td>${models.channel.channelName }</td>
												<td>${models.modelsRentPrice }</td>
												<td>
												<a class="btn btn-info"
													href="cms/channel/editRentPlanJsp.action?rentPlanId=${rentPlan.rentPlanId }">修改</a>
												<a class="btn btn-danger"
													href="cms/channel/updatePlan.action?rentPlanId=${rentPlan.rentPlanId }&flag=0">删除</a>
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
</body>
</html>

