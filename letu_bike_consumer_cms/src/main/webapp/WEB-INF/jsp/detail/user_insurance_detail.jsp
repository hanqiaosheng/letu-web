<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
function review(flag){
	if(flag==1){
		$('#review').attr("action","cms/user/insurance/update.action?insuranceState=1&insuranceId="+${insurance.insuranceId});
		$('#lpje').hide();
	}
	else if(flag==0){
		$('#review').attr("action","cms/user/insurance/update.action?insuranceState=2&insuranceId="+${insurance.insuranceId});
		$('#lpje').show();
	}
	$.confirm($('#myModal').html(),[{yes:"提交"},{no:'取消'}],function(type,e){
		if(type=='yes'){
			var textareaHtml=e.find('textarea').val();
			var inputHtml=e.find('input').val();
			if(!inputHtml&&flag==0){
				$.alert("请输入金额");
				return
			}
			if(!textareaHtml){
				$.alert("请输入驳回理由");
				return
			}
			
			e.find('form').submit()
				
			
	    }else{
	    	this.hide();
	    }
	    
	},{width:500});
}
	

function checkDel(obj){
	var mess = "确认删除吗？";
	$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
		if(type=='yes'){
				$.ajax({
					url:"cms/user/insurance/update.action?insuranceId="+obj+"&insuranceState=3",
					type:'post',
					success:function(data){
						if(data!=""){
							window.location.href="${basePath}/cms/user/insurance/list.action";
						}
					}
				});
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
				window.location.href="${basePath}/cms/user/insurance/list.action";
			}
		}
	});
}

</script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">保险申请详情</h1>
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
				<div class="panel-body form-horizontal" >
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">账号：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${user.userTel }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">姓名：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${user.userRealname }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">身份证号码：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${user.userIdcard }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">申请时间：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static"><fmt:formatDate value='${insurance.insuranceApplyTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">内容：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${insurance.insuranceContent }</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">审核结果：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">
					       <c:if test="${insurance.insuranceState==0 }">
									未处理
									</c:if>
									<c:if test="${insurance.insuranceState==1 }">
									已拒绝
									</c:if>
									<c:if test="${insurance.insuranceState==2}">
									已通过
							</c:if>
					      </p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">事故全景照片：</label>
					    <div class="col-sm-10">
					      <c:forEach items="${insurance.accidentImgs }" var="img">
								<img alt="图片无法显示" src="upload/${img }">    
							</c:forEach>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">事故损伤局部照片：</label>
					    <div class="col-sm-10">
					      <c:forEach items="${insurance.detailImgs }" var="img">
							<img alt="图片无法显示" src="upload/${img }">      
						  </c:forEach>
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">赔偿协议书照片：</label>
					    <div class="col-sm-10">
					     <img alt="图片无法显示" src="upload/${insurance.insuranceCompensate }">      
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">身份证照片（正反面）：</label>
					    <div class="col-sm-10">
					     <c:forEach items="${insurance.identityContact }" var="img">
							<img alt="图片无法显示" src="upload/${img }">      
						 </c:forEach>
					    </div>
					</div>
					
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">赔偿处理结果证明文件：</label>
					    <div class="col-sm-10">
					     <img  alt="图片无法显示" src="upload/${insurance.insuranceResultProve }">   
					    </div>
					</div>
					<c:if test="${insurance.insuranceState != 0}">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">审核时间：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static"><fmt:formatDate value='${insurance.insuranceDealTime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
					    </div>
					</div>
					<c:if test="${insurance.insuranceState==1 }">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">拒绝理由：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${insurance.insuranceResult}</p>
					    </div>
					</div>
					</c:if>
					<c:if test="${insurance.insuranceState==2}">
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">理赔金额：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${insurance.insuranceMoney}</p>
					    </div>
					</div>
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">处理建议：</label>
					    <div class="col-sm-10">
					      <p class="form-control-static">${insurance.insuranceResult}</p>
					    </div>
					</div>
					</c:if>
					</c:if>
					
					
        						
        						
        					
					<c:if test="${insurance.insuranceState == 0}">
					<div class="form-group text-center">
						<a class="btn btn-success" href="javascript:void(0)"
							onclick="review(0);">通过</a>
						<a class="btn btn-danger" href="javascript:void(0)"
							onclick="review(1);">驳回</a>
					</div>
					</c:if>
<!-- 					<a class="btn btn-danger" href="javascript:void(0)" -->
<%-- 						onclick="checkDel('${insurance.insuranceId}')">删除</a> --%>
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

	<!-- /.row -->
<div id="myModal" style="display:none;">
	<form class="form-horizontal" style="padding:20px 20px 0 20px;" role="form" action="" method="post" id="review">
	<input type="hidden" value="${insurance.insuranceUserId }" name="insuranceUserId">
	  <div class="form-group" id="lpje"> 
        <label for="inputEmail3" class="col-sm-2 control-label"  style="line-height:22px">理赔金额</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="inputEmail3" placeholder="理赔金额" name="insuranceMoney">
        </div>
      </div>
      <div class="form-group">
        <label for="inputEmail3" class="col-sm-2 control-label" style="line-height:20px">处理意见</label>
        <div class="col-sm-10">
           <textarea class="form-control" name="insuranceResult" style="margin-top:10px;margin-bottom:10px;height:70px;resize:none;" placeholder="请输入处理意见...... "></textarea>
        </div>
      </div>
  </form>

</div>

<%@include file="../common/buttom.jsp"%>
</html>
