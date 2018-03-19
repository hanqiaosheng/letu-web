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
	
    
    function checkAll() {
    	if($("#checkAllBox").val()=='1'){
    		$(":checkbox").prop("checked",true);
    		$("#checkAllBox").val("0");
    	}else if($("#checkAllBox").val()=='0'){
    		$(":checkbox").prop("checked",false);
    		$("#checkAllBox").val("1");
    	}
	}
    function unFreezeAll(flag,obj) {
    	if(flag=='all'){
    		$.ajax({
    			type:'post',
    			data:$('#checkAllForm').serialize(),
    			url:'${basePath}/cms/user/account/isFreeze.action?accountIsFreeze=1',
    			success:function(data){	
    				if(data!=""){
						window.location.reload();
					}
    			}
    		});
    	}else if(flag='one'){
    		$.ajax({
				url:"${basePath}/cms/user/account/isFreeze.action?accountId="+obj+"&accountIsFreeze=1",
				type:'post',
				success:function(data){
					if(data!=""){
						window.location.reload();
					}
				}
			});
    }
   }
    function freezeAll(flag,obj) {
    	if(flag=='all'){
    		$.ajax({
    			type:'post',
    			async:false,
    			data:$('#checkAllForm').serialize(),
    			url:'${basePath}/cms/user/account/isFreeze.action?accountIsFreeze=0',
    			success:function(data){	
    				if(data!=""){
						window.location.reload();
					}
    			}
    		});
    	}else if(flag='one'){
    		$.ajax({
    			async:false,
				url:"${basePath}/cms/user/account/isFreeze.action?accountId="+obj+"&accountIsFreeze=0",
				type:'post',
				success:function(data){
					if(data!=""){
						window.location.reload();
					}
				}
			});
    	
	}
   }
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">账户信息</h1>
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
                            	<form action="cms/user/account/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">账号</span>
                                    <input type="text" class="form-control" placeholder="请输入账号" value="${account.aUserTel }" name="aUserTel"  maxlength="11">
                                </div>
                                <div class="col-md-2">
                                    <span class="">姓名</span>
                                    <input type="text" class="form-control" placeholder="请输入姓名" value="${aUserName }" name="aUserName"  maxlength="11">
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">最后缴纳预付款时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name=fromTime value="${account.fromTime }" class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="toTime" value="${account.toTime }" class="form-control">
                                </div> 
                                <div class="col-md-2 time-box">
                                    <span class="tit">最后消费时间</span>
                                   	<input placeholder="开始时间" id="cStarttimeB" type="text" name=fromTimeB value="${account.fromTimeB }" class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtimeB" type="text" name="toTimeB" value="${account.toTimeB }" class="form-control">
                                </div> 
								<div class="col-md-2">
                                    <span class="">预付款金额</span>
                                    <input type="text" class="form-control" placeholder="请输入金额" value="${account.accountDeposit}" name="accountDeposit" >
                                </div>    
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/user/account/isFreeze.action?accountIsFreeze=1" id="checkAllForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<!-- <th></th> -->
											<th>账号</th>
										    <th>姓名</th>
										    <th>身份证</th>
											<th>预付款金额</th>
										<!--  	<th>体验金额</th>-->
											<th>最后缴纳预付款时间</th>
											<th>最后消费时间</th>
											<th>最后退款时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${accountList}" var="account">
											<tr class="odd gradeA">
												<%-- <td>
												<input type="checkbox" name="accountIds" value="${account.accountId }">
												</td> --%>
												<td>${account.aUser.userTel }</td>
												<td>${account.aUser.userRealname }</td>
												<td>${account.aUser.userIdcard }</td>
												<!-- <td><fmt:formatNumber pattern="0.00" value="${account.accountAvailableBalance }"/></td>-->
												<td><fmt:formatNumber pattern="0.00" value="${account.accountDeposit }"/></td>
												<!--  <td><fmt:formatNumber pattern="0.00" value="${account.accountBbin }"/></td>-->
												<td><fmt:formatDate value='${account.accountFinalRechargeTime}' pattern='yyyy-MM-dd HH:mm'/></td>
												<td><fmt:formatDate value='${account.accountFinalConsumeTime}' pattern='yyyy-MM-dd HH:mm'/></td>
												<td><fmt:formatDate value='${account.accountFinalRefundTime}' pattern='yyyy-MM-dd HH:mm'/></td>
												<td class="center">
														<a class="btn btn-info"
															href="cms/user/account/detail.action?moneyLogAccountId=${account.accountId }">详情</a>
														<shiro:hasPermission name="accountUpdateButton">
														<a class="btn btn-primary" href="cms/user/account/editAccountJsp.action?accountId=${account.accountId }" >修改</a>
														</shiro:hasPermission>	
														<%-- <c:if test="${account.accountIsFreeze==0}">
															<a class="btn btn-success"
																href="javascript:void(0)" onclick="unFreezeAll('one','${account.accountId }');">解冻</a>
														</c:if>
														<c:if test="${account.accountIsFreeze==1}">
															<a class="btn btn-danger"
																href="javascript:void(0)" onclick="freezeAll('one','${account.accountId }');">冻结</a>
														</c:if>--%>
												</td> 
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
							</form>
							    <!-- <input id="checkAllBox" type="hidden" value="1" />
								<input  type="checkbox"  onclick="checkAll();" />全选
								<button class="btn btn-success" onclick="unFreezeAll('all','');">批量解冻</button>
								<button class="btn btn-danger" onclick="freezeAll('all','');">批量冻结</button> -->
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

