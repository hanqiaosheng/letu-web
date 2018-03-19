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
			<h1 class="page-header">站点位置</h1>
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
<script type="text/javascript">
AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
    //实例化Geocoder
    geocoder = new AMap.Geocoder({
    });
    //TODO: 使用geocoder 对象完成相关功能
  })
  
  //
  var x = '${fixedReturn.fixedReturnLng}';
  var y = '${fixedReturn.fixedReturnLat}';
  var lnglatXY = [x,y]
  var infoWindow = new AMap.InfoWindow({offset:new AMap.Pixel(0,-45)});
  geocoder.getAddress(lnglatXY, function(status, result) {
      if (status === 'complete' && result.info === 'OK') {
           //获得了有效的地址信息:
           address=result.regeocode.formattedAddress;//.split("市")[1];
           address=address.substring(3,address.length);
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
  var icon = new AMap.Icon({
      image: 'images/bikeState.png',
      map:map,
      size:new AMap.Size(26.5,40),
      imageSize: new AMap.Size(26.5,40)
    });
  
  marker = new AMap.Marker({
      icon: icon,
      position: [x,y],
  	  //offset: {x: -22.5,y: -50.5}
  });
  marker.setMap(map);
  getTips(lnglatXY);
  infoWindow.open(map, lnglatXY);
  var editor={};
  editor._circle=(function(){
      var circle = new AMap.Circle({
          center: [x, y],// 圆心位置
          radius: 400, //半径
          strokeColor: "#98F5FF", //线颜色
          strokeOpacity: 1, //线透明度
          strokeWeight: 3, //线粗细度
          fillColor: "#98F5FF", //填充颜色
          fillOpacity: 0.35//填充透明度
      });
      circle.setMap(map);
      return circle;
  })();
  var markers = [];
  function getTips(data){//后台搜索周围的车
		if(markers){
			$.each(markers, function(i, marker) {
				marker.setMap(null);
	        });
		}
		markers = [];
    	$.ajax({
        url : "cms/bike/findFixedBikes.action",
        data:"latLng="+data+"&fixedReturnId="+'${fixedReturn.fixedReturnId}',
        async : true,  
        type : "post", 
        beforeSend : function() {
            //alert("before");
        },
        success : function(data) {
        		if(data==null||data==""){
                } else{
                    $.each(data, function(i, LatLng) {
                  	  var markerPosition = [LatLng.lng,LatLng.lat];
                        var icon = new AMap.Icon({
                            image: 'images/bike.png',
                            map:map,
                            size:new AMap.Size(45, 50.5),
                            imageSize: new AMap.Size(45, 50.5)
                          });
                       
                        var marker = new AMap.Marker({
                          position: markerPosition,
                          icon: icon,
                          offset: {x: -22.7,y: -50.5},
                          extData:LatLng.id
                        });
                        marker.setMap(map);
                        markers.push(marker);
                    });
//                    addCluster(1);
                }	
        },
        error : function() {
            // $.alert("请求失败");
        }
    });
  } 

   
</script>

</html>
