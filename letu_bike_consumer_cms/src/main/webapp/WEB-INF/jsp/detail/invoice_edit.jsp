<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
function send(obj){
	var mess = "确认处理吗？";
	$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
		if(type=='yes'){
			if(obj!=''){
				$.ajax({
					url:"cms/invoice/update.action?invoiceState=1&invoiceId="+obj,
					type:'post',
					success:function(data){
						if(data!=""){
							window.location.href="${basePath }/cms/invoice/list.action";
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

</script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">发票详情</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body form-horizontal">
				    <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">编号：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceId }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">发票类型：</label>
					    <div class="col-sm-1">
					    <p class="form-control-static">
					      <select class="form-control" name="invoiceType" value="${ invoiceType}" id="invoiceType" disabled="disabled">
                                    <%-- <option value="1" <c:if test="${invoice.invoiceType==1 }">selected="selected"</c:if>>电子发票</option> --%>
                                    <option value="2" <c:if test="${invoice.invoiceType==2 }">selected="selected"</c:if>>纸质发票</option>
                          </select>
						  
						  </p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">发票金额：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static"><fmt:formatNumber value="${invoice.invoiceMoney }" pattern="0.00"></fmt:formatNumber>元</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">申请时间：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static"><fmt:formatDate value='${invoice.invoiceApplicationTime }' pattern='yyyy-MM-dd HH:mm:ss'/></p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">公司抬头：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceName }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">发票内容：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceContent }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">开票状态：</label>
					    <div class="col-sm-1">
					    <p class="form-control-static">
					      <select class="form-control" name="invoiceState" value="${invoiceState }" id="invoiceState" disabled="disabled">
                                     
                                      <option value="0"<c:if test="${invoice.invoiceState==0 }">selected="selected"</c:if>>未处理</option>
                                      <option value="1"<c:if test="${invoice.invoiceState==1 }">selected="selected"</c:if>>待发货</option>
                                      <option value="2"<c:if test="${invoice.invoiceState==2 }">selected="selected"</c:if>>已发货</option>
                                      <%-- <option value="3"<c:if test="${invoice.invoiceState==3 }">selected="selected"</c:if>>待开票</option>
                                      <option value="4"<c:if test="${invoice.invoiceState==4 }">selected="selected"</c:if>>已开票</option> --%>
                                      <option value="5"<c:if test="${invoice.invoiceState==5 }">selected="selected"</c:if>>未激活</option>
                                      
                           </select>
						  
						  </p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">开票行程：</label>
					    <div class="col-sm-10">
					      <a class="btn btn-info" href="cms/invoice/bikeRentInfo.action?invoiceId=${invoice.invoiceId }">点击查看详情</a>
					    </div>
					</div>
					<c:if test="${invoice.invoiceType == 2 }">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">收件人：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceReceiveName }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">联系电话：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceReceiveTel }</p>
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">所在地区：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceReceiveCity }&nbsp;&nbsp;&nbsp;${invoice.invoiceReceiveAddress }</p>
					    </div>
					</div>
					</c:if>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">电子邮件：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceEmail }</p>
					    </div>
					</div>
					<c:if test="${!empty invoice.invoiceMark }">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">备注：纳税人识别号：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceTaxpayerNumber }</p>
					    </div>
					</div>
					<c:if test="${!empty invoice.invoiceAddrePhone }">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">地址电话：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceAddrePhone }</p>
					    </div>
					</div>
					</c:if>
					<c:if test="${!empty invoice.invoiceBankAccount }">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">开户行及账号：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${invoice.invoiceBankAccount }</p>
					    </div>
					</div>
					</c:if>
					</c:if>
					
					<div class="form-group text-center">
					<c:if test="${invoice.invoiceState == 0}">
						<a class="btn btn-primary" href="javascript:void(0)"
							onclick="send('${invoice.invoiceId}')">处理</a>
						
					</c:if>
					
					</div>
					
					
				</div>
						<!-- /.col-lg-6 (nested) -->
			</div>
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
					<!-- /.row (nested) -->
		</div>
				<!-- /.panel-body -->
	</div>
			<!-- /.panel -->
</div>
		<!-- /.col-lg-12 -->

	<!-- /.row -->

<%@include file="../common/buttom.jsp"%>
</html>
