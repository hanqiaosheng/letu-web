<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
function checkDel(obj){
	var mess = "确认删除吗？";
	$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
		if(type=='yes'){
				$.ajax({
					url:"cms/user/feedback/update.action?feedbackId="+obj+"&feedbackState=3",
					type:'post',
					success:function(data){
						if(data!=""){
							window.location.href="${basePath}/cms/user/feedback/questionList.action";
						}
					}
				});
		}else if(type=='no'){
			this.hide();
		}
    }) 
}

function update(obj){
	
		$.ajax({
			url:"cms/user/feedback/update.action",
			type:'post',
			data:"feedbackId="+obj+"&feedbackState=4"+"&feedbackRemark="+$(".feedbackRemark").val(),
			success:function(data){
				if(data!=""){
					
					window.location.href="${basePath}/cms/user/feedback/questionList.action";
				}
			}
		});
	
	
}

</script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">反馈处理</h1>
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
					    <label for="inputEmail3" class="col-sm-2 control-label">账号：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${feedback.fUserTel }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">反馈时间：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static"><fmt:formatDate value='${feedback.feedbackTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">反馈类型：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${feedback.fDataDetVal }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">状态：</label>
					    <div class="col-sm-10">
					    <p class="form-control-static">
					      <c:if test="${feedback.feedbackState==0 }">
							未处理
						  </c:if>
						  <c:if test="${feedback.feedbackState==1 }">
							已拒绝
						  </c:if>
						  <c:if test="${feedback.feedbackState==2}">
							已接受
						  </c:if>
						  </p>
					    </div>
					</div>
					<c:if test="${!empty bike.bikeCode }">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">车辆编码：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${bike.bikeCode }</p>
					    </div>
					</div>
					</c:if>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">反馈图片：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">
					       <c:forEach items="${feedback.feedbackPics }" var="img">
							<img alt="图片无法显示" src="upload/${img }">      
						  </c:forEach>
					       <%-- <img alt="无法显示" src="upload/${feedback.feedbackPicaddress }"> --%>
					      </p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">反馈内容：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">
					       ${feedback.feedbackContent }
					      </p>
					    </div>
					</div>
					<c:if test="${!empty feedback.feedbackRefuseReason }">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">反馈拒绝理由：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">
                             ${feedback.feedbackRefuseReason }
					      </p>
					    </div>
					</div>
					</c:if>
					
					<label for="inputEmail3" class="col-sm-2 control-label">反馈备注：</label>
					
					<div class="form-group">
					<div class="col-sm-4">
									<input class="form-control feedbackRemark" name="feedbackRemark" placeholder="反馈备注" value="${feedback.feedbackRemark }" id="feedbackRemark" type="text" required="required">
					</div>
					</div>
                    
					
					<div class="form-group text-center">
					<c:if test="${feedback.feedbackState == 2}">
						
						<div style="text-align: center;">
						<input type="button" value="完成处理" class="btn btn-primary" onclick="update('${feedback.feedbackId}')">
						</div>
					</c:if>
					<%-- <a class="btn btn-danger" href="javascript:void(0)"
						onclick="checkDel('${feedback.feedbackId}')">删除</a> --%>
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
