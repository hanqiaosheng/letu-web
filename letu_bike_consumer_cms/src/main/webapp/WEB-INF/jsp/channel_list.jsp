<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript">
function checkDel(objA,objB){
	if(objB==-1){
		var mess = "确认删除吗？";
	}else if(objB==0){
		var mess = "确认冻结吗？";
	}else if(objB==1){
		var mess = "确认解冻吗？";
	}
	
	$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
		if(type=='yes'){
			$.ajax({
				url:"${basePath}/cms/channel/frozenChannel.action?flag="+objB+"&channelId="+objA,
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
				<h1 class="page-header">渠道列表</h1>
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
                            	<form action="cms/channel/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">渠道编号</span>
                                   <input placeholder="渠道编号" type="text" name="channelNum" value="${channelNum }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">渠道名</span>
                                    <input placeholder="渠道名" type="text"  name="channelName" value="${channelName }" class="form-control">
                                </div>
								<div class="col-md-2">
                                    <span class="">渠道负责人</span>
                                    <input placeholder="渠道负责人" type="text"  name="charger" value="${charger }"  class="form-control">
                                </div>                                
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="addChannelButton">
	                                    <a  class="btn btn-success" href="cms/channel/gotoAdd.action">添加渠道</a>
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
											<th>渠道编号</th>
											<th>渠道名</th>
											<th>渠道负责人</th>
											<th>联系方式</th>
											<th>渠道创建人</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${sonChannels }" var="channel">
											<tr class="odd gradeA">
												<td>${channel.channelNum }</td>
												<td>${channel.channelName }</td>
												<td>${channel.channelChargerName }</td>
												<td>${channel.channelChargerTel}</td>
												<td>${channel.adminName }</td>
												<td class="center">
														<a class="btn btn-info"
														href="cms/channel/channelDetail.action?channelId=${channel.channelId}">详情</a>
														<shiro:hasPermission name="channelUpdateButton">
														<a class="btn btn-primary"
														href="cms/channel/channelUpdateJsp.action?channelId=${channel.channelId}">编辑</a>
														</shiro:hasPermission>
														<c:if test="${channel.channelState==1 }">
														<shiro:hasPermission name="channelFrozenButton">
														<a class="btn btn-warning"
														onclick="checkDel('${channel.channelId}',0)">冻结</a>
														</shiro:hasPermission>
														</c:if>
														<c:if test="${channel.channelState==0 }">
														<shiro:hasPermission name="channelFrozenButton">
														<a class="btn btn-warning"
														onclick="checkDel('${channel.channelId}',1)">解冻</a>
														</shiro:hasPermission>
														</c:if>
														<shiro:hasPermission name="channelDeleteButton">
														<a class="btn btn-danger"  onclick="checkDel('${channel.channelId}',-1)" >删除</a>
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

