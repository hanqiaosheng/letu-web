<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
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
					data:'planId='+obj+'&planState=0',
					url:'cms/redeemplan/updateState.action',
					success:function(data){
						if(data=="success"){
							window.location.reload();
						}else if(data=="fail"){
							$.alert("方案已上线，无法删除，请先下线!",true,function(){
				            },false,{className:'ui-alert',width:300});
						}
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
	
	function updateState(obja,objb){
		$.ajax({
			type:'post',
			data:'planId='+obja+'&planState='+objb,
			url:'cms/redeemplan/updateState.action',
			success:function(data){
				if(data=="success"){
					window.location.reload();
				}
			}
		});
	}
	
	function checkEdit(obj){
		$.ajax({
			type:'post',
			data:'planId='+obj,
			url:'cms/redeemplan/checkState.action',
			success:function(data){
				if(data=="success"){
					window.location.href="${basePath}/cms/redeemplan/redeemPlanEditJsp.action?redeemPlanId="+obj
				}else if(data=="fail"){
					$.alert("方案已上线，无法修改，请先下线!",true,function(){
		            },false,{className:'ui-alert',width:300});
				}
			}
		});
	}
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">兑换方案列表</h1>
				
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
								<form action="cms/redeemplan/redeemPlanList.action" method="post" id="searchForm" >
								<input type="hidden" id="pageIndex" name="pageIndex">
								<div class="col-md-2">
                                   <span class="">代金券兑换名称</span>
                                   <input placeholder="代金券兑换名称" name="redeemPlanName" type="text" value="${redeemPlanName}" class="form-control">
                                </div> 
							</form>
                            </div>
                            <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
							<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                                   <a class="btn btn-success" href="cms/redeemplan/redeemPlanJsp.action">新建兑换方案</a>
	                                </div>
                           </div>
						</div>
						<!-- /.panel-heading -->
						
						
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>代金券兑换名称</th>
											<th>兑换码</th>
											<th>代金券方案</th>
											<th>可兑换次数</th>
											<th>已兑换次数</th>
											<th>创建时间</th>
											<th>上线时间</th>
											<th>下线时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${redeemPlanList }" var="redeemPlan" varStatus="varStatus">
											<tr class="odd gradeA">
												<td>${redeemPlan.planId }</td>
												<td>${redeemPlan.planName }</td>
												<td>${redeemPlan.planRedeemCode }</td>
												<td>${redeemPlan.couponPlan.couponPlanName }</td>
												<td>${redeemPlan.planSurplusNums }</td>
												<td>${redeemPlan.planRedeemNums-redeemPlan.planSurplusNums }</td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${redeemPlan.planCreateTime }" /></td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${redeemPlan.planOnlineTime }" /></td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${redeemPlan.planOfflineTime }" /></td>
												<td>
												    <a class="btn btn-info" href="cms/redeemplan/redeemPlanDetail.action?redeemPlanId=${redeemPlan.planId }">详情</a>
													<c:if test="${redeemPlan.planState==2 }" >
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${redeemPlan.planId }','1')">上线</a>
													</c:if>
													<c:if test="${redeemPlan.planState==1 }">
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${redeemPlan.planId }','2')">下线</a>
													</c:if>
													<%-- <a class="btn btn-primary" href="javascript:void(0)" onclick="checkEdit('${redeemPlan.planId }')">修改</a>  --%>
													<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${redeemPlan.planId }')">删除</a>
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

