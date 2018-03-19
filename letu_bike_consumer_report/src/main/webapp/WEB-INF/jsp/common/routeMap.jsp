<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
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
<script type="text/javascript">

AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
    //实例化Geocoder
    geocoder = new AMap.Geocoder({
    });
    //TODO: 使用geocoder 对象完成相关功能
  })
  
  //
   
  var startx = "${bikeRentInfo.rentStartlng}";
  var starty = "${bikeRentInfo.rentStartlat}";
  var endx =  "${bikeRentInfo.rentEndlng}";
  var endy ="${bikeRentInfo.rentEndlat}";
  var map = new AMap.Map('container', {
      resizeEnable: true,
      zoom:13,
      center: [startx,starty]
      
  });
  
  var startInfoWindow = new AMap.InfoWindow({offset:new AMap.Pixel(0,-45)});
  var endInfoWindow = new AMap.InfoWindow({offset:new AMap.Pixel(0,-45)});
  if(startx!=""&&starty!=""){
	  var startlnglatXY = [startx,starty];
	  geocoder.getAddress(startlnglatXY, function(status, result) {
	      if (status === 'complete' && result.info === 'OK') {
	           //获得了有效的地址信息:
	           address=result.regeocode.formattedAddress;//.split("市")[1];
	           address=address.substring(3,address.length);
	           startInfoWindow.setContent("起点："+address);
	           startInfoWindow.open(map, startlnglatXY);
	      }else{
	            //获取地址失败
	      }
	    }); 
	  var startIcon = new AMap.Icon({
	      image: 'images/bike.png',
	      map:map,
	      size:new AMap.Size(45, 50.5),
          imageSize: new AMap.Size(45, 50.5)
	    });
	  startMarker = new AMap.Marker({
	      icon: startIcon,
	      position: [startx,starty],
	      offset: {x: -22.7,y: -50.5}
	  });
	  startMarker.on('click',startMarkerClick);
	  startMarker.setMap(map);
  }
  if(endx!=""&&endy!=""){
	  var endlnglatXY=[endx,endy];
	  geocoder.getAddress(endlnglatXY, function(status, result) {
	      if (status === 'complete' && result.info === 'OK') {
	           //获得了有效的地址信息:
	           address=result.regeocode.formattedAddress;//.split("市")[1];
	           address=address.substring(3,address.length);
	           endInfoWindow.setContent("终点："+address);

	      }else{
	            //获取地址失败
	      }
	    });  
	  var endIcon = new AMap.Icon({
	      image: 'images/bike.png',
	      map:map,
	      size: new AMap.Size(50, 56.1),
          imageSize: new AMap.Size(50, 56.1)
	    });
	  endMarker = new AMap.Marker({
	      icon: endIcon,
	      position: [endx,endy],
	  	  offset: {x: -25,y: -56.1}
	  });
	  endMarker.on('click',endMarkerClick);
	  endMarker.setMap(map);
  }
  function startMarkerClick(e){
	  startInfoWindow.open(map, e.target.getPosition());
  }
  function endMarkerClick(e){
	  endInfoWindow.open(map, e.target.getPosition());
  }
  var walking = new AMap.Walking({
	  map: map,
	  hideMarkers:true,//隐藏起点终点
	  //panel: "panel"//路线详情的显示
	  });
  walking.search(startMarker.getPosition(),endMarker.getPosition(), function(status, result){
      if(status === 'complete'){
            }else{
                  //获取地址失败
            }
          }); 
    

  

   
</script>