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
					url:"cms/cashcoupon/delCashCoupon.action?cashCouponId="+obj,
					type:'post',
					success:function(data){
						if(data=="success"){
							window.location.reload();
						}else if(data=="fail"){
							$.alert("代金券使用中，无法删除!",true,function(){
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
			data:'couponId='+obj,
			url:'cms/cashcoupon/checkCoupon.action',
			success:function(data){
				if(data=="success"){
					window.location.href="${basePath}/cms/cashcoupon/cashCouponEditJsp.action?couponId="+obj
				}else if(data=="fail"){
					$.alert("代金券使用中，无法修改!",true,function(){
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
				<h1 class="page-header">代金券列表</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					<div class="panel-heading">
						<a class="btn btn-success" href="cms/cashcoupon/cashCouponJsp.action">添加</a>
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
                            	<form action="cms/cashcoupon/cashCouponList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">代金券名称</span>
                                    <input type="text" class="form-control" placeholder="请输入代金券名称" value="${couponName}" name="couponName" >
                                </div>
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/redeemplan/redeemMoneyList.action" id="checkAllForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>标号</th>
											<th>代金券名称</th>
											<th>代金券金额</th>
											<th>有效期</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${cashCouponList}" var="cashCoupon">
											<tr class="odd gradeA">
												<td>${cashCoupon.couponId }</td>
												<td>${cashCoupon.couponName }</td>
												<td>${cashCoupon.couponMoney }</td>
												<td>
												<c:if test="${cashCoupon.couponIsValidity==1}">
									 			<fmt:formatDate value='${cashCoupon.couponStartTime}' pattern='yyyy-MM-dd'/>---<fmt:formatDate value='${cashCoupon.couponEndTime}' pattern='yyyy-MM-dd'/>
												</c:if>
												<c:if test="${cashCoupon.couponIsValidity==0}">
												  无期限
												</c:if>
												<c:if test="${cashCoupon.couponIsValidity ==2}">
												 		${cashCoupon.couponValidityTime }天
												</c:if>
												</td>
												<td class="center"><fmt:formatDate value='${cashCoupon.couponCreateTime}' pattern='yyyy-MM-dd'/></td>						
												<td class="center">
													<a class="btn btn-success"
													href="cms/cashcoupon/cashCouponDetail.action?couponId=${cashCoupon.couponId }">详情</a>
													<%-- <a class="btn btn-primary" 
													href="javascript:void(0)" onclick="checkEdit('${cashCoupon.couponId }')">修改</a> --%>
													<a class="btn btn-danger"
													href="javascript:void(0)" onclick="checkDel('${cashCoupon.couponId }')">删除</a>
												</td>
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
</body>
</html>

