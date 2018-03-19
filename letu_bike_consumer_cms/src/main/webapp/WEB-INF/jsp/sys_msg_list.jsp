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
						url:"${basePath}/cms/sysMsg/update.action?sysMsgId="+obj+"&sysMsgIsonline=-1",
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
	 function online(flag,obj){
			 $.ajax({
					url:"${basePath}/cms/sysMsg/update.action?sysMsgId="+obj+"&sysMsgIsonline="+flag,
					type:'post',
					success:function(data){
						if(data!=""){
							window.location.reload();
						}
					}
				}); 
	 }
	function deleteAll() {
		$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:'${basePath}/cms/sysMsg/update.action?sysMsgIsonline=-1',
			success:function(data){	
					window.location.reload();
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
    
    function checkState(obj){
    	$.ajax({
    		type:"post",
    		url:"cms/sysMsg/checkSysMsg.action",
    		data:"sysMsgId="+obj,
    		success:function(data){
    			if(data.message=="success"){
    				JavaScript:window.location.href="${basePath}/cms/sysMsg/editJsp.action?sysMsgId="+obj
    			}else if(data.message=="fail"){
    				$.alert("已上线，无法修改，请先下线！",true,function(){
		            },false,{className:'ui-alert',width:300});
    			}
    		}
    	})
    }
    
    var selectedOp="${sysMsg.sysMsgIsonline}";
    $(document).ready(function(){
    	if(selectedOp=='0')
    		$("#selectedState option[value='0']").attr("selected",true);
    	if(selectedOp=='1')
    		$("#selectedState option[value='1']").attr("selected",true);
   	});
    
   
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">消息列表</h1>
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
                            	<form action="cms/sysMsg/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">标题</span>
                                    <input type="text" class="form-control" placeholder="请输入标题" value="${sysMsg.sysMsgTitle}" name="sysMsgTitle" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">发布状态</span>
                                    <select name="sysMsgIsonline" class="form-control" id="selectedState">
                                    	<option selected value="">全部</option>
										<option value="0">未发布</option>
										<option value="1">已发布</option>
                                    </select>
                                </div>    
                                <div class="col-md-2 time-box">
                                    <span class="tit">时间</span>
                                   	<input placeholder="创建时间" id="cStarttime" type="text" name="fromTime" value="${sysMsg.fromTime}" class="form-control">
                                    <span class="line">-</span><input placeholder="创建时间" id="cEndtime" type="text" name="toTime" value="${sysMsg.toTime}" class="form-control">
                                </div>                     
                              	</form>
                            </div>
                            <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="addMessageButton">
	                                   <a class="btn btn-success" href="cms/sysMsg/addJsp.action">添加消息</a>
	                                </shiro:hasPermission>
	                                </div>
                           </div>
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/sysMsg/update.action?sysMsgIsonline=-1" id="checkAllForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th></th>
											<th>标题</th>
											<th>内容</th>
											<th>状态</th>
											<th>编辑人</th>
											<th>创建时间</th>
											<th>上线时间</th>
											<th>下线时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${sysMsgList}" var="sysMsg">
											<tr class="odd gradeA">
												<td>
												<input type="checkbox" name="sysMsgIds" value="${sysMsg.sysMsgId }">
												</td>
												<td>${sysMsg.sysMsgTitle }</td>
												<td>${sysMsg.sysMsgContent }</td>
												<td>
													<c:if test="${sysMsg.sysMsgIsonline==1 }">
														已发布
													</c:if>
													<c:if test="${sysMsg.sysMsgIsonline==0 }">
														未发布
													</c:if>
												</td>
												<td class="center">${sysMsg.operationAdminName }</td>
												<td class="center"><fmt:formatDate value='${sysMsg.sysMsgCreatetime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td class="center"><fmt:formatDate value='${sysMsg.sysMsgOnlinetime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td class="center"><fmt:formatDate value='${sysMsg.sysMsgOfflinetime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td class="center">
													
													<c:if test="${sysMsg.sysMsgIsonline == 1}">
													<shiro:hasPermission name="messageOnlineButton">
														<a class="btn btn-warning"
															href="javascript:void(0)" onclick="online('0','${sysMsg.sysMsgId }')";>下线</a>
													</shiro:hasPermission>		
													</c:if> 
													<c:if test="${sysMsg.sysMsgIsonline == 0}">
													<shiro:hasPermission name="messageOnlineButton">
														<a class="btn btn-warning"
															href="javascript:void(0)" onclick="online('1','${sysMsg.sysMsgId }')";>上线</a>
													</shiro:hasPermission>
													</c:if>
													<a class="btn btn-info"
															href="cms/sysMsg/detail.action?sysMsgId=${sysMsg.sysMsgId }">详情</a>
													<shiro:hasPermission name="messageUpdateButton">	
													<a class="btn btn-warning" href="javascript:void(0)" onclick="checkState('${sysMsg.sysMsgId }')">修改</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="messageDeleteButton">
													<a class="btn btn-danger"
															href="javascript:void(0)" onclick="checkDel('${sysMsg.sysMsgId }');">删除</a>
													</shiro:hasPermission>		
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
							</form>
							<input id="checkAllBox" type="hidden" value="1" />
							<input  type="checkbox"  onclick="checkAll();" />全选
							<shiro:hasPermission name="messageDeleteButton">
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

