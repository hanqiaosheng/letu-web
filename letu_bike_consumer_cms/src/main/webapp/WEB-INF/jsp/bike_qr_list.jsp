<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<link rel="stylesheet" href="map/maskMap.css">
<script type="text/javascript" src="js/search.js"></script>
<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<%@include file="common/datePicker.jsp"%>
 
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">二维码导出</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					    <div class="panel-heading clearfix ">
                            <div class="row">
                                <div class="caption font-red-intense col-md-12">
                                    <button class="btn btn-default hide-search pull-right" type="button">隐藏</button>
                                </div>
                            </div>
                            <div class="row tables-btn-box">
                            	<form action="cms/bike/bikeQrList.action" method="post" id="searchForm">
                                <input type="hidden" id="pageIndex" name="pageIndex">
                                <div class="col-md-2">
                                    <span class="">批次号</span>
                                   <input placeholder="批次号" type="text" id="mbikeBatchNumber" name="bikeBatchNumber" value="${bike.bikeBatchNumber }" class="form-control">
                                </div>
                                
                                <div class="col-md-2">
                                    <span class="">车辆编号</span>
                                   <input placeholder="车辆编号" type="text" id="mbikeCode" name="bikeCode" value="${bike.bikeCode }" class="form-control">
                                </div>
                                
                                 <div class="col-md-2">
                                    <span class="">车锁编号</span>
                                   <input placeholder="车锁编号" type="text" id="mbikeLockCode" name="bikeLockCode" value="${bikeLockCode }" class="form-control">
                                </div>
                                
                                <div class="col-md-2">
                                <span class="">车型</span>
                                   <select name="bikeModelsId" id="bikeModelsId"  class="form-control">
								    <option value="-1">全部型号</option>
								    <c:forEach items="${modelList }" var="models">
									<option value="${models.modelsId }" <c:if test="${models.modelsId==bike.bikeModelsId }">selected="selected"</c:if>>${models.modelsName }</option>
								    </c:forEach>
								</select>
                                </div>
                                
                                <div class="col-md-2">
                                <span class="">渠道</span>
                                   <select name="channelid"  id="mchannelid" class="form-control">
								    <option value="-1">全部渠道</option>
									<c:forEach items="${channels }" var="channel">
										<option value="${channel.channelId }"  <c:if test="${channel.channelId==channelid }">selected="selected"</c:if>>${channel.channelName }</option>
									</c:forEach>
								</select>
                                </div>  
                                 	</form>                         
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-success" type="button"  onclick="createQrimg()">导出二维码</button>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="bikeForm" method="post">
							<input type="hidden" id="bikeState" name="bikeState">
								<table id="table" class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>批次号</th>
										    <th>车辆编号</th>
											<th>车型</th>
											<th>渠道名称</th>
											<th>设备锁号</th>
											<th>预付款</th>
											<th>状态</th>
											<th>定位</th>
											<th>车辆详情</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="bike" items="${bikeList }">
											<tr class="odd gradeA">
												<td>${bike.bikeBatchNumber }</td>
											    <td>${bike.bikeCode }</td>
												<td>${bike.models.modelsName }</td>
												<td>${bike.models.channel.channelName }</td>
												<td>${bike.bikeLock.bikeLockCode }</td>
												<td><fmt:formatNumber pattern="0.00" value="${bike.models.modelsDeposit }"/></td>
												<td class="center">
												<c:if test="${bike.bikeState==0 }">空闲中</c:if>
												<c:if test="${bike.bikeState==1 }">临时还车</c:if>
												<c:if test="${bike.bikeState==2 }">租借中</c:if>
												<c:if test="${bike.bikeState==3 }">维护中</c:if>
												<c:if test="${bike.bikeState==4 }">锁定中</c:if>
												<c:if test="${bike.bikeState==5 }">冻结中</c:if>
												</td>
												<td><a class="btn btn-info" href="cms/bike/bikePosition.action?bikeId=${bike.bikeId }&positionx=${bike.bikeAtitude}&positiony=${bike.bikeLongitude}">定位</a></td>
												<td><a class="btn btn-info" href="cms/bike/bikeDetail.action?bikeId=${bike.bikeId }">详情</a></td>
											</tr>
									</c:forEach>
									</tbody>
								</table>
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

	<div id="mask" class="mask">
		<div class="map-box">
			<div class="map" id="container">
				<div id="myPageTop">
					<table>
						<tr>
							<td><label>请输入关键字：</label></td>
						</tr>
						<tr>
							<td><input id="tipinput" /></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="btn-box">
				<button class="submit" type="">确定</button>
				<button class="rentState" type="">取消</button>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			AMap.service('AMap.Geocoder', function() {//回调函数  加载插件   根据经纬获取地址
				//实例化Geocoder
				geocoder = new AMap.Geocoder({});
				//TODO: 使用geocoder 对象完成相关功能
			})

            $("#mchannelid").select2();
			//

			/* 		  var lnglatXY = ['${bike.bikeLongitude}','${bike.bikeAtitude}'];
			 lnglatXYTo(lnglatXY); */

		});
		function lnglatXYTo(obj) {
			var lnglatXY = obj;
			geocoder.getAddress(lnglatXY, function(status, result) {
				if (status === 'complete' && result.info === 'OK') {
					//获得了有效的地址信息:
					address = result.regeocode.formattedAddress;//.split("市")[1];
					address = address.substring(3, address.length);
				} else {
					//获取地址失败
				}
			});
		}

		function importBike() {
			var bikeModelsName = $("#bikeModelsName").val();
			if ($("#bikeChannelId").val() == '') {
				$.alert("请选择渠道", true, function() {
				}, false, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}
			if ($("#bikeModelsName").val() == '') {
				$.alert("请输入车型", true, function() {
				}, false, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}
			if ($("#bikePutAddress").val() == '') {
				$.alert("请输入投放地址", true, function() {
				}, false, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}
			if ($("#cStarttimeB").val() == '') {
				$.alert("请输入投放时间", true, function() {
				}, false, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}
			$.ajax({
				url : 'cms/bike/checkModelName.action',
				type : 'post',
				data : 'bikeModelsName=' + bikeModelsName,
				success : function(data) {
					if (data = "success") {
						$("#importForm").submit();
					} else if (data = "fail") {
						$.alert("请输入正确的车型", true, function() {
						}, false, {
							className : 'ui-alert',
							width : 300
						});
					}
				}
			})

		}
	</script>
	<%@include file="common/buttom.jsp"%>
	<script type="text/javascript">
	//批量生成二维码
	function createQrimg(){
		$.isLoading.show('二维码生成中...',true)
		var bikeBatchNumber = $("#mbikeBatchNumber").val();
		var bikeCode = $("#mbikeCode").val();
		var bikeLockCode = $("#mbikeLockCode").val();
		var bikeModelsId = '${bike.bikeModelsId}';
		var channelid = '${channelid}';
		$.ajax({
			timeout : 2000000,
			url : 'cms/bike/importQr.action',
			type : 'post',
			data:{'bikeBatchNumber':bikeBatchNumber,'bikeCode':bikeCode,'bikeLockCode':bikeLockCode,'bikeModelsId':bikeModelsId,'channelid':channelid},
			//data : 'bikeModelsName=' + bikeModelsName,
			success : function(data) {
				if (data.message== "success") {
					$.isLoading.hide();
					/* $.alert("生成完了",true,function(){
		            },false,{className:'ui-alert',width:300}); */
					window.location.href="/qrcode.zip";
					
				} 
			}
		})
	
	}
	
	</script>
	<script type="text/javascript"> 
	var lnglat;
	function showMask(){
	    $("#mask").css("height",$(document).height());
	    $("#mask").css("width",$(document).width());
	    $("#mask").show(500);
	}
	//隐藏遮罩层
	function hideMask(){
	    $("#mask").hide(500);
	}
	var _this;
	$(".go-back").on("focus",function(){
		_this=$(this);
	    showMask();
	})
	$(".mask .rentState").click(function(){
	    $("#mask").hide(500);
	})
	$(".mask .submit").click(function(){
		_this.val(lnglat);
		$("#bikeAddressText").val(address);
	    $("#mask").hide(500);
	})
	var marker;
  	var map = new AMap.Map('container', {
      resizeEnable: true,
      zoom:13,
      center: [120.156077,30.287459],
      
  	});
	map.on('click', function(e) {
		if(marker){
			marker.hide();
		}
	    marker = new AMap.Marker({
	        icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
	        position: [e.lnglat.getLng(),e.lnglat.getLat()]
	    });
	    marker.setMap(map);
	    setMarker=marker;
	    lnglat=e.lnglat.getLng()+','+e.lnglat.getLat();
	    lnglatXYTo(lnglat);
	});
	
	//输入提示
    var autoOptions = {
        input: "tipinput"
    };
    var auto = new AMap.Autocomplete(autoOptions);
    var placeSearch = new AMap.PlaceSearch({
        map: map
    });  //构造地点查询类
    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
    	map.setCenter(e.poi.location);
    	if(marker){
			marker.hide();
		}
	    marker = new AMap.Marker({
	        icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
	        position: e.poi.location
	    });
	    marker.setMap(map);
	    setMarker=marker;
	    lnglat=e.poi.location;
	    lnglatXYTo(lnglat);
    }
	
</script>
</body>
</html>

