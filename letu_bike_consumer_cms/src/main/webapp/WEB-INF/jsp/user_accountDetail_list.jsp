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
				<h1 class="page-header">账户记录</h1>
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
                            	<form action="cms/user/account/detail.action?moneyLogAccountId=${moneyLog.moneyLogAccountId}" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
								<div class="col-md-2">
                                    <span class="">操作类型</span>
                                    <select class="form-control" name="moneyLogStreamType">
										<option value= "" >全部</option>
										
										<option value= "1" <c:if test="${moneyLog.moneyLogStreamType==1 }">selected</c:if>>消费</option>
										<option value= "2" <c:if test="${moneyLog.moneyLogStreamType==2 }">selected</c:if>>退款</option>
										<option value= "3" <c:if test="${moneyLog.moneyLogStreamType==3 }">selected</c:if>>预付款补充</option>
										<option value= "4" <c:if test="${moneyLog.moneyLogStreamType==4 }">selected</c:if>>余额充值</option>
									</select>
                                </div> 
                                <div class="col-md-2 time-box">
                                    <span class="tit">操作时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name=fromTime value="${moneyLog.fromTime }" class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="toTime" value="${moneyLog.toTime }" class="form-control">
                                </div>   
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<div class="panel-heading">
								<label>账号：${user.userTel}</label>
								<label>姓名 ：${user.userRealname }</label>
								<label>预付款金额：<fmt:formatNumber pattern="0.00" value="${account.accountDeposit }"/>元</label>
							</div>
							 
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>操作类型</th>
											<th>金额（元）</th>
											<th>操作时间</th>
											<th>方式</th>
											<th>操作</th>
											
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${moneyLogList}" var="moneyLog">
											<tr class="odd gradeA">
												<td>
													<%-- <c:if test="${moneyLog.moneyLogStreamType==1 }">余额充值</c:if> --%>
													<c:if test="${moneyLog.moneyLogStreamType==1 }">消费</c:if>
													<c:if test="${moneyLog.moneyLogStreamType==2 }">退款</c:if>
													<c:if test="${moneyLog.moneyLogStreamType==4 }">余额充值</c:if>
													<c:if test="${moneyLog.moneyLogStreamType==3 }">预付款补充</c:if>
												</td>
												<td><fmt:formatNumber pattern="0.00" value="${moneyLog.moneyLogOpreateMoney }"/></td>
												<td><fmt:formatDate value='${moneyLog.moneyLogCreateTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td>
													<c:if test="${moneyLog.moneyLogOpreateId==1 }">微信支付</c:if>
													<c:if test="${moneyLog.moneyLogOpreateId==2 }">游客消费</c:if>
													<c:if test="${moneyLog.moneyLogOpreateId==3 }">会员消费</c:if>
													<c:if test="${moneyLog.moneyLogOpreateId==4 }">预付款退款</c:if>
													<c:if test="${moneyLog.moneyLogOpreateId==6 }">支付宝支付</c:if>
												</td>
												<td><a class="btn btn-danger"
													href="javascript:void(0)" onclick="checkDel('${moneyLog.moneyLogId }','${moneyLog.moneyLogAccountId}');">删除</a>
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

