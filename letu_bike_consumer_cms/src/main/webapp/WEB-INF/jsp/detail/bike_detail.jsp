<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			_self=this;
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'bikeId='+obj,
					url:'cms/bike/deleteBike.action',
					success:function(data){
						if(data!="fail"){
							window.location.href="${basePath}/cms/bike/bikeList.action";
						}else{
							_self.hide();
							$.alert("改车辆绑定锁，无法删除");
						}
					}
				});
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
			<h1 class="page-header">车辆信息详情</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
				
					<div class="row">
					
						<div class="panel-body form-horizontal">
						<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">车辆编号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.bikeCode  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">车辆型号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.models.modelsCode }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">设备锁号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.bikeLock.bikeLockCode }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">锁sim编号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.bikeLock.bikeLockSimCode }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">渠道名称：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.models.channel.channelName }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">批次号：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.bikeBatchNumber }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">车辆状态：</label>
								<div class="col-sm-10">
									<p class="form-control-static">
									    <c:if test="${bike.bikeState==0 }">空闲中</c:if>
										<c:if test="${bike.bikeState==1 }">临时还车</c:if>
										<c:if test="${bike.bikeState==2 }">租借中</c:if>
										<c:if test="${bike.bikeState==3 }">维护中</c:if>
										<c:if test="${bike.bikeState==4 }">锁定中</c:if>
										<c:if test="${bike.bikeState==5 }">冻结中</c:if>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">车锁状态：</label>
								<div class="col-sm-10">
									<p class="form-control-static">
										<c:if test="${lockStatus==0 }">已锁</c:if>
										<c:if test="${lockStatus==1 }">未锁</c:if>
										<c:if test="${lockStatus==2 }">受阻</c:if>
										<c:if test="${empty lockStatus }">未关联锁</c:if>
									</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">负责人：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.bikeManagerName }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">联系电话：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.bikeManagerTel }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">投放时间：</label>
								<div class="col-sm-10">
									<p class="form-control-static"><fmt:formatDate value="${bike.bikePutTime }" pattern="yyyy-MM-dd"/></p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">投放地点：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${bike.bikeAddress }</p>
								</div>
							</div>
							<div class="form-group" style="margin-left: 160px">
	
								<a class="btn btn-info" href="cms/bike/bikePosition.action?bikeId=${bike.bikeId }&positionx=${bike.bikeAtitude}&positiony=${bike.bikeLongitude}">定位</a></td>	

								<a class="btn btn-info" href="cms/bikeRentInfo/bikeRentInfoList.action?bikeId=${bike.bikeId }">租赁详情</a></td>	

								<a class="btn btn-info" href="cms/bikeFixInfo/bikeFixInfoDetailList.action?bikeId=${bike.bikeId }">维护详情</a>
							</div>

								<div class="form-group" style="margin-left: 160px">	
									
										<a class="btn btn-primary" href="cms/bike/updateBikeJsp.action?bikeId=${bike.bikeId }&bikeState=${bike.bikeState }">设置状态</a> 
										<a class="btn btn-primary" href="cms/bike/editJsp.action?bikeId=${bike.bikeId }&managerId=${bike.bikeRepair.managerId}&channelId=${bike.models.modelsChannelId}">修改</a>
										<a class="btn btn-danger" onclick="checkDel('${bike.bikeId }')" href="javascript:void(0);">删除</a>
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
