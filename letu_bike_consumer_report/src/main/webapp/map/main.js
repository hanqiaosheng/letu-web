 	var Lng,Lat;
    var startPoint=null;//起点
    var map, geolocation,dragListener,clickListener;
    var city;//存放定位点的城市，用于判断是否同城租车等
    var infoWindow = new AMap.InfoWindow({offset:new AMap.Pixel(0,-45)});
    var startMarker=new AMap.Marker({});;



    //加载地图，调用浏览器定位服务
    map = new AMap.Map('mapContainer', {
      resizeEnable: true,
      mapStyle:'fresh',
      zoom: 13
    });

    AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
        //实例化Geocoder
        geocoder = new AMap.Geocoder({
        });
        //TODO: 使用geocoder 对象完成相关功能
      })




    var geoPoint;//存放定位点的经纬度信息
    map.plugin('AMap.Geolocation', function() {
      geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 1000,          //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            showCircle: true,  
            zoomToAccuracy: false,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            showMarker: false, 
            buttonPosition:'LB'
          });
      map.addControl(geolocation);
      geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
      });
    //解析定位结果
    function onComplete(data) {
//       geoPoint=data.position;
//       var icon = new AMap.Icon({
//           image: 'gps.png',
//           map:map,
//           size:new AMap.Size(32.5, 27),
//           imageSize: new AMap.Size(32.5, 27)
//         });
//       var marker = new AMap.Marker({
//         position: geoPoint,
//         icon: icon,
//         offset: {x: -32.5,y: -13.5}
//       });
//       marker.setMap(map);
      geocoder.getAddress(geoPoint, function(status, result) {//储存定位所在城市
        if (status === 'complete' && result.info === 'OK') {
             //获得了有效的地址信息:
             city=result.regeocode.addressComponent.city;
           }else{
             city='未知城市';
             //获取地址失败
           }
         }); 
      var str=['定位成功'];
      str.push('经度：' + data.position.getLng());
      Lng=data.position.getLng();
      str.push('纬度：' + data.position.getLat());
      Lat=data.position.getLat();
      str.push('精度：' + data.accuracy + ' 米');
      str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
      //alert(str)
      //document.getElementById('tip').innerHTML = str.join('<br>');
    }
    //解析定位错误信息
    function onError(data) {
      document.getElementById('tip').innerHTML = '定位失败';
    }

    var flag="0";
    var timer=self.setInterval("selectAble()",5000);
    function selectAble(){
    	flag="0";
      }
    


    var _moveend = function(e) {//拖动地图后执行的方法
    	if(flag=="0"){
    		if(map.getZoom()<=17){
    			cluster.clearMarkers();
            	getTips(map.getCenter());
            	flag="1";	
    		}
    	}
    	
//       	geocoder.getAddress(map.getCenter(), function(status, result) {//储存定位所在城市
//         if (status === 'complete' && result.info === 'OK') {
//              //获得了有效的地址信息:
//              if(result.regeocode.addressComponent.city!=city){
//                 //alert("我们正努力进入更多城市！")
//                 return;
//              }else{
//                 //alert("此处执行搜索"+map.getCenter()+"附近的车辆")//拖动地图之后执行的方法，此处获取拖动后经纬度周边的车并执行布点方法
//              }
//           }else{
//              //获取地址失败
//            }
//          }); 
        
    }
    dragListener=new AMap.event.addListener(map, "moveend", _moveend);
    clickListener=new AMap.event.addListener(map, "click", cancel);

    var cluster, markers = [];
function getTips(data){//后台搜索周围的车
		markers = [];
      	$.ajax({
          url : "cms/bike/findBikes.action",
          data:"latLng="+data,
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
                              image: 'bike.png',
                              map:map,
                              size:new AMap.Size(45, 50.5),
                              imageSize: new AMap.Size(45, 50.5)
                            });
                          var marker = new AMap.Marker({
                            position: markerPosition,
                            icon: icon,
                            offset: {x: -22.7,y: -50.5}
                          });
                          marker.on('click',markerClick);
                          markers.push(marker);
                      });
                      addCluster(1);
                  }	
          },
          error : function() {
              // $.alert("请求失败");
          }
      });
    } 
  getTips(map.getCenter());

// 添加点聚合
function addCluster(tag) {
  if (cluster) {
    cluster.setMap(null);
  }
  if (tag == 1) {//自定义图标
    var sts = [{
    	url: "bikeSmall.png",
        size: new AMap.Size(50, 60),
        offset: new AMap.Pixel(-25, -56),
        textSize:12,
        textColor:'ff0000'
    }, {
      url: "bikeSmall.png",
      size: new AMap.Size(50, 60),
      offset: new AMap.Pixel(-25, -56),
      textSize:12,
      textColor: '#ffbf00'
    }, {
      url: "bikeSmall.png",
      size: new AMap.Size(50, 60),
      offset: new AMap.Pixel(-25, -56),
      textSize:12,
      textColor: '#ff0000'
    }, {
      url: "bikeSmall.png",
      size: new AMap.Size(50, 60),
      offset: new AMap.Pixel(-25, -56),
      textSize:12,
      textColor: '#ff00ed'
    }];
    map.plugin(["AMap.MarkerClusterer"], function() {
      cluster = new AMap.MarkerClusterer(map, markers, {
        styles: sts,
        maxZoom:17
      });
    });
  } 
}





    
    
function cancel(){//撤销规划
   timer=self.setInterval("selectAble()",5000);
   route.clear();
   if(startPoint!=null){
	   map.setZoomAndCenter(16,startPoint);
   }
   document.getElementById("centerPoint").style.display="block";
   startPoint=null;
   startMarker.hide();
   document.getElementById("myPageTop_hint").style.display="none";
   setSmall();
   infoWindow.close();

}

function regular(){//固定起点图标
    document.getElementById("centerPoint").style.display="none";
    startPoint=map.getCenter();
    var icon = new AMap.Icon({
      image: 'geo.png',
      map:map,
      size: new AMap.Size(15, 34.77),
      imageSize: new AMap.Size(15, 34.77)
    });
    startMarker = new AMap.Marker({
      icon: icon,
      position:startPoint,
      map: map,
      offset: {x: -7.5,y: -34.77}
    });
}





     //步行路线规划
     var walking = new AMap.Walking({
      //map: map,
      hideMarkers:true,//隐藏起点终点
      //panel: "panel"//路线详情的显示
    }); 
     var route=new Lib.AMap.WalkingRender();
     var lastMarker=null;
     function markerClick(e){//点击车辆后规划路线
      clearInterval(timer);
      setSmall();
      lastMarker=e.target;
      var icon = new AMap.Icon({
          image: 'bike.png',
          map:map,
          size: new AMap.Size(50, 56.1),
          imageSize: new AMap.Size(50, 56.1)
        });
      e.target.setIcon(icon);
      e.target.setOffset(new AMap.Pixel(-25.2,-56.1));
      geocoder.getAddress(e.target.getPosition(), function(status, result) {//储存定位所在城市
        if (status === 'complete' && result.info === 'OK') {
             //获得了有效的地址信息:
             if(false){//如果定位的城市与车所在的城市不同 result.regeocode.addressComponent.city!=city
                //alert("我们正努力进入更多城市！")
             }
             else{
                document.getElementById("centerPoint").style.display="none";
                if(null==startPoint){
                  startPoint=map.getCenter();
                  var icon = new AMap.Icon({
                    image: 'geo.png',
                    map:map,
                    size: new AMap.Size(15, 34.77),
                    imageSize: new AMap.Size(15, 34.77)
                  });
                  startMarker = new AMap.Marker({
                    icon: icon,
                    position:startPoint,
                    map: map,
                    offset: {x: -7.5,y: -34.77}
                  });      
                }
                walking.search(startPoint,e.target.getPosition(), function(status, result){
                  if(status === 'complete'){
                	  (route).autoRender({
          					data: result,
                          	map: map,
                          	hideMarkers:true
          			   });
                      //逆地理编码
                      var wresult=result;
                      var address;
                      var lnglatXY=e.target.getPosition();//地图上所标点的坐标

                      geocoder.getAddress(lnglatXY, function(status, result) {
                        if (status === 'complete' && result.info === 'OK') {
                             //获得了有效的地址信息:
                             address=result.regeocode.formattedAddress.split("市",2)[1];
                             myPageTop_hint.innerHTML="<p>目的地：<span>"+address+"</span></p><div><p>步行距离</p><p><span>"+wresult.routes[0].distance+"</span>米</p></div><div><p>大约需要</p><p><span>"+parseInt(wresult.routes[0].time/60)+"</span>分钟</p></div>";
                              document.getElementById("myPageTop_hint").style.display="block";
                        }else{
                              //获取地址失败
                        }
                      }); 
                document.getElementById("myPageTop_hint").style.display="block";
                infoWindow.setContent("小主，上车，我带您兜风！");
                infoWindow.open(map, e.target.getPosition());
              }
            });
          }
        }else{
             //获取地址失败
           }
      }); 
    }
      map.setFitView();

 
      
    function setSmall(){//撤销规划图标变回原大小
      if(lastMarker!=null){
        var icon = new AMap.Icon({
          image: 'bike.png',
          map:map,
          size: new AMap.Size(45, 50.5),
          imageSize: new AMap.Size(45, 50.5)
        });
        lastMarker.setIcon(icon);
        lastMarker.setOffset(new AMap.Pixel(-22.7,-50.5));
        lastMarker=null;
      }
    }