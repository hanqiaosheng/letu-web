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

.amap-info-content {
    font-size: 12px;
}

#myPageTop {
    position: absolute;
    top: 5px;
    left: 8px;
    font-family: "Microsoft Yahei", "微软雅黑", "Pinghei";
    font-size: 14px;
}

#panel {
  position: fixed;
  background-color: white;
  max-height: 90%;
  overflow-y: auto;
  top: 10px;
  right: 10px;
  width: 280px;
}
#myPageTop{
    width:100%;
    margin-top:5px;
    height:30px;
}
#myPageTop .input-box{
    width:95%;
    height:30px;
    background:#6bb1f7;
    border-radius:5px;
}
#myPageTop_hint{
  width: 95%;
    margin-top:5px;
    background:rgba(235,235,235,0.7);
    overflow:hidden;

}
#myPageTop_hint div{
    width:50%;
    float:left;
}
#myPageTop_hint p{
    width:100%;
    text-align:center;
    margin:5px 0;
}
#myPageTop_hint div p span{color:red;}
#myPageTop .input-box input{
    height:28px;
    margin:1px 0 0 1px;
    width:83%;
    border-radius:5px;
    padding-left:10px;
    position:relative;
}
#myPageTop .input-box button{
    height:30px;
    line-height:30px;
    background:#6bb1f7;
    color:#fff;
    width:12%;
    text-align:center;
    border-radius:5px;
}
#myPageTop .input-box svg{
    position:absolute;
    top:6px;
    left:2px;
    z-index:100;
}
.left-bottom-botton{
    position:absolute;
    bottom:60px;
    left:11px;
}
.left-bottom-botton button{
    padding:3px;
    background:#6bb1f7;
    color:#fff;
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
			<h1 class="page-header"><c:if test="${flag==1 }">借车位置</c:if><c:if test="${flag==2 }">还车位置</c:if></h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
				<label>车辆编号:${bike.bikeId }</label>
				<label>
				车辆状态:
				<c:if test="${bike.bikeState==0 }">空闲中</c:if>
				<c:if test="${bike.bikeState==1 }">预约中</c:if>
				<c:if test="${bike.bikeState==2 }">租借中</c:if>
				<c:if test="${bike.bikeState==3 }">维护中</c:if>
				<c:if test="${bike.bikeState==4 }">锁定中</c:if>
				<c:if test="${bike.bikeState==5 }">冻结中</c:if>
				</label>
				
				
				<label><c:if test="${flag==1 }">借车位置:</c:if><c:if test="${flag==2 }">还车位置:</c:if></label><label id="bikePosition"></label>
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
  var x = '${bikeRentInfo.rentStartlng }';
  var y = '${bikeRentInfo.rentStartlat }';
  var lnglatXY = [x,y]
  geocoder.getAddress(lnglatXY, function(status, result) {
      if (status === 'complete' && result.info === 'OK') {
           //获得了有效的地址信息:
           address=result.regeocode.formattedAddress;//.split("市")[1];
           address=address.substring(3,address.length);
           $("#bikePosition").text(address);

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
      image: 'images/bike.png',
      map:map,
      size:new AMap.Size(45, 50.5),
      imageSize: new AMap.Size(45, 50.5)
    });
  
  marker = new AMap.Marker({
      icon: icon,
      position: [x,y],
      offset: {x: -22.5,y: -50.5}
  });
  marker.setMap(map);
  

   
</script>

</html>
