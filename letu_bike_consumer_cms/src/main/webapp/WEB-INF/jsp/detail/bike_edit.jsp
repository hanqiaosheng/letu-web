<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<%@include file="../common/datePicker.jsp"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<link rel="stylesheet" href="map/maskMap.css">
<script type="text/javascript">
 /* function changeTel(obj){
	 $.ajax({
		 type:'post',
		 url:'cms/bikeRepair/bikeRepairInfo.action',
		 data:'managerId='+obj.value,
		 success:function(data){
			 $("#managerTel").val(data.managerTel);
		 }
	 });
 }
  */
 
/*  function channel(obj){
	 $.ajax({
		 type:'post',
		 url:'cms/bikeRepair/bikeRepairInfo.action',
		 data:'modelsId='+obj,
		 success:function(data){
			 $("#channel").text(data.channelName);
		 }
	 });
 } */
 function check(){
 $.ajax({
	 type:'post',
	 url:'cms/bike/checkBikeCode.action',
	 data:'bikeCode='+$("#bikecode").val()+'&lockCode='+$("#lockCode").val()+'&bikeId='+$("#bikeId").val()+'&flag=2',
	 success:function(data){
		    if(data=='failLock'){
				$.alert("锁编号不存在",true,function(){
	            },false,{className:'ui-alert',width:300}); 
			}else if(data=='failLockBike'){
				$.alert("该锁已绑定其它车辆",true,function(){
	            },false,{className:'ui-alert',width:300}); 
			}else if(data=='success'){
				$("#bikeForm").submit();
			}
	 }
 });
 }
</script>

<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">车辆编辑</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" id="bikeForm" action="cms/bike/editBike.action" method="post">
							<label>车辆编号：${bike.bikeCode }</label>
								
								<br>
								<label>设备锁编号：</label>
								<div class="form-group">
									<input class="form-control" id="lockCode" value="${lock.bikeLockCode }" type="text" name="lockCode">
								</div>
								<label>锁sim编号：</label>
								<div class="form-group">
									<input class="form-control" id="simCode" value="${lock.bikeLockSimCode }" type="text" name="simCode">
								</div>
								<input type="hidden" id="bikeId" name="bikeId" value="${bike.bikeId }">
								<label>车型：</label>
								<div class="form-group">
								<select name="bikeModelsId" id="bikeModelsId" class="form-control" required="required">
								    <c:forEach items="${modelsList }" var="models">
										<option value="${models.modelsId }"  <c:if test="${bike.bikeModelsId==models.modelsId }">selected="selected"</c:if>>${models.modelsName }</option>
									</c:forEach>
								</select>
								</div>
								
								<label>站点：</label>
								<div class="form-group">
								<select name="bikeFixedReturnId" id="bikeFixedReturnId" class="form-control" required="required">
								    <option value="0">暂不在站点</option>
								    <c:forEach items="${fixedReturnList }" var="fixedReturn">
										<option value="${fixedReturn.fixedReturnId }"  <c:if test="${bike.bikeFixedReturnId==fixedReturn.fixedReturnId }">selected="selected"</c:if>>${fixedReturn.fixedReturnName }</option>
									</c:forEach>
								</select>
								</div>
							
								<label>负责人：</label>
								<div class="form-group">
								<input class="form-control" name="bikeManagerName" placeholder="负责人" value="${bike.bikeManagerName }" id="bikeManagerName" type="text" required="required">
								</div>
								<label>联系电话：</label>
								<div class="form-group">
									<input class="form-control" name="bikeManagerTel" placeholder="联系电话" value="${bike.bikeManagerTel }" id="bikeManagerTel" type="text" required="required">
								</div>
								<div style="text-align: center;">
									<input type="button" value="提交" class="btn btn-primary" onclick="return check()">
								</div>
							</form>
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


<script type="text/javascript">
 $(document).ready(function(){
	 AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
		    //实例化Geocoder
		    geocoder = new AMap.Geocoder({
		    });
		    //TODO: 使用geocoder 对象完成相关功能
		  })
		  
		  //

		  var lnglatXY = ['${bike.bikeLongitude}','${bike.bikeAtitude}'];
	      lnglatXYTo(lnglatXY);
		 
	}); 
   function lnglatXYTo(obj){
	  
	   var lnglatXY = obj;
	   $("#hideAddress").val(lnglatXY);
	   geocoder.getAddress(lnglatXY, function(status, result) {
		      if (status === 'complete' && result.info === 'OK') {
		           //获得了有效的地址信息:
		           address=result.regeocode.formattedAddress;//.split("市")[1];
		           address=address.substring(3,address.length);
		           $("#putAddress").val(address);

		      }else{
		            //获取地址失败
		      }
		    }); 
   } 
</script>
<%@include file="../common/buttom.jsp"%>
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
		_this.val(address);
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
</html>
