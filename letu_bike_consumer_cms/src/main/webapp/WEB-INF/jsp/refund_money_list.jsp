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
					type:'post',
					data:'refundId='+obj+'&refundState=-1',
					url:'cms/refund/refundState.action',
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
	
	function deleteAll(){
		$("#hideRefundState").val(-1);
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				$.ajax({
				type:'post',
				async:false,
				data:$('#refundForm').serialize(),
				url:'cms/refund/refundAllState.action',
				success:function(data){
					window.location.reload();
				}
				});
			}else if(type=='no'){
				this.hide();
			}
			
	    }) 
		
    }
	function checkAll(){
    	if($("#selectAll").is(':checked')){
    		$(":checkbox").prop("checked",true);
    	}else{
    		$(":checkbox").prop("checked",false);
    	}
    }
	function ok(){
		$("#refuseReason").val($("#refundRefuseReason").val());
		var refundStateSel = $("#refundStateSel").val();
		$("#hideRefundState").val(refundStateSel);
		$.ajax({
			type:'post',
			async:false,
			data:$('#refundForm').serialize(),
			url:'cms/refund/refundAllState.action',
			success:function(data){
				window.location.reload();
			}
			});
	}
	
	function passRefund(obj){
		$.ajax({
			type:'post',
			async:false,
			data:'refundId='+obj+'&refundState=1',
			url:'cms/refund/refundState.action',
			success:function(data){
				if(data=="success"){
					$.alert("退款成功",false,function(){
						window.location.reload();
                    },1000,{className:'ui-alert',width:300}); 
				}else if(data=="fail"){
					$.alert("退款失败",false,function(){
						 window.location.reload();
	                },1000,{className:'ui-alert',width:300}); 
				}
			}
			});
	}
	
	function postId(obj){
		$.confirm($('#myModal').html(),[{yes:"提交"},{no:'取消'}],function(type,e){
			if(type=='yes'){
				var textareaHtml=e.find('textarea').val();
				if(!textareaHtml){
					$.alert("请输入拒绝理由");
					return
				}
				
				$.ajax({
					type:'post',
					async:false,
					data:'refundId='+obj+'&refundState=2'+'&refundRefuseReason='+textareaHtml,
					url:'cms/refund/refundState.action',
					success:function(data){
						window.location.reload();
					}
					});
					
				
		    }else{
		    	this.hide();
		    }
		    
		},{width:500});
	}
	
	function auditing(obj){
		if(obj==2){
			$("#hideReason").show();
		}else if(obj==1){
			$("#hideReason").hide();
		}
	}
	
	function exportExcel(){
		$("#exportFlag").val(1);
		$("#searchForm").submit();
	}
	
	function search(){
		$("#exportFlag").val(0);
		$("#searchForm").submit();
	}
	 /* ${rechargeRecord.rechargeMoney}
	${rechargeRecord.rechargePayType}
	${rechargeRecord.rechargeOrderId}
	${rechargeRecord.rechargeOutTradeNo}  */
	
	function getType(obj){
		if(obj==1){
			return "微信支付";
		}
		if(obj==2){
			return "支付宝支付";
		}
	}
	function rechargeRecord(obj){
		
		$.ajax({
			type:'post',
			data:'refundCode='+obj,
			url:'cms/refund/rechargeRecord.action',
			success:function(data){
				if(data!=null&&data!=""){
					$.confirm('<div class="form-horizontal" style="font-size:14px; text-align:left;">'+
							'<div class="form-group">'+
						    '<label for="inputEmail3" class="col-sm-5 control-label">充值金额：</label>'+
						    '<div class="col-sm-7">'+
						      '<p class="form-control-static">'+data.rechargeMoney+'</p>'+
						    '</div>'+
						    '</div>'+
						'<div class="form-group">'+
					    '<label for="inputEmail3" class="col-sm-5 control-label">充值类型：</label>'+
					    '<div class="col-sm-7">'+
					      '<p class="form-control-static">'+getType(data.rechargePayType)+'</p>'+
					    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
					    '<label for="inputEmail3" class="col-sm-5 control-label">充值订单流水号：</label>'+
					    
					    '<div class="col-sm-7">'+
					      '<p class="form-control-static">'+data.rechargeOrderId+'</p>'+
					    '</div>'+
					    '</div>'+
					    '<div class="form-group">'+
					    '<label for="inputEmail3" class="col-sm-5 control-label">商户订单号：</label>'+
					    '<div class="col-sm-7">'+
					      '<p class="form-control-static">'+data.rechargeOutTradeNo+'</p>'+
					    '</div>'+
					    '</div>'+
					    
					    
						
						'</div>',[{yes:"确定"}],function(type,e){
						this.hide()
				    },{width:500})
				}else{
					$.confirm('<div class="form-horizontal">'+
						'<div class="form-group">'+
						    '<label for="inputEmail3" class="col-sm-2 control-label">无充值记录</label>'+
						    
						'</div>'+
						'</div>',[{yes:"确定"}],function(type,e){
						this.hide()
				    })
					
				 
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
				<h1 class="page-header">退款列表</h1>
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
                            	<form action="cms/refund/refundList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <input type="hidden" id="exportFlag" name="flag">
                                <div class="col-md-2 time-box">
                                    <span class="tit">退款发起时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name=refundStarttime value='<fmt:formatDate value="${refundStarttime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="refundEndtime" value='<fmt:formatDate value="${refundEndtime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>
								<div class="col-md-2">
                                    <span class="">退款账号</span>
                                    <input placeholder="退款账号" type="text" name="userPhone" value="${userPhone }" class="form-control">
                                </div>   
                                <div class="col-md-2">
                                    <span class="">退款单号</span>
                                    <input placeholder="退款单号" type="text" name="refundOrderId" value="${refundOrderId }" class="form-control">
                                </div>     
                                <%-- <div class="col-md-2 time-box">
                                    <span class="tit">处理时间</span>
                                   	<input placeholder="开始时间" id="cStarttimeB" type="text" name=operateStarttime value='<fmt:formatDate value="${operateStarttime }" pattern='yyyy-MM-dd'/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtimeB" type="text" name="operateEndtime" value='<fmt:formatDate value="${operateEndtime }" pattern='yyyy-MM-dd'/>' class="form-control">
                                </div>     --%>                       
                                <div class="col-md-2">
                                    <span class="">退款状态</span>
                                    <select name="refundState"  class="form-control">
								    <option value="-2"<c:if test="${refundState==-2 }">selected="selected"</c:if>>全部状态</option>
									<%-- <option value="0"<c:if test="${refundState==0 }">selected="selected"</c:if>>未处理</option> --%>
									<option value="1"<c:if test="${refundState==1 }">selected="selected"</c:if>>退款中</option>
									<option value="2"<c:if test="${refundState==2 }">selected="selected"</c:if>>退款成功</option>
									<option value="3"<c:if test="${refundState==3 }">selected="selected"</c:if>>退款关闭</option>
									<option value="4"<c:if test="${refundState==4 }">selected="selected"</c:if>>退款未确定</option>
									<%-- <option value="5"<c:if test="${refundState==5 }">selected="selected"</c:if>>退款处理中</option> --%>
									<option value="6"<c:if test="${refundState==6 }">selected="selected"</c:if>>退款异常</option>
								</select>
                                </div>
                                <%-- <div class="col-md-2">
                                    <span class="">退款处理人</span>
                                    <input placeholder="退款处理人" type="text" name="adminName" value="${adminName }" class="form-control">
                                </div> --%>
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                            <shiro:hasPermission name="refundExportButton">
                            <a style="margin-top: 10px" class="btn btn-success" onclick="exportExcel()"  href="javascript:void(0)">导出</a>
                            </shiro:hasPermission>
                       </div>
                      
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="refundForm" method="post">
							<input type="hidden" id="refuseReason" name="refundRefuseReason">
							<input type="hidden" id="hideRefundState" name="refundState">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
										    <th></th> 
											<th>退款帐号</th>
											<th>退款发起时间</th>
											<th>退款金额</th>
											<th>退款单号</th>
											<th>退款状态</th>
											<!-- <th>处理时间</th>
											<th>退款处理人</th> -->
											<th>退款详情</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="refund" items="${refundList }">
											<tr class="odd gradeA">
											    <td>
											    <c:if test="${refund.refundState==0 }">
											     <input type="checkbox" name="refundIds" value="${refund.refundId }">
											    </c:if>
											    </td>
												<td>${refund.account.aUser.userTel }</td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${refund.refundCreatetime }"/></td>
												<td class="center"><fmt:formatNumber pattern="0.00" value="${refund.refundMoney }"/></td>
												<td class="center">${refund.refundOrderId }</td>
												<td>
												
												<c:if test="${refund.refundState==1 }"><span style="color:orange;">退款中</span></c:if>
												<c:if test="${refund.refundState==2 }"><span style="color:green;">退款成功</span></c:if>
												<c:if test="${refund.refundState==3 }"><span style="color:red;">退款关闭</span></c:if>
												<c:if test="${refund.refundState==4 }"><span style="color:red;">退款未确定</span></c:if>
												<%-- <c:if test="${refund.refundState==5 }">退款处理中</c:if> --%>
												<c:if test="${refund.refundState==6 }"><span style="color:red;">退款异常</span></c:if>
												</td>
												<%-- <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${refund.refundOperatetime }"/></td>
												<td>${refund.admin.adminRealname }</td> --%>
												<td>
												<a class="btn btn-info" href="cms/refund/refundDetail.action?refundId=${refund.refundId }">详情</a> 
												</td>
												<td class="center">
												<%-- <c:if test="${refund.refundState==0 }">
												<shiro:hasPermission name="refundARButton">
												<a class="btn btn-warning" onclick="passRefund('${refund.refundId }')" href="javascript:void(0)">通过</a>
												</shiro:hasPermission>
												<shiro:hasPermission name="refundARButton">
												<button onclick="postId('${refund.refundId }')" type="button" class="btn btn-warning" >拒绝</button>
												</shiro:hasPermission>
												</c:if> --%>
												<shiro:hasPermission name="refuDeleteButton">
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${refund.refundId }')">删除</a>
												</shiro:hasPermission>
												<a class="btn btn-danger" href="javascript:void(0)" onclick="rechargeRecord('${refund.refundCode }')">充值记录</a>
												</td>
											</tr>
									</c:forEach>
									</tbody>
								</table>
								</form>
								<%-- <input  type="checkbox" id="selectAll" onclick="checkAll()" />全选
								<shiro:hasPermission name="refundARButton">
								 <button data-toggle="modal" data-target="#groupsDel" data-to="#groupsDel"  type="button" class="btn btn-primary" >
								 </span> 批量审核</button>
								 </shiro:hasPermission>
								 <shiro:hasPermission name="refuDeleteButton">
								<button class="btn btn-danger" onclick="deleteAll();">批量删除</button>
								</shiro:hasPermission> --%>
							</div>
							
							<!---拒绝理由的弹框-->
						<div aria-hidden="true"  style="display: none;" id="groupsDel" class="modal fade">
						  <div class="modal-dialog" style="width:600px; height:600px;margin:50px auto;">
						    <div class="modal-content">
						      <div class="modal-header" style="padding:0;">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"  style="height:50px;line-height:50px;padding-left:30px;border-bottom:1px solid #f5f5f5;">退款状态</h4>
						      </div>
						      <div class="modal-dialog" style="width:300px; height:130px;margin-bottom:0;padding-left:30px;">
						             <select id="refundStateSel" class="form-control" onchange="auditing(this.value)">
									 <option value="1">通过</option>
									 <option value="2">拒绝</option>
									</select>
									<div id="hideReason" style="display: none;">
									<label>拒绝理由:</label>
									 <input type="text" id="refundRefuseReason" style="width:300px;">
						            </div>
						      </div>
						      <div class="modal-footer">
						        <a class="btn btn-primary btn-xs" onclick="ok()" href="javascript:void(0)" data-dismiss="modal">确定</a>
						        <button type="button" class="btn btn-default btn-xs" data-dismiss="modal">取消</button>
						      </div>
						    </div>
						  </div>
						</div>
						
							<!---拒绝理由的弹框-->
					<div id="myModal" style="display:none;">
						<form class="form-horizontal" style="padding:20px 20px 0 20px;" role="form" action="" method="post" id="refundReasonForm">
					      <div class="form-group">
					        <label for="inputEmail3" class="col-sm-2 control-label" style="line-height:20px">拒绝理由</label>
					        <div class="col-sm-10">
					           <textarea class="form-control" name="refundRefuseReason" style="margin-top:10px;margin-bottom:10px;height:70px;resize:none;" placeholder="请输入拒绝理由...... "></textarea>
					        </div>
					      </div>
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

