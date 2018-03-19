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
					url:"${basePath}/cms/sysMsg/update.action?sysMsgId="+obj+"&sysMsgIsonline=-1",
					type:'post',
					success:function(data){
							window.location.href="${basePath}/cms/sysMsg/list.action";
					}
				});

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
						window.location.href="${basePath}/cms/sysMsg/list.action";
					}
				}
			}); 
 }

</script>

<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">消息详情</h1>
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
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
								<div class="form-group">
									消息标题：${sysMsg.sysMsgTitle }
								</div>
                                <br>
                                <div class="form-group">
									消息内容：${sysMsg.sysMsgContent }
                                </div>
                            </div>
					</div>

					<c:if test="${sysMsg.sysMsgIsonline == 1}">
						<a class="btn btn-warning" href="javascript:void(0)"
							onclick="online('0','${sysMsg.sysMsgId }')";>下线</a>
					</c:if>
					<c:if test="${sysMsg.sysMsgIsonline == 0}">
						<a class="btn btn-warning" href="javascript:void(0)"
							onclick="online('1','${sysMsg.sysMsgId }')";>上线</a>
					</c:if>

					<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${sysMsg.sysMsgId }');">删除</a>
					
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

<%@include file="../common/buttom.jsp"%>
</html>
