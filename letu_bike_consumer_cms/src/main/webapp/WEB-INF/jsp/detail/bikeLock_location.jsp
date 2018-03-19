<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<%@include file="../common/body.jsp"%>
<style type="text/css">

#container {
	width: 800px;
	height: 500px;
}

.button-group {
	position: absolute;
	bottom: 20px;
	right: 20px;
	font-size: 12px;
	padding: 10px;
}

.button-group .button {
	height: 28px;
	line-height: 28px;
	background-color: #0D9BF2;
	color: #FFF;
	border: 0;
	outline: none;
	padding-left: 5px;
	padding-right: 5px;
	border-radius: 3px;
	margin-bottom: 4px;
	cursor: pointer;
}
.button-group .inputtext {
	height: 26px;
	line-height: 26px;
	border: 1px;
	outline: none;
	padding-left: 5px;
	padding-right: 5px;
	border-radius: 3px;
	margin-bottom: 4px;
	cursor: pointer;
}
 
*/
#tip {
	background-color: #fff;
	padding-left: 10px;
	padding-right: 10px;
	position: absolute;
	font-size: 12px;
	right: 10px;
	top: 20px;
	border-radius: 3px;
	border: 1px solid #ccc;
	line-height: 30px;
}


.amap-info-content {
	font-size: 12px;
}

#myPageTop {
	position: absolute;
	top: 5px;
	right: 10px;
	background: #fff none repeat scroll 0 0;
	border: 1px solid #ccc;
	margin: 10px auto;
	padding:6px;
	font-family: "Microsoft Yahei", "微软雅黑", "Pinghei";
	font-size: 14px;
}
#myPageTop label {
	margin: 0 20px 0 0;
	color: #666666;
	font-weight: normal;
}
#myPageTop input {
	width: 170px;
}
#myPageTop .column2{
	padding-left: 25px;
}

 .marker {
            color: #ff6600;
            padding: 4px 10px;
            border: 1px solid #fff;
            white-space: nowrap;
            font-size: 12px;
            font-family: "";
            background-color: #0066ff;
        }
</style>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">锁定位</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
				<label>锁IMEI:${bikeLockInfo.bikeLockCode }</label>
				<label>车辆编号：${bikeLockInfo.bikeCode }</label>
				<label>
				电压:${bikeLockInfo.bikeLockVoltage}V
				</label>
				<label>
					锁状态:
					<c:if test="${bikeLockInfo.bikeLockStatus==0 }">已锁</c:if>
					<c:if test="${bikeLockInfo.bikeLockStatus==1 }">未锁</c:if>
					<c:if test="${bikeLockInfo.bikeLockStatus==2 }">车锁受阻</c:if>
				</label>
				<label>
					在线状态:
					<c:if test="${bikeLockInfo.bikeLockState==0 }"><div class="state state-gray">未在线</div></c:if>
					<c:if test="${bikeLockInfo.bikeLockState==1 }"><div class="state state-green">在线</div></c:if>
				</label>
				</div>
				<div class="panel-heading">
				<label>注意：在收到锁返回信息之前，将显示上一次定位位置</label>
				<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
				<a class="btn btn-primary" 
				 onclick="sendGPS()">定位</a>
				</div>
				
				<div class="panel-body">
					<div id="container" class="form-control"></div>
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


<%@include file="../common/buttom.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
    //实例化Geocoder
    geocoder = new AMap.Geocoder({
    });
    //TODO: 使用geocoder 对象完成相关功能
  })
  
  //
  var x = '${latLng.lng}';
  var y = '${latLng.lat}';
  var lnglatXY = [x,y]
  var infoWindow = new AMap.InfoWindow({offset:new AMap.Pixel(0,-25)});
  geocoder.getAddress(lnglatXY, function(status, result) {
      if (status === 'complete' && result.info === 'OK') {
           //获得了有效的地址信息:
//            address=result.regeocode.formattedAddress.split("市",2)[1];
//            address=address.substring(3,address.length);
           var index = result.regeocode.formattedAddress.indexOf("市");
           address = result.regeocode.formattedAddress.substring(index+1);
           infoWindow.setContent(address);

      }else{
            //获取地址失败
      }
    }); 
  

  var map = new AMap.Map('container', {
      resizeEnable: true,
      zoom:13,
      center: [x,y]
      
  });
//   var icon = new AMap.Icon({
//       image: 'images/bike.png',
//       map:map,
//       size:new AMap.Size(45, 50.5),
//       imageSize: new AMap.Size(45, 50.5)
//     });
  
  marker = new AMap.Marker({
//       icon: icon,
      position: [x,y],
  });
  marker.setMap(map);
  infoWindow.open(map, lnglatXY);
  
function sendGPS(){
	$.ajax({
		url:"${basePath}cms/lock/sendGPS.action?bikeLockCode=${bikeLockInfo.bikeLockCode }",
		type:'post',
		success:function(data){
			if(data!=""){
				$.confirm('获取GPS信息指令发送成功，请稍后刷新页面确认',[{yes:"确定"},{no:"取消"}],function(type,e){
					window.location.reload();
				}); 
			}else{
				$.alert("硬件尚未与服务器关联");
			}
		}
	});
}
   
</script>

</html>
