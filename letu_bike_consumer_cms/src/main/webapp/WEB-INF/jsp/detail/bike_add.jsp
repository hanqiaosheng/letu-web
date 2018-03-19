<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<body>


<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<%@include file="../common/datePicker.jsp"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<link rel="stylesheet" href="map/maskMap.css">

<!-- <script type="text/javascript">
 function changeTel(obj){
	 $.ajax({
		 type:'post',
		 url:'cms/bikeRepair/bikeRepairInfo.action',
		 data:'managerId='+obj.value,
		 success:function(data){
			 $("#managerTel").val(data.managerTel);
		 }
	 });
 }

</script> -->
<script type="text/javascript">
  function newBatch(){
	  $.ajax({
			 type:'post',
			 url:'cms/bike/maxBatchNumber.action',
			 success:function(data){
				 if($("#batchButton").val()=="新建批次"){
					 $("#hideBatchNumber").attr("type","text");
					 $("#hideBatchNumber").val(data);
					 $("#hideBatchNumber").attr("name","bikeBatchNumber");
					 $("#bikeBatchNumber").hide();
					 $("#bikeBatchNumber").attr("name","");
					 $("#batchButton").val("重新选择");
				 }else{
					 $("#hideBatchNumber").attr("type","hidden");
					 $("#bikeBatchNumber").show();
					 $("#hideBatchNumber").attr("name","bikeBatchNumber");
					 $("#batchButton").val("新建批次");
				 }
				 
			 }
		 });
  }
</script>
<script type="text/javascript">
  function check(){
	  if($("#bikecode").val()==""){
		  $.alert("请输入车辆编号",true,function(){
          },false,{className:'ui-alert',width:300}); 
		  return false;
	  }
	  if($("#bikeManagerName").val()==""){
		  $.alert("请输入负责人",true,function(){
          },false,{className:'ui-alert',width:300}); 
		  return false;
	  }
	  if($("#bikeManagerTel").val()==""){
		  $.alert("请输入联系电话",true,function(){
          },false,{className:'ui-alert',width:300}); 
		  return false;
	  }
	  if($("#cStarttime").val()==""){
		  $.alert("请输入投放时间",true,function(){
          },false,{className:'ui-alert',width:300}); 
		  return false;
	  }
	  if($("#bikeAddress").val()==""){
		  $.alert("请输入投放地点",true,function(){
          },false,{className:'ui-alert',width:300}); 
		  return false;
	  }
	  $.ajax({
			 type:'post',
			 url:'cms/bike/checkBikeCode.action',
			 data:'bikeCode='+$("#bikecode").val()+'&lockCode='+$("#lockCode").val()+'&flag=1',
			 success:function(data){
				if(data=='fail'){
					$.alert("车辆编号已存在",true,function(){
		            },false,{className:'ui-alert',width:300}); 
				}else if(data=='failLock'){
					$.alert("锁编号不存在",true,function(){
		            },false,{className:'ui-alert',width:300}); 
				}else if(data=='failLockBike'){
					$.alert("该锁已绑定车辆",true,function(){
		            },false,{className:'ui-alert',width:300}); 
				}else if(data=='success'){
					$("#form").submit();
				}
			 }
		 });
  }
</script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">新增车辆</h1>
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
							<form id="form" role="form" action="cms/bike/addBike.action"  method="post">
							

								<label>车辆编号：</label>
								<div class="form-group">
									<input class="form-control" id="bikecode" placeholder="车辆编号" type="text" name="bikeCode">
								</div>
								<label>设备锁编号：</label>
								<div class="form-group">
									<input class="form-control" id="lockCode" placeholder="设备锁编号" type="text" name="lockCode">
								</div>
								<label>锁sim编号：</label>
								<div class="form-group">
									<input class="form-control" id="simCode" placeholder="锁sim编号" type="text" name="simCode">
								</div>
								<label>车型：</label>
								<div class="form-group">
									<select name="bikeModelsId" id="bikeModelsId" class="form-control" required="required">
										<c:forEach items="${modelsList }" var="models">
											<option value="${models.modelsId }">${models.modelsName }</option>
										</c:forEach>
									</select>
								</div>
								<label>站点：</label>
								<div class="form-group">
									<select name="bikeFixedReturnId" id="bikeFixedReturnId" class="form-control" required="required">
										<option value="0">暂不在站点</option>
										<c:forEach items="${fixedReturnList }" var="fixedReturn">
											<option value="${fixedReturn.fixedReturnId }">${fixedReturn.fixedReturnName }</option>
										</c:forEach>
									</select>
								</div>
								<label>批次号：</label>
								<div class="form-group">
									<select name="bikeBatchNumber" id="bikeBatchNumber" class="form-control" required="required">
										<c:forEach items="${bikeBatchNumberList }" var="batchNumber">
											<option value="${batchNumber.bikeBatchNumber }">${batchNumber.bikeBatchNumber }</option>
										</c:forEach>
									</select>
									<input type="hidden" id="hideBatchNumber" readonly="readonly" class="form-control" >
								</div>
								<div class="form-group">
								<input class="btn btn-success" id="batchButton" onclick="newBatch()" type="button" value="新建批次">
								</div>
								<label>负责人：</label>
								<div class="form-group">
								<input class="form-control" name="bikeManagerName" placeholder="负责人" type="text" id="bikeManagerName" required="required">
								</div>
								<label>联系电话：</label>
								<div class="form-group">
									<input class="form-control" placeholder="联系电话" type="text" id="bikeManagerTel" name="bikeManagerTel" required="required">
								</div>
								<label>投放时间：</label>
								<div class="form-group">
									<input class="form-control" readonly="readonly" placeholder="投放时间" type="text" id="cStarttime" name="bikePutTime" required="required">
								</div>
								<label>投放地点：</label>
								<div class="form-group">
									<input  class="form-control go-back" id="bikeAddress" name="bikeAddress" placeholder="投放地点" type="text" required="required">
								</div>
								<input type="hidden" name="putAddress" id="hideAddress">
								<div style="text-align: center;">
									<input type="button" value="提交" class="btn btn-primary" onclick="return check()">
								</div>
							</form>
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
									<button class="submit" value="提交" type="">确定</button>
									<button class="rentState" type="">取消</button>
								</div>
							</div>
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
</body>
</html>
