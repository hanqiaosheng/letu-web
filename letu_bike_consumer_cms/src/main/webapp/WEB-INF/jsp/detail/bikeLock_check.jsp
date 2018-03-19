<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
   var  wsServer = 'ws://'+'lock.letulife.com:40080/'+'webSocketServer.socket'; 
   if('WebSocket' in window){
   	var  websocket = new WebSocket(wsServer); 
    websocket.onopen = function (evt) { onOpen(evt) }; 
    websocket.onclose = function (evt) { onClose(evt) }; 
    websocket.onmessage = function (evt) { onMessage(evt) }; 
    websocket.onerror = function (evt) { onError(evt) }; 
    function onOpen(evt) { 
       console.log("Connected to WebSocket server."); 
       websocket.send("cms,${bikeLockInfo.bikeLockCode }");
    } 
    function onClose(evt) { 
       console.log("Disconnected"); 
    } 
    function onMessage(evt) { 
 	   console.log(evt.data);
//     	if(evt.data=='go'){
    		$.confirm(evt.data,[{yes:"确定"},{no:"取消"}],function(type,e){
				window.location.reload();
			});
//     	}
    } 
    function onError(evt) { 
       console.log('Error occured: ' + evt.data); 
    }
    function rentBike(){
    	$.isLoading.show();
    
    }
   }
  
   </script> 
<script type="text/javascript">

function lockOrUnlock(obj,id){
	if(obj=='lock'){
		$.ajax({
			url:"${basePath}cms/lock/lock.action?bikeLockId="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('关锁指令发送成功，请稍后刷新页面确认已上锁',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					})
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}
	else if(obj=='unlock'){
		$.ajax({
			url:"${basePath}cms/lock/unlock.action?bikeLockId="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('开锁指令发送成功，请稍后刷新页面确认已开锁',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					}); 
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}else if(obj=='state'){
		$.ajax({
			url:"${basePath}cms/lock/state.action?bikeLockId="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('获取状态信息指令发送成功，请稍后刷新页面确认',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					}); 
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}else if(obj=='pwd'){
		$.ajax({
			url:"${basePath}cms/lock/getDomainPwd.action?bikeLockId="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('获取域名配置密码指令发送成功，请稍后刷新页面确认',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					}); 
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}else if(obj=='ready'){
		$.ajax({
			url:"${basePath}cms/lock/shakeHands.action?bikeLockId="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('开启域名配置功能指令发送成功，请稍后刷新页面确认',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					}); 
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}else if(obj=='version'){
		$.ajax({
			url:"${basePath}cms/lock/getversion.action?bikeLockCode="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('获取版本信息指令发送成功，请稍后刷新页面确认',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					}); 
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}
}
</script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">车锁调试</h1>
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
								锁编号：${bikeLockInfo.bikeLockCode }
							</div>
							<div class="form-group">
								车辆编号：${bikeLockInfo.bikeCode }
							</div>
							<div class="form-group">
								车锁类型：<c:if test="${bikeLockInfo.bikeLockType==0 || bikeLockInfo.bikeLockType==null}">全自动锁</c:if>
								<c:if test="${bikeLockInfo.bikeLockType==1 }">半自动锁</c:if>
							</div>
							<div class="form-group">
								电压：${bikeLockInfo.bikeLockVoltage}V
							</div>
							<div class="form-group">
								锁状态：
								<c:if test="${bikeLockInfo.bikeLockStatus==0 }">已锁</c:if>
								<c:if test="${bikeLockInfo.bikeLockStatus==1 }">未锁</c:if>
								<c:if test="${bikeLockInfo.bikeLockStatus==2 }">车锁受阻</c:if>
							</div>
							<div class="form-group">
								在线状态：
								<c:if test="${bikeLockInfo.bikeLockState==0 }"><div class="state state-gray">未在线</div></c:if>
								<c:if test="${bikeLockInfo.bikeLockState==1 }"><div class="state state-green">在线</div></c:if>
							</div>
							<div class="form-group">
								域名配置密码状态：
								<c:if test="${bikeLockInfo.bikeLockDomainPwdState==0 }">未确认配置</c:if>
								<c:if test="${bikeLockInfo.bikeLockDomainPwdState==1 }">已确认配置</c:if>
								<c:if test="${bikeLockInfo.bikeLockDomainPwdState==2 }">旧密码错误</c:if>
								<c:if test="${bikeLockInfo.bikeLockDomainPwdState==3 }">配置失败</c:if>
							</div>
							<div class="form-group">
								域名配置状态：
								<c:if test="${bikeLockInfo.bikeLockDominState==0 }">可配置</c:if>
								<c:if test="${bikeLockInfo.bikeLockDominState==1 }">未确认配置</c:if>
								<c:if test="${bikeLockInfo.bikeLockDominState==2 }">已确认配置</c:if>
								<c:if test="${bikeLockInfo.bikeLockDominState==3 }">配置失败</c:if>
								<c:if test="${bikeLockInfo.bikeLockDominState==4 }">开启域名配置功能失败，密码错误</c:if>
							</div>
                            <form  action="cms/lock/setDomainPwd.action" method="post" >
								<input type="hidden" value="${bikeLockInfo.bikeLockId }" name="bikeLockId">
								<div class="form-group">
									域名配置密码：<input class="form-control" name="npwd" placeholder="域名配置密码" value="${bikeLockInfo.bikeLockDomainPwd }"  type="text" required="required">
								</div>
                           		<div style="text-align: center;">
									<input type="submit" value="修改并发送指令" class="btn btn-primary">
								</div>
                            </form>
                            <form  action="cms/lock/setDomain.action" method="post" >
								<input type="hidden" value="${bikeLockInfo.bikeLockId }" name="bikeLockId">
								<div class="form-group">
									域名或IP：<input class="form-control" name="bikeLockDomain" placeholder="域名或IP" value="${bikeLockInfo.bikeLockDomain }"  type="text" required="required">
								</div>
								<div class="form-group">
									端口：<input class="form-control" name="bikeLockPort" placeholder="端口" value="${bikeLockInfo.bikeLockPort }"  type="text" required="required">
								</div>
<%-- 								<c:if test="${bikeLockInfo.bikeLockDominState==0 }"> --%>
	                           		<div style="text-align: center;">
										<input type="submit" value="修改并发送指令" class="btn btn-primary">
									</div>
<%-- 								</c:if> --%>
                            </form>
                            <br>
<%--                			<c:if test="${bikeLockInfo.bikeLockStatus==0 }"> --%>
							<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('unlock','${bikeLockInfo.bikeLockId}');">开锁</a>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${bikeLockInfo.bikeLockStatus==1 }"> --%>
							<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('lock','${bikeLockInfo.bikeLockId}');">上锁</a>
<%-- 						</c:if> --%>
						<a class="btn btn-primary" href="cms/lock/location.action?bikeLockId=${bikeLockInfo.bikeLockId }">定位</a>
<%-- 						<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('lock','${bikeLockInfo.bikeLockId}');">关机</a> --%>
						<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('state','${bikeLockInfo.bikeLockId}');">获取状态信息</a>
						<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('pwd','${bikeLockInfo.bikeLockId}');">获取域名配置密码</a>
						<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('ready','${bikeLockInfo.bikeLockId}');">开启配置域名功能</a>
						<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('version','${bikeLockInfo.bikeLockCode}');">获取车锁版本</a>
<%-- 						<a class="btn btn-primary" href="cms/lock/setDomainPwd.action?bikeLockId=${bikeLockInfo.bikeLockId}" >修改域名配置密码</a> --%>
<%-- 						<a class="btn btn-primary" href="cms/lock/setDomain.action?bikeLockId=${bikeLockInfo.bikeLockId}" >修改域名</a> --%>
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

	<!-- /.row -->

<%@include file="../common/buttom.jsp"%>
</html>
