 	var Lng,Lat;
    var startPoint=null;//起点
    var map, geolocation,dragListener,clickListener;
    var city;//存放定位点的城市，用于判断是否同城租车等
    var startMarker=new AMap.Marker({});;
    var geoMarker;
    var rentId = $.getQueryString('rentId');


    //加载地图，调用浏览器定位服务
    map = new AMap.Map('mapContainer', {
      resizeEnable: true,
      mapStyle:'light',
      zoom: 15
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
            timeout: 3000,          //超过3秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10,20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            buttonDom:'<div class="localmap"><i class="icon-focus"></i></div>',
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
//    //解析定位结果
    function onComplete(data) {
       if(geoMarker)
    	   geoMarker.hide();
       geoPoint=data.position;
       var icon = new AMap.Icon({
           image: '/images/gps.png',
           map:map,
           size:new AMap.Size(20, 20),
           imageSize: new AMap.Size(20, 20)
         });
       geoMarker = new AMap.Marker({
         position: geoPoint,
         icon: icon,
         offset: {x: -10,y: -10}
       });
       geoMarker.setMap(map);
      geocoder.getAddress(geoPoint, function(status, result) {//储存定位所在城市
        if (status === 'complete' && result.info === 'OK') {
             //获得了有效的地址信息:
             city=result.regeocode.addressComponent.city;
           }else{
             city='未知城市';
             //获取地址失败
           }
         }); 
//      var str=['定位成功'];
//      str.push('经度：' + data.position.getLng());
//      Lng=data.position.getLng();
//      str.push('纬度：' + data.position.getLat());
//      Lat=data.position.getLat();
//      str.push('精度：' + data.accuracy + ' 米');
//      str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
      //alert(str)
      //document.getElementById('tip').innerHTML = str.join('<br>');
    }
    //解析定位错误信息
    function onError(data) {
    	 $.alert("定位失败,请确认已开启定位",true,function(){
			//window.location.href=url+"/html/watchbike.html?v="+new Date().getTime();
         },5000,{width:300});
//      document.getElementById('tip').innerHTML = '定位失败';
    }

    var flag="0";
//    var timer=self.setInterval("selectAble()",5000);//搜索时间限制
    function selectAble(){
    	flag="0";
      }
    


    var _moveend = function(e) {//拖动地图后执行的方法
    	if(flag=="0"){//搜索时间限制
    		if(map.getZoom()<=17){
//    			cluster.clearMarkers();
//          	getTips(map.getCenter(),rentId);
//            	flag="1";	
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
function getTips(data,rentId){//后台搜索周围的车
		if(markers){
			$.each(markers, function(i, marker) {
				marker.setMap(null);
	        });
	
		}
		markers = [];
      	$.ajax({
          url : url+"/fixedReturn/findAllFixedsNow.action",
          data:"rentId="+rentId,
          async : true,  
          type : "post", 
          beforeSend : function() {
              //alert("before");
          },
          success : function(data) {
          		if(data.data.fixedList==null||data.data.fixedList==""){
                  } else{
                      $.each(data.data.fixedList, function(i, LatLng) {
                    	  var markerPosition = [LatLng.lng,LatLng.lat];
                          var icon = new AMap.Icon({
                              image: '/images/bikeState.png',
                              map:map,
                              size:new AMap.Size(26.5,40),
                              imageSize: new AMap.Size(26.5,40)
                            });
                          var marker = new AMap.Marker({
                            position: markerPosition,
                            icon: icon,
                            offset: {x: -13.5,y: -40},
                            extData:LatLng.fixedReturnId
                          });
                          marker.on('click',markerClick);
                          marker.setMap(map);
                          markers.push(marker);
                      });
//                      addCluster(1);
                  }	
          },
          error : function() {
              // $.alert("请求失败");
          }
      });
    } 
  getTips(map.getCenter(),rentId);

// 添加点聚合
function addCluster(tag) {
  if (cluster) {
    cluster.setMap(null);
  }
  if (tag == 1) {//自定义图标
    var sts = [{
    	url: "/images/bikeState.png",
        size: new AMap.Size(50, 60),
        offset: new AMap.Pixel(-25, -56),
        textSize:12,
    }, {
      url: "/images/bikeState.png",
      size: new AMap.Size(50, 60),
      offset: new AMap.Pixel(-25, -56),
      textSize:12,
    }, {
      url: "/images/bikeState.png",
      size: new AMap.Size(50, 60),
      offset: new AMap.Pixel(-25, -56),
      textSize:12,
      textColor: '#ff0000'
    }, {
      url: "/images/bikeState.png",
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
//   timer=self.setInterval("selectAble()",5000);
   flag='0';
   route.clear();
   if(startPoint!=null){
	   map.setZoomAndCenter(16,startPoint);
   }
   document.getElementById("centerPoint").style.display="block";
   startPoint=null;
   startMarker.hide();
   setSmall();
   $('.news-box').show();
   $('.watchbox').hide();

}

function regular(){//固定起点图标
    document.getElementById("centerPoint").style.display="none";
    startPoint=map.getCenter();
    var icon = new AMap.Icon({
      image: '/images/geo.png',
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
     var time;
     function markerClick(e){//点击车辆后规划路线
    	 if($('#appointing').css("display")=="none"){
	    	  $.isLoading.show('路线规划中...');
	    	  $('.news-box').hide();
	    	  $('.watchbox').show();
//		      clearInterval(timer);
		      flag='1';
		      setSmall();
		      lastMarker=e.target;
		      var icon = new AMap.Icon({
		          image: '/images/bikeState.png',
		          map:map,
		          size: new AMap.Size(26.5,40),
		          imageSize: new AMap.Size(26.5,40)
		        });
		      e.target.setIcon(icon);
		      e.target.setOffset(new AMap.Pixel(-13.5,-36.5));
		      geocoder.getAddress(e.target.getPosition(), function(status, result) {//储存定位所在城市
		        if (status === 'complete' && result.info === 'OK') {
		             //获得了有效的地址信息:
		             if(false){//如果定位的城市与车所在的城市不同 result.regeocode.addressComponent.city!=city
		                //alert("我们正努力进入更多城市！")
		             }
		             else{
		            	 routeSearch(e.target,geoPoint);
		             }
		        }else{
		             //获取地址失败
		           }
		      }); 
     }
    	 getFixedInfo(e.target.getExtData())
    }
      map.setFitView();
      
      function getFixedInfo(id){
    	  $("#fixedTel").html("");
    	  $("#fixedModels").html("");
    	  $.ajax({
    		  type:'post',
    		  url:url+'/fixedReturn/fixedReturnInfos.action',
    		  data:'fixedReturnId='+id,
    		  beforeSend: function(request) {
    	            request.setRequestHeader("fromFlag", "2");
    	        },
    		  success : function(data) {
    			  if(data!=null&&data!=""){
    				  $("#bikeNum").html(data.data.fixedReturn.fixedReturnBikeNum);if(data.data.fixedReturn.fixedReturnBikeNum<0){
    					  $("#bikeNum").html(0);
    				  }else{
    					  $("#bikeNum").html(data.data.fixedReturn.fixedReturnBikeNum);
    				  }
        			  $("#fixedName").html(data.data.fixedReturn.fixedReturnName);
        			  $("#fixedBrief").html(data.data.fixedReturn.fixedReturnBrief);
        			  var arr= new Array()
        			  var arr1= new Array()
        			  var str2 = data.data.fixedReturn.fixedReturnTel;
        			  var str="";
        			  var str1="";
        			  if(str2!=""){
        				  str2 = str2.replace(",","，");
        				  arr = str2.split("，");
        				  for(var index in arr){
          					str += "<p>"+arr[index]+"</p>";
          				  }
          				  $("#fixedTel").append(str);
        			  }
        			  arr1 = data.data.fixedReturn.models;
        			  
        			  for(var index in arr1){
        				  str1 += '<li class="padding-left-44"><label>'+arr1[index].modelsName+'：</label> <div class="info">';
        				  if(null!=arr1[index].modelRentPrice&&""!=arr1[index].modelRentPrice){
        					  str1 += '<p>预付款：<span>'+arr1[index].modelsDeposit+'</span>元</p>';
        					  str1 += '<div class="clearfix rent-list"><label class="pull-left">租金：</label><div class="pull-left">';
        					  var obj = arr1[index].modelRentPrice;
        					  if(arr1[index].modelRentPrice.rentPriceOption==1){
            					  for(var i in obj.priceList){
                					  str1 += '<p>'+obj.priceList[i].fromTime+'-'+obj.priceList[i].toTime+'小时<span >'+obj.priceList[i].rentPrice.toFixed(2)+'</span>元/小时</p>';
                				  }
                				  if(obj.priceList.length>0){
                					  str1 += '<p>'+obj.priceList[obj.priceList.length-1].toTime+'小时后<span >'+arr1[index].modelRentPrice.lastPrice.toFixed(2)+'</span>元/小时</p>';
                				  }else{
                					  str1 += '<p>'+arr1[index].modelRentPrice.lastPrice.toFixed(2)+'元/小时</p>';
                				  }
            				  }else if(arr1[index].modelRentPrice.rentPriceOption==2){
            					  for(var i in obj.priceList){
                					  str1 += '<p>'+obj.priceList[i].fromTime+'-'+obj.priceList[i].toTime+'个 半小时<span >'+obj.priceList[i].rentPrice.toFixed(2)+'</span>元/半小时</p>';
                				  }
                				  if(obj.priceList.length>0){
                					  str1 += '<p>'+obj.priceList[obj.priceList.length-1].toTime+'个 半小时后<span >'+arr1[index].modelRentPrice.lastPrice.toFixed(2)+'</span>元/半小时</p>';
                				  }else{
                					  str1 += '<p>'+arr1[index].modelRentPrice.lastPrice.toFixed(2)+'元/半小时</p>';
                				  }
            				  }
        					  str1 += '</div></div>'
        				  }else{
        					  var obj = arr1[index].modelRentPriceList;
        					  for(var j in obj){
        						  if(obj[j].rentPriceType==1){
        							  str1 += '<label>游客计费：</label>';
        							  str1 += '<div class="info"><p>预付款：<span>'+arr1[index].modelsDeposit+'</span>元</p>'
        							  str1 += '<div class="clearfix rent-list"><label class="pull-left">租金：</label><div class="pull-left">';
        						  }else if(obj[j].rentPriceType==2){
        							  str1 += '<br><label>会员计费：</label>';
        							  str1 += '<div class="info"><div class="clearfix rent-list"><label class="pull-left">租金：</label><div class="pull-left">';
        						  }
        						  
        						  if(obj[j].rentPriceOption==1){
                					  for(var i in obj[j].priceList){
                    					  str1 += '<p>'+obj[j].priceList[i].fromTime+'-'+obj[j].priceList[i].toTime+'小时<span >'+obj[j].priceList[i].rentPrice.toFixed(2)+'</span>元/小时</p>';
                    				  }
                    				  if(obj[j].priceList.length>0){
                    					  str1 += '<p>'+obj[j].priceList[obj[j].priceList.length-1].toTime+'小时后<span >'+obj[j].lastPrice.toFixed(2)+'</span>元/小时</p>';
                    				  }else{
                    					  str1 += '<p>'+obj[j].lastPrice.toFixed(2)+'元/小时</p>';
                    				  }
                				  }else if(obj[j].rentPriceOption==2){
                					  for(var i in obj[j].priceList){
                    					  str1 += '<p>'+obj[j].priceList[i].fromTime+'-'+obj[j].priceList[i].toTime+'个 半小时<span >'+obj[j].priceList[i].rentPrice.toFixed(2)+'</span>元/半小时</p>';
                    				  }
                    				  if(obj[j].priceList.length>0){
                    					  str1 += '<p>'+obj[j].priceList[obj[j].priceList.length-1].toTime+'个 半小时后<span >'+obj[j].lastPrice.toFixed(2)+'</span>元/半小时</p>';
                    				  }else{
                    					  str1 += '<p>'+obj[j].lastPrice.toFixed(2)+'元/半小时</p>';
                    				  }
                				  }
        						  str1 += '</div></div></div>'
        					  }
        					  
        				  }
        				  str1 += '</div></li>'
        				  }
        				  $("#fixedModels").append(str1);
    			  }
    			 
    		  }
    		  
    	  })
      }

    function routeSearch(marker,geoPoint){
    	document.getElementById("centerPoint").style.display="none";
        if(null==startPoint){
        	/*if(null==geoPoint)
        		startPoint=map.getCenter();
        	else*/
        		startPoint=geoPoint;
	          var icon = new AMap.Icon({
	            image: '/images/geo.png',
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
        walking.search(startPoint,marker.getPosition(), function(status, result){
          if(status === 'complete'){
        	  (route).autoRender({
  					data: result,
                  	map: map,
                  	hideMarkers:true
  			   });
              //逆地理编码
              var wresult=result;
              var address;
              var lnglatXY=marker.getPosition();//地图上所标点的坐标

              geocoder.getAddress(lnglatXY, function(status, result) {
                if (status === 'complete' && result.info === 'OK') {
                     //获得了有效的地址信息:
//                     address=result.regeocode.formattedAddress.split("市",2)[1];
                     
                     var index = result.regeocode.formattedAddress.indexOf("市");
                     address = result.regeocode.formattedAddress.substring(index+1);
                     $('#address').html(address);
                     $('#distance').html(wresult.routes[0].distance);
                     $('#walkTime').html(parseInt(wresult.routes[0].time/60));
                     time=parseInt(wresult.routes[0].time/60);
                }else{
                      //获取地址失败
                }
              }); 
        $.isLoading.hide();
      }
    });
    }
      
    function setSmall(){//撤销规划图标变回原大小
      if(lastMarker!=null){
        var icon = new AMap.Icon({
          image: '/images/bikeState.png',
          map:map,
          size: new AMap.Size(26.5,40),
          imageSize: new AMap.Size(26.5,40)
        });
        lastMarker.setIcon(icon);
        lastMarker.setOffset(new AMap.Pixel(-22.7,-50.5));
        lastMarker=null;
      }
    }
 /*   function getAppTime(id){
      	$.ajax({
            url : url+"/appoint/appointPlan.action",
            data:"bikeId="+id,
            async : true,  
            type : "post", 
            dataType:"json",
            beforeSend : function() {
                //alert("before");
            },
            success : function(data) {
            		if(data==null||data==""){
                    } else{
                    	$('#freeTime').html(data.data.appointmentPlan.appointmentPlanFreetime);
                    	setAmount.max=data.data.appointmentPlan.appointmentPlanFreetime;
                    	$('#planId').val(data.data.appointmentPlan.appointmentPlanId);
                    	$('#appBikeId').val(data.data.bike.bikeId);
                    	$('#userId').val(data.data.userId);
                    	if(time>setAmount.max){
                    		$('#exc-number').val(setAmount.max);
                    	}
                    	else{
                    		if(time==0)
                    			$('#exc-number').val(1);
                    		else
                    		$('#exc-number').val(time);
                    	}
                    }	
            },
            error : function() {
                //$.alert("请求失败");
            }
        });
    }*/
    
    
/*    function getSysMsg(){
      	$.ajax({
            url : url+"/sysMsg/msgList.action",
            async : true,  
            type : "post", 
            dataType:"json",
            beforeSend : function() {
                //alert("before");
            },
            success : function(data) {
	            	$.each(data.data.sysMsgList, function(i, sysMsg) {
	            		
	            		$('#sysMsgs').append("<a class='swiper-slide' href='notice.html?id="+sysMsg.sysMsgId+"'>"+sysMsg.sysMsgContent+"</a>")
	                });
            		var mySwiper4 = new Swiper('#swiper-news', {
            	        direction : 'vertical',
            	        autoplay : 5000,
            	        loop : true,
            	        onlyExternal : true,//禁止拖动
            	   })
            },
            error : function() {
                //$.alert("请求失败");
            }
        });
    }
    getSysMsg();*/
    
   
   
