<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<link rel="stylesheet" href="map/maskMap.css">
<div id="mask" class="mask">
     <div class="map-box">
     	<div class="map" id="container">
     	 	<div id="myPageTop">
			    <table>
			        <tr>
			            <td>
			                <label>请输入关键字：</label>
			            </td>
			        </tr>
			        <tr>
			            <td>
			                <input id="tipinput"/>
			            </td>
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
	$(document).on({
		"focus":function(){
			_this=$(this);
			 // if(_this.val()==""){  
		    	showMask();
		     // }  
		}
	},'.go-back')
	$(".mask .rentState").click(function(){
	    $("#mask").hide(500);
	})
	$(".mask .submit").click(function(){
		_this.val(lnglat);
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
    }
	
</script>

<script type="text/javascript">
	$.each($('.lnglatStr'),function(){
		var str=$(this).data('lng')+','+$(this).data('lat')
		if($(this).data('lng')&&$(this).data('lat')){
			getlnglatXY(str,$(this));
		}
	})
	
	$.each($('.lnglatStr2'),function(){
		var str=$(this).data('lng2')+','+$(this).data('lat2')
		if($(this).data('lng2')&&$(this).data('lat2')){
			getlnglatXY(str,$(this));
		}
	})
	
	function getlnglatXY(obj,_this){
		 AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
			    //实例化Geocoder
			    geocoder = new AMap.Geocoder({
			    });
			    //TODO: 使用geocoder 对象完成相关功能
			  })
			  
			  //
			  var lnglatXY = obj.split(',');
		      lnglatXYTo(lnglatXY,_this);
	}
	
   function lnglatXYTo(obj,_this){
	   var lnglatXY = obj;
	   geocoder.getAddress(lnglatXY, function(status, result) {
		      if (status === 'complete' && result.info === 'OK') {
		           //获得了有效的地址信息:
		           address=result.regeocode.formattedAddress;//.split("市")[1];
		           address=address.substring(3,address.length);
		           _this.html(address); 

		      }else{
		            //获取地址失败
		      }
		    }); 
   } 
</script>
