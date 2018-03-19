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
					url:"cms/couponplan/delCouponPlan.action?couponPlanId="+obj,
					type:'post',
					success:function(data){
						if(data=="success"){
							window.location.reload();
						}else if(data=="fail"){
							$.alert("代金券方案使用中，无法删除!",true,function(){
				            },false,{className:'ui-alert',width:300});
						}
					}
				});
				
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
	 
	function checkEdit(obj){
		$.ajax({
			type:'post',
			data:'couponPlanId='+obj,
			url:'cms/couponplan/checkCouponPlan.action',
			success:function(data){
				if(data=="success"){
					window.location.href="${basePath}/cms/couponplan/couponPlanEditJsp.action?couponPlanId="+obj
				}else if(data=="fail"){
					$.alert("代金券方案使用中，无法修改!",true,function(){
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
				<h1 class="page-header">代金券方案列表</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					<div class="panel-heading">
						<a class="btn btn-success" href="cms/couponplan/couponPlanAddJsp.action">添加</a>
						</div>
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
                            	<form action="cms/couponplan/couponPlanList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">代金券方案名称</span>
                                    <input type="text" class="form-control" placeholder="请输入代金券方案名称" value="${couponPlanName}" name="couponPlanName" >
                                </div>
                              	</form>
                            </div>
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
											<th>代金券方案名称</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${couponPlanList}" var="couponPlan">
											<tr class="odd gradeA">
												<td>${couponPlan.couponPlanId }</td>
												<td>${couponPlan.couponPlanName }</td>
												<td>
												 <fmt:formatDate value='${couponPlan.couponPlanCreateTime}' pattern='yyyy-MM-dd'/>
												</td>
												<td class="center">
													<a class="btn btn-success"
													href="cms/couponplan/planDetail.action?couponPlanId=${couponPlan.couponPlanId }">详情</a>
													<%-- <a class="btn btn-primary"
													href="javascript:void(0)" onclick="checkEdit('${couponPlan.couponPlanId }')">修改</a> --%>
													<a class="btn btn-danger"
														href="javascript:void(0)" onclick="checkDel('${couponPlan.couponPlanId }')">删除</a>
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

