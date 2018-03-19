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
						url:"cms/user/update.action?userId="+obj+"&userState=2&flag=1",
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
    			url:'cms/user/update.action?userState=2&flag=1',
    			success:function(data){	
    					window.location.reload();
    			}
    		});
    	}else{
    		$.ajax({
    			type:'post',
    			data:$('#checkAllForm').serialize(),
    			url:'cms/user/updateBlacklist.action?userIsblacklist=1&flag=1',
    			success:function(data){	
    					window.location.reload();
    			}
    		});
    	}
	}
    function recovery(obj){
    	$.ajax({
			url:"cms/user/updateBlacklist.action?userId="+obj+"&userIsblacklist=1&flag=1",
			type:'post',
			success:function(data){
				if(data!=""){
					window.location.reload();
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
				<h1 class="page-header">黑名单</h1>
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
                            	<form action="cms/user/blackList.action?userIsblacklist=0" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">账号</span>
                                    <input type="text" class="form-control" placeholder="请输入账号" value="${user.userTel}" name="userTel" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">身份证</span>
                                    <input type="text" class="form-control" placeholder="请输入身份证" value="${user.userIdcard}" name="userIdcard" >
                                </div>
								<div class="col-md-2">
                                    <span class="">姓名</span>
                                    <input type="text" class="form-control" placeholder="请输入姓名" value="${user.userRealname}" name="userRealname" >
                                </div>   
                                <div class="col-md-2 time-box">
                                    <span class="tit">拉黑时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name=fromTime value="${user.fromTime }"  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="toTime" value="${user.toTime }" class="form-control">
                                </div>                             
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/user/updateBlacklist.action?userIsblacklist=1&flag=1" id="checkAllForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th></th>
											<th>手机号</th>
											<th>姓名</th>
											<th>身份证</th>
											<th>拉黑时间</th>
											<th>拉黑理由</th>
											<th>操作人</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${userList}" var="user">
											<tr class="odd gradeA">
												<td>
												<input type="checkbox" name="userIds" value="${user.userId }">
												</td>
												<td>${user.userTel }</td>
												<td>${user.userRealname }</td>
												<td>${user.userIdcard }</td>
												<td><fmt:formatDate value='${user.userDefriendTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td>${user.defriendReason }</td>
												<td class="center">
													${user.defriendAdminName}
												</td>
												<td class="center">
													<c:if test="${user.userIsblacklist == 0}">
													<shiro:hasPermission name="blacklistRecoveryButton">
														<a class="btn btn-primary"
															href="javascript:void(0)" onclick="recovery('${user.userId }');">恢复</a>
													</shiro:hasPermission>	
													</c:if> 
													<c:if test="${user.userState == 0|| user.userState == 1}">
													<shiro:hasPermission name="blacklistDeleteButton">
														<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${user.userId }');">删除</a>
													</shiro:hasPermission>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
							<input id="checkAllBox" type="hidden" value="1" />
								<input  type="checkbox"  onclick="checkAll();" />全选
								<shiro:hasPermission name="blacklistRecoveryButton">
								<button class="btn btn-primary" onclick="deleteAll('');">批量恢复</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="blacklistDeleteButton">
								<button class="btn btn-danger" onclick="checkDel('');">批量删除</button>
								</shiro:hasPermission>
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

