<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'refundId='+obj+'&refundState=-1',
					url:'cms/refund/refundState.action',
					success:function(data){
							window.location.href="${basePath}/cms/refund/refundList.action";
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
	
	function passRefund(obj){
		$.ajax({
			type:'post',
			async:false,
			data:'refundId='+obj+'&refundState=1',
			url:'cms/refund/refundState.action',
			success:function(data){
				window.location.href="${basePath}/cms/refund/refundList.action";
			}
			});
	}
	
	function postId(obj){
		$("#refundid").val(obj);
	}
	
	function reason(){
		$.ajax({
			type:'post',
			async:false,
			data:$('#refundReasonForm').serialize(),
			url:'cms/refund/refundState.action',
			success:function(data){
				window.location.href="${basePath}/cms/refund/refundList.action";
			}
			});
		
	}
</script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">退款信息详情</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
				<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
					<div class="row">
					
						<div class="col-lg-6">
								
									<div class="form-group">
										<label>退款账号:</label>
										<div class="form-group">
											<input class="form-control" type="text" value="${user.userTel }" readonly="readonly">
										</div>
										<label>退款发起时间:</label>
										<div class="form-group">
											<input class="form-control" type="text" value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${refund.refundCreatetime }"/>' readonly="readonly">
										</div>
										<%-- <label>退款处理人:</label>
										<div class="form-group">
											<input class="form-control" type="text" value="${admin.adminRealname }" readonly="readonly">
										</div> --%>
										<label>退款状态:</label>
										<div class="form-group">
										<select disabled="disabled"  class="form-control">						
											<option value="0"<c:if test="${refund.refundState==1 }">selected="selected"</c:if>>退款中</option>
											<option value="1"<c:if test="${refund.refundState==2 }">selected="selected"</c:if>>退款成功</option>
											<option value="2"<c:if test="${refund.refundState==3 }">selected="selected"</c:if>>退款关闭</option>
											<option value="3"<c:if test="${refund.refundState==4 }">selected="selected"</c:if>>退款未确定</option>
											<option value="4"<c:if test="${refund.refundState==5 }">selected="selected"</c:if>>退款处理中</option>
											<option value="5"<c:if test="${refund.refundState==6 }">selected="selected"</c:if>>退款异常</option>
								        </select>
										</div>
										<%-- <label>处理时间:</label>
										<div class="form-group">
											<input class="form-control" type="text" value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${refund.refundOperatetime }"/>' readonly="readonly">
										</div> --%>
										<label>退款金额:</label>
										<div class="form-group">
											<input class="form-control" type="text" value='<fmt:formatNumber pattern="0.00" value="${refund.refundMoney }"/>' readonly="readonly">
										</div>
										<%-- <label>退款拒绝理由:</label>
										<div class="form-group">
											<input class="form-control" type="text" value="${refund.refundRefuseReason }" readonly="readonly">
										</div> --%>
										<c:if test="${refund.refundState==0 }">
												<a class="btn btn-success" onclick="passRefund('${refund.refundId }')" href="javascript:void(0)">通过</a>
												<button onclick="postId('${refund.refundId }')" data-toggle="modal" data-target="#groupsRefund" data-to="#groupsRefund"  type="button" class="btn btn-danger" >拒绝</button>
										</c:if>
										<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${refund.refundId }')">删除</a>
									
								</div>
									<!---拒绝理由的弹框-->
						<div aria-hidden="true"  style="display: none;" id="groupsRefund" class="modal fade">
						  <div class="modal-dialog" style="width:600px; height:600px;margin:50px auto;">
						    <div class="modal-content">
						      <div class="modal-header" style="padding:0;">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"  style="height:50px;line-height:50px;padding-left:30px;border-bottom:1px solid #f5f5f5;">拒绝理由：</h4>
						      </div>
						      <form action method="post" id="refundReasonForm">
						      <div class="modal-dialog" style="width:300px; height:130px;margin-bottom:0;padding-left:30px;">
								     <input type="text" name="refundRefuseReason" style="width:300px;">
						             <input type="hidden" id="refundid" name="refundId">
						             <input type="hidden" name="refundState" value="2">
						      </div>
						      </form>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default btn-xs" data-dismiss="modal">取消</button>
						        <a class="btn btn-primary btn-xs" onclick="reason()" href="javascript:void(0)" data-dismiss="modal">确定</a>
						      </div>
						      
						    </div>
						  </div>
						</div>
						</div>
						<!-- /.col-lg-6 (nested) -->
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<%@include file="../common/buttom.jsp"%>
</html>
