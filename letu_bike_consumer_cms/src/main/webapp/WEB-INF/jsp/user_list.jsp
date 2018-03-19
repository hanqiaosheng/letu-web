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
						url:"cms/user/update.action?userState=2&userId="+obj,
						type:'post',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
				}
				else{
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
    			url:'cms/user/update.action?userState=2',
    			success:function(data){	
    					window.location.reload();
    			}
    		});
    	}else{
    		$.confirm($('#myModal').html(),[{yes:"提交"},{no:'取消'}],function(type,e){
    			if(type=='yes'){
    				var textareaHtml=e.find('textarea').val();
    				if(!textareaHtml){
    					$.alert("请输入拉黑理由");
    					return
    				}else{
    					$("#defriendReason").val(textareaHtml);
    				}
    				
    				$.ajax({
    	    			type:'post',
    	    			data:$('#checkAllForm').serialize(),
    	    			url:'cms/user/updateBlacklist.action?userIsblacklist=0',
    	    			success:function(data){	
    	    					window.location.reload();
    	    			}
    	    		});
    					
    				
    		    }else{
    		    	this.hide();
    		    }
    		    
    		},{width:500});
    		
    	}
    	
	}
    function toBlack(obj){
    	$.confirm($('#myModal').html(),[{yes:"提交"},{no:'取消'}],function(type,e){
			if(type=='yes'){
				var textareaHtml=e.find('textarea').val();
				if(!textareaHtml){
					$.alert("请输入拉黑理由");
					return
				}else{
					$("#defriendReason").val(textareaHtml);
				}
				
				$.ajax({
					type:'post',
					data:$('#checkAllForm').serialize(),
					url:'cms/user/updateBlacklist.action?userIsblacklist=0&userId='+obj,
					success:function(data){	
							window.location.reload();
					}
				});
					
				
		    }else{
		    	this.hide();
		    }
		    
		},{width:500});
    }
    

</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">用户列表</h1>
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
                            	<form action="cms/user/list.action" method="post" id="searchForm">
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
                                    <span class="tit">注册时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="fromTime" value="${user.fromTime }"  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="toTime" value="${user.toTime }" class="form-control">
                                </div>                               
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/user/updateBlacklist.action?userIsblacklist=0" id="checkAllForm" method="post">
								<input type="hidden" name="defriendReason" id="defriendReason">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th></th>
											<th>账号</th>
											<th>姓名</th>
											<th>身份证</th>
											
											<th>注册时间</th>
											<th>状态</th>
											<th>账户详情</th>
											<th>租赁详情</th>
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
												
												<td><fmt:formatDate value='${user.userCreatetime}' pattern='yyyy-MM-dd HH:mm'/></td>
												<td class="center">
												<c:if test="${user.userIsblacklist == 0 }">
													拉黑
												</c:if>
												<c:if test="${user.userIsblacklist == 1 }">
													<c:if test="${user.userState == 0 }">
														<span style="color: green;">空闲</span>
													</c:if>
													<c:if test="${user.userState == 1 }">
														<span style="color:orange;">租借</span>
													</c:if> 
												</c:if>
												</td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/user/account/detail.action?moneyLogAccountId=${user.userAccountId }">详情</a>
												</td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/user/bikeRentInfoList.action?rentInfoUserId=${user.userId }">详情</a>
												</td>
												<td class="center">
												<shiro:hasPermission name="userUpdateButton">
													<a class="btn btn-primary"
															href="cms/user/editJsp.action?userId=${user.userId }" >修改</a>
												</shiro:hasPermission>				
													<c:if test="${user.userIsblacklist == 1}">
												<shiro:hasPermission name="userFrozenButton">	
														<a class="btn btn-warning"
															href="javascript:void(0)" onclick="toBlack('${user.userId }')">拉黑</a>
												</shiro:hasPermission>			
													</c:if> 
													<c:if test="${user.userState == 0|| user.userState == 1}">
												<shiro:hasPermission name="userDeleteButton">
														<a class="btn btn-danger"
															href="javascript:void(0)" onclick="checkDel('${user.userId }')">删除</a>
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
							<shiro:hasPermission name="userDeleteButton">
							<button class="btn btn-warning" onclick="deleteAll('')">批量拉黑</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="userDeleteButton">
							<button class="btn btn-danger" onclick="checkDel('');">批量删除</button>
							</shiro:hasPermission>
							
							<!-- 拉黑弹框 -->
							<div id="myModal" style="display:none;">
						<form class="form-horizontal" style="padding:20px 20px 0 20px;" role="form" action="" method="post" id="refundReasonForm">
					      <div class="form-group">
					        <label for="inputEmail3" class="col-sm-2 control-label" style="line-height:20px">拉黑理由</label>
					        <div class="col-sm-10">
					           <textarea class="form-control" style="margin-top:10px;margin-bottom:10px;height:70px;resize:none;" placeholder="请输入拉黑理由...... "></textarea>
					        </div>
					      </div>
					   </form>

                       </div>
							
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

