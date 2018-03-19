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
						url:"cms/user/feedback/update.action?feedbackId="+obj+"&feedbackState=3",
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
		if(objB==1){
			$.confirm($('#myModal').html(),[{yes:"提交"},{no:'取消'}],function(type,e){
				if(type=='yes'){
					var textareaHtml=e.find('textarea').val();
					if(!textareaHtml){
						$.alert("请输入拒绝理由");
						return
					}
					
					$.ajax({
						url:"cms/user/feedback/update.action?feedbackId="+objA+"&feedbackState="+objB+"&feedbackRefuseReason="+textareaHtml,
						type:'post',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
						
					
			    }else{
			    	this.hide();
			    }
			    
			},{width:500});
			
		}else if(objB==2){
			$.ajax({
				url:"cms/user/feedback/update.action?feedbackId="+objA+"&feedbackState="+objB,
				type:'post',
				success:function(data){
					if(data!=""){
						window.location.reload();
					}
				}
			});
		}
		
	}
	
	function updateRemark(obj,obj1){
		     $(".remark").html(obj1);
		
			$.confirm($('#remarkModal').html(),[{yes:"提交"},{no:'取消'}],function(type,e){
				if(type=='yes'){
					var textareaHtml=e.find('textarea').val();
					if(!textareaHtml){
						$.alert("请输入备注");
						return
					}
					
					$.ajax({
						url:"cms/user/feedback/update.action?feedbackId="+obj+"&feedbackState="+4+"&feedbackRemark="+textareaHtml,
						type:'post',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
						
					
			    }else{
			    	this.hide();
			    }
			    
			},{width:500});
			
		
		
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
    			url:"cms/user/feedback/update.action?feedbackState=3",
    			success:function(data){	
    					window.location.reload();
    			}
    		});
    	}else{
    		$.ajax({
    			type:'post',
    			data:$('#checkAllForm').serialize(),
    			url:"cms/user/feedback/update.action?feedbackState=2",
    			success:function(data){	
    					window.location.reload();
    			}
    		});
    	}
	}
    function refuseAll() {
    	$.confirm($('#myModal').html(),[{yes:"提交"},{no:'取消'}],function(type,e){
			if(type=='yes'){
				
				var textareaHtml=e.find('textarea').val();
				if(!textareaHtml){
					$.alert("请输入拒绝理由");
					return
				}else{
					$("#reason").val(textareaHtml);
				}
				
				$.ajax({
					type:'post',
					data:$('#checkAllForm').serialize(),
					url:"cms/user/feedback/update.action?feedbackState=1",
					success:function(data){	
							window.location.reload();
					}
				});
				
		    }else{
		    	this.hide();
		    }
		    
		},{width:500});
    	
	}
    
    var selectedOp="${feedback.feedbackState}";
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
				<h1 class="page-header">用户使用车辆反馈</h1>
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
                            	<form action="cms/user/feedback/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">用户账号</span>
                                    <input type="text" class="form-control" placeholder="请输入账号" value="${feedback.fUserTel}" name="fUserTel" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">姓名</span>
                                    <input type="text" class="form-control" placeholder="请输入姓名" value="${feedback.fUserName}" name="fUserName" >
                                </div>
                               <div class="col-md-2">
                                    <span class="">反馈类型</span>
                                    <select name="feedbackTypeId" class="form-control" id="">
                                    	<option selected value="" >全部</option>
										<option value="22" <c:if test="${feedback.feedbackTypeId==22 }">selected</c:if>>车辆违规</option>
										<option value="23" <c:if test="${feedback.feedbackTypeId==23 }">selected</c:if>>使用错误</option>
										<option value="24" <c:if test="${feedback.feedbackTypeId==24 }">selected</c:if>>异常反馈</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <span class="">处理状态</span>
                                    <select name="feedbackState" class="form-control" id="selectedState">
                                    	<option selected value="">全部</option>
										<option value="0">未处理</option>
										<option value="1">已拒绝</option>
										<option value="2">已接受</option>
                                    </select>
                                </div>  
                                <div class="col-md-2 time-box">
                                    <span class="tit">反馈时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="fromTime" value="<fmt:formatDate value='${feedback.fromTime }' pattern='yyyy-MM-dd'/>" class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="toTime" value="<fmt:formatDate value='${feedback.toTime }' pattern='yyyy-MM-dd'/>" class="form-control">
                                </div>                       
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/user/feedback/update.action?feedbackState=2" id="checkAllForm" method="post">
								<input type="hidden" name="feedbackRefuseReason" id="reason">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th></th>
											<th>用户账号</th>
											<th>姓名</th>
											<th>反馈类型</th>
											<th>反馈时间</th>
											<th style="width:30%">反馈内容</th>
											<th style="width:10%">反馈备注</th>
											<th>状态</th>
											<th>详情</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${feedbackList}" var="feedback">
											<tr class="odd gradeA">
												<td>
												<c:if test="${feedback.feedbackState == 0 }">
													<input type="checkbox" name="feedbackIds" value="${feedback.feedbackId }">
												</c:if>
												</td>
												<td>${feedback.fUserTel }</td>
												<td>${feedback.fUserName }</td>
												<td>${feedback.fDataDetVal }</td>
												<td><fmt:formatDate value='${feedback.feedbackTime}' pattern='yyyy-MM-dd HH:mm'/></td>
												<td>${feedback.feedbackContent }</td>
												<td>${feedback.feedbackRemark }</td>
												<td class="center">
													<c:if test="${feedback.feedbackState == 0 }">
														<span style="color:orange;">未处理</span>
													</c:if>
													<c:if test="${feedback.feedbackState == 1 }">
														<span style="color:blue;">已拒绝</span>
													</c:if> 
													<c:if test="${feedback.feedbackState == 2 }">
														<span style="color:green;">已接受</span>
													</c:if>
													<c:if test="${feedback.feedbackState == 4 }">
														已处理
													</c:if>
												</td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/user/feedback/detail.action?feedbackId=${feedback.feedbackId }">详情</a>
												</td>
												<td class="center">
													
													<c:if test="${feedback.feedbackState == 0}">
													<shiro:hasPermission name="userFeedbackAcceptButton">
														<a class="btn btn-primary"
														href="javascript:void(0)" onclick="update('${feedback.feedbackId }','2');">接受</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="userFeedbackRefuseButton">	
														<a class="btn btn-warning"
														href="javascript:void(0)" onclick="update('${feedback.feedbackId }','1');">拒绝</a>
													</shiro:hasPermission>
													</c:if> 
													<c:if test="${feedback.feedbackState == 4}">
													<shiro:hasPermission name="userFeedbackAcceptButton">
														<a class="btn btn-primary"
														href="javascript:void(0)" onclick="updateRemark('${feedback.feedbackId }','${feedback.feedbackRemark }');">修改备注</a>
													</shiro:hasPermission>
													</c:if>
													<c:if test="${feedback.feedbackState == 2}">
													<shiro:hasPermission name="userFeedbackAcceptButton">
														<a class="btn btn-primary"
														href="cms/user/feedback/remarkDetail.action?feedbackId=${feedback.feedbackId }">处理</a>
													</shiro:hasPermission>
													</c:if>
													<shiro:hasPermission name="userFeedbackDeleteButton">
														<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${feedback.feedbackId}')">删除</a>
													</shiro:hasPermission>	
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
							</form>
							<input id="checkAllBox" type="hidden" value="1" />
							<input  type="checkbox"  onclick="checkAll();" />全选
							<shiro:hasPermission name="userFeedbackAcceptButton">
							<button class="btn btn-primary" onclick="deleteAll('')">批量接受</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="userFeedbackRefuseButton">
							<button class="btn btn-warning" onclick="refuseAll();">批量拒绝</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="userFeedbackDeleteButton">
							<button class="btn btn-danger" onclick="checkDel('');">批量删除</button>
							</shiro:hasPermission>
							
					 <!---拒绝理由的弹框-->
					   <div id="myModal" style="display:none;">
						<form class="form-horizontal" style="padding:20px 20px 0 20px;" role="form" action="" method="post" id="refundReasonForm">
					      <div class="form-group">
					        <label for="inputEmail3" class="col-sm-2 control-label" style="line-height:20px">拒绝理由</label>
					        <div class="col-sm-10">
					           <textarea class="form-control" name="feedbackRefuseReason" style="margin-top:10px;margin-bottom:10px;height:70px;resize:none;" placeholder="请输入拒绝理由...... "></textarea>
					        </div>
					      </div>
					    </form>
                       </div>
                       <div id="remarkModal" style="display:none;">
						<form class="form-horizontal" style="padding:20px 20px 0 20px;" role="form" action="" method="post" id="feedbackRemark">
					      <div class="form-group">
					        <label for="inputEmail3" class="col-sm-2 control-label" style="line-height:20px">修改备注</label>
					        <div class="col-sm-10">
					           <textarea class="form-control remark" name="feedbackRemark" style="margin-top:10px;margin-bottom:10px;height:70px;resize:none;" ></textarea>
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

