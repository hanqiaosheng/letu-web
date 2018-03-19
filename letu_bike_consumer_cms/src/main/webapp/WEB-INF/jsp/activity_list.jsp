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
function checkDel(obj){
	var mess = "确认删除吗？";
	$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
		if(type=='yes'){
			$.ajax({
				url:"${basePath}/cms/activity/delete.action?activityId="+obj,
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
				<h1 class="page-header">活动列表</h1>
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
                            	<form action="cms/activity/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">活动标题</span>
                                    <input placeholder="活动标题" type="text"  name="name" value="${name }" class="form-control">
                                </div>
                                
                                <div class="col-md-2 time-box">
                                    <span class="tit">活动创建时间</span>
                                   	<input placeholder="活动创建起始时间" id="cStarttime" type="text" name="startTime" value='<fmt:formatDate value="${startTime }"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="活动创建截至时间" id="cEndtime" type="text" name="endTime" value='<fmt:formatDate value="${endTime }"/>' class="form-control">
                                </div>
                                
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="activityAddButton">
	                                    <a  class="btn btn-success" href="cms/activity/addJsp.action">添加</a>
	                                </shiro:hasPermission>
	                                </div>
                           </div>
                       </div>
                       
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTab
							le_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>活动名称</th>
											<th>活动说明</th>
											<th>置顶号</th>
											<th>城市</th>
											<th>活动创建时间</th>
											<th>链接</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${activities }" var="activity">
											<tr class="odd gradeA">
											    <td>${activity.activityId }</td>
												<td>${activity.activityName }</td>
												<td>${activity.activityInstruction }</td>
												<td>${activity.activityTopnum }</td>
												<td>${activity.cityName }</td>
												<td><fmt:formatDate value="${activity.activityCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>${activity.activityUrl}</td>
												<td class="center">
														<a class="btn btn-info"
														href="cms/activity/detail.action?activityId=${activity.activityId}">详情</a>
														<shiro:hasPermission name="activityUpdateButton">
														<a class="btn btn-primary"
														href="cms/activity/editJsp.action?activityId=${activity.activityId}">修改</a>
														</shiro:hasPermission>
														<shiro:hasPermission name="activityDeleteButton">
														<a class="btn btn-danger"  onclick="checkDel('${activity.activityId}',-1)" >删除</a>
														</shiro:hasPermission>
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

