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
				if(obj!=''){
					$.ajax({
						url:"cms/user/insurance/update.action?insuranceId="+obj+"&insuranceState=3",
						type:'post',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
				}else{
					deleteAll();
				}
				
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
	
	function update(objA,objB){
		$.ajax({
			url:"cms/user/insurance/update.action?insuranceId="+objA+"&insuranceState="+objB,
			type:'post',
			success:function(data){
				if(data!=""){
					window.location.reload();
				}
			}
		});
	}
    
    function checkAll() {
    	if($("#checkAllBox").val()=='1'){
    		$(":checkbox").prop("checked",true);
    		$("#checkAllBox").val("0");
    	}else if($("#checkAllBox").val()=='0'){
    		$(":checkbox").prop("checked",false);
    		$("#checkAllBox").val("1");
    	}
	}
    function deleteAll(obj) {
    	if(obj!=''){
    		$.ajax({
    			type:'post',
    			data:$('#checkAllForm').serialize(),
    			url:"cms/user/insurance/update.action?insuranceState=3",
    			success:function(data){	
    					window.location.reload();
    			}
    		});
    	}else{
    		$.ajax({
    			type:'post',
    			data:$('#checkAllForm').serialize(),
    			url:"cms/user/insurance/update.action?insuranceState=2",
    			success:function(data){	
    					window.location.reload();
    			}
    		});
    	}
	}
    function refuseAll() {
    	$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:"cms/user/insurance/update.action?insuranceState=1",
			success:function(data){	
					window.location.reload();
			}
		});
	}
    
    var selectedOp="${insurance.insuranceState}";
    $(document).ready(function(){
    	if(selectedOp=='0')
    		$("#selectedState option[value='0']").attr("selected",true);
    	if(selectedOp=='1')
    		$("#selectedState option[value='1']").attr("selected",true);
    	if(selectedOp=='2')
    		$("#selectedState option[value='2']").attr("selected",true);
   	});
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">用户保险申请</h1>
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
                            	<form action="cms/user/insurance/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">用户账号</span>
                                    <input type="text" class="form-control" placeholder="请输入账号" value="${insurance.iUserTel}" name="iUserTel" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">姓名</span>
                                    <input type="text" class="form-control" placeholder="请输入姓名" value="${insurance.iUserName}" name="iUserName" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">处理状态</span>
                                    <select name="insuranceState" class="form-control" id="selectedState">
                                    	<option selected value="">全部</option>
										<option value="0">未处理</option>
										<option value="1">已拒绝</option>
										<option value="2">已接受</option>
                                    </select>
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">申请时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="fromTime" value="${insurance.fromTime }" class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="toTime" value="${insurance.toTime }" class="form-control">
                                </div>                         
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/user/insurance/update.action?insuranceState=2" id="checkAllForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
<!-- 											<th></th> -->
											<th>用户账号</th>
											<th>姓名</th>
											<th>申请时间</th>
											<th>内容</th>
											<th>状态</th>
											<th>提交人</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${insuranceList}" var="insurance">
											<tr class="odd gradeA">
<!-- 												<td> -->
<%-- 												<c:if test="${insurance.insuranceState == 0 }"> --%>
<%-- 													<input type="checkbox" name="insuranceIds" value="${insurance.insuranceId }"> --%>
<%-- 												</c:if> --%>
<!-- 												</td> -->
												<td>${insurance.iUserTel }</td>
												<td>${insurance.iUserName }</td>
												<td><fmt:formatDate value='${insurance.insuranceApplyTime}' pattern='yyyy-MM-dd'/></td>
												<td>${insurance.insuranceContent }</td>
												<td class="center">
													<c:if test="${insurance.insuranceState == 0 }">
														<span style="color:orange;">未处理</span>
													</c:if>
													<c:if test="${insurance.insuranceState == 1 }">
														<span style="color:blue;">已拒绝</span>
													</c:if> 
													<c:if test="${insurance.insuranceState == 2 }">
														<span style="color:green;">已接受</span>
													</c:if>
												</td>
												<td>${insurance.iAdminName }</td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/user/insurance/detail.action?insuranceId=${insurance.insuranceId }">详情</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
							</form>
<!-- 							<input id="checkAllBox" type="hidden" value="1" /> -->
<!-- 							<input  type="checkbox"  onclick="checkAll();" />全选 -->
<!-- 							<button class="btn btn-success" onclick="deleteAll('')">批量接受</button> -->
<!-- 							<button class="btn btn-danger" onclick="refuseAll();">批量拒绝</button> -->
<!-- 							<button class="btn btn-danger" onclick="checkDel('');">批量删除</button> -->
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

