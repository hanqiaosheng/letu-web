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
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			_self=this;
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'bikeId='+obj,
					url:'cms/bike/deleteBike.action',
					success:function(data){
						if(data!="fail"){
							window.location.reload();
						}else{
							_self.hide();
							$.alert("改车辆绑定锁，无法删除");
						}
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
</script>
<script>
/* $(function(){
	var bachNumber = "${batchNumber}";
	if(bachNumber>0){
		$.alert("批次号为："+bachNumber+"<br>请相关人员记录",true,function(){
			window.location.href="${basePath}cms/bike/bikeList.action";
        },false,{className:'ui-alert',width:300}); 
	}
}) */
</script>
<script>
$(function(){
	var failFlag = "${failFlag}";
	if(failFlag==1){
		$.alert("车辆编号重复，导入失败",true,function(){
			window.location.href="${basePath}cms/bike/bikeList.action";
        },false,{className:'ui-alert',width:300});
	}
})
</script>
<script type="text/javascript">
  
	    function checkAll(){
	    	if($("#selectAll").is(':checked')){
	    		$(":checkbox").prop("checked",true);
	    	}else{
	    		$(":checkbox").prop("checked",false);
	    	}
	    }
	    function deleteAll(){
	    	var mess = "确认删除吗？";
			$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
				if(type=='yes'){
					$.ajax({
						type:'post',
						async:false,
						data:$('#bikeForm').serialize(),
						url:'cms/bike/deleteAllBike.action',
						success:function(data){
							window.location.reload();
						}
					});
					 
				}else if(type=='no'){
					this.hide();
				}
		    }) 
		    
	    }
	   
	    
	    function ok(){
	    	$("#bikeState").val($("#bikeStateSel").val());
	    	$.ajax({
				type:'post',
				data:$('#bikeForm').serialize(),
				url:'cms/bike/updateAllBike.action',
				success:function(data){	
						window.location.reload();
				}
			});
	    	
	    }
	    
	    function editAllJsp(){
	    	$("#bikeForm").attr("action","cms/bike/editAllJsp.action");
	    	$("#bikeForm").submit();
	    }
	    
	    
	    
    </script>
  <script type="text/javascript">
	$(document).ready(function() {
	  $("#bikeModelsName").select2();
	  $("#modelId").select2();
	  $("#channelidSelect").select2();
	  $("#bikeModelsId").select2();
	  //$("#fixedReturnId").select2();
	});
  </script>
  <script type="text/javascript">
   function adjustBike(){
	   $.isLoading.show('校准中...',true)
	   $.ajax({
			type:'post',
			data:$('#searchForm').serialize(),
			url:'cms/bike/checkBikeOfFixed.action',
			success:function(data){	
				$.isLoading.hide();
				if(data=="success"){
					$.alert("校准成功",true,function(){
						window.location.reload();
			        },false,{className:'ui-alert',width:300});
				}else{
					$.alert("校准失败",true,function(){
						window.location.reload();
			        },false,{className:'ui-alert',width:300});
				}
			}
		});
   }
  </script>
 
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">车辆列表</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					<div class="panel-heading clearfix ">
                        <div class="panel-body">
                        	<form id="importForm" action="cms/bike/importBike.action" method="post" enctype="multipart/form-data" 	>
						        	   <div class="col-md-1">
						        	   <shiro:hasPermission name="addBikeButton">
		                                  <a class="btn btn-success" href="cms/bike/addJsp.action">添加车辆</a>
		                               </shiro:hasPermission>
                                       </div>
                                       <div class="col-md-2">
	                                   <select id="bikeModelsId" class="form-control" name="bikeModelsId" required="required" onchange="getFixed(this.value)">
				                     		<option value="-1">请选择车型</option>
				                     		<c:forEach var="model" items="${modelList}">
			                   					<option value="${model.modelsId }">
			                     					${model.modelsName }
				                     			</option>
				                     		</c:forEach>
		                     	       </select>
                                       </div>
                                       <div class="col-md-2">
	                                  <select id="fixedReturnId" class="form-control" name="fixedReturnId" required="required">
				                     		<option value="0">请选择还车点</option>
				                     		<%-- <div id="fixedReturnIds">
				                     		<c:forEach var="fixedReturn" items="${fixedReturnList}">
			                   					<option value="${fixedReturn.fixedReturnId }">
			                     					${fixedReturn.fixedReturnName }
				                     			</option>
				                     		</c:forEach> 
				                     		</div> --%>
		                     	       </select> 
                                       </div>
                                       <div class="col-md-2">
		                               <input required="required" placeholder="投放地点" type="text" id="bikePutAddress" name="bikePutAddress" class="form-control go-back">
                                       </div>
                                       <div class="col-md-2">
		                               <input required="required" placeholder="投放时间" id="cStarttimeB" type="text" name="bikePutTime"  class="form-control">
                                       </div>
                                       <input type="hidden" name="bikeAddressText" id="bikeAddressText">
						        		<div class="inputfileli">
								        	<span class="inputfile">
								        	<shiro:hasPermission name="bikeUploadButton">
			                                	<button class="btn btn-default btn-primary" type="button" onclick="file.init($(this))">上传Excel</button>
			                                </shiro:hasPermission>	
			                                	<input type="file" name="excel" style="display:none" id="pinstructions">
			                                	
		                                	</span>
		                                	<shiro:hasPermission name="bikeInputButton">
	                                		<button class="btn btn-success" type="button" onclick=" return importBike()">录入车辆</button>
	                                		</shiro:hasPermission>
	                                	</div>
						       </form>
			        	</div>
                      </div>
				
					    <div class="panel-heading clearfix ">
                            <div class="row">
                                <div class="caption font-red-intense col-md-12">
                                    <i class="fa fa-search font-red-intense"></i>
                                    <span class="caption-subject bold uppercase">查询</span>
                                    <button class="btn btn-default hide-search pull-right" type="button">隐藏</button>
                                </div>
                            </div>
                            <br>
                            <div class="row tables-btn-box">
                            	<form action="cms/bike/bikeList.action" method="post" id="searchForm">
                                <input type="hidden" id="pageIndex" name="pageIndex">
                                <div class="col-md-2">
                                    <span class="">车辆编号</span>
                                   <input placeholder="车辆编号" type="text" name="bikeCode" value="${bike.bikeCode }" class="form-control">
                                </div>
                                
                                <div class="col-md-2">
                                <span class="">车型</span>
                                 <select id="modelId" name="modelsId"  class="form-control" onchange="getFixedReturn(this.value)">
								    <option value="-1">全部车型</option>
								    <c:forEach items="${modelList }" var="models">
									<option value="${models.modelsId }" <c:if test="${models.modelsId==modelsId }">selected="selected"</c:if>>${models.modelsName }</option>
								    </c:forEach>
								</select> 
								<%-- <input required="required" placeholder="车型" value="${modelsName }" type="text" name="modelsName"  class="form-control"> --%>
                                </div>
                                <div class="col-md-2">
                                  <span class="">还车点</span>
	                                 <select id="bikeFixedReturnId" class="form-control" name="bikeFixedReturnId">
				                     		<option value="-1">全部还车点</option>
				                     		<option value="0" <c:if test="${bike.bikeFixedReturnId==0 }">selected</c:if>>暂不在还车点</option>
		                     	     </select> 
                                </div>
                                <div class="col-md-2">
                                    <span class="">投放地点</span>
                                  <input placeholder="投放地点" type="text" name="bikeputAddress" value="${bikeputAddress }" class="form-control go-back">
                                </div>
								
                                <div class="col-md-2">
                                <span class="">车辆状态</span>
                                   <select name="bikeState" class="form-control">
								    <option value="-1"<c:if test="${bike.bikeState==-1 }">selected="selected"</c:if>>全部状态</option>
									<option value="0"<c:if test="${bike.bikeState==0 }">selected="selected"</c:if>>空闲中</option>
									<option value="1"<c:if test="${bike.bikeState==1 }">selected="selected"</c:if>>临时还车</option>
									<option value="2"<c:if test="${bike.bikeState==2 }">selected="selected"</c:if>>租借中</option>
									<option value="3"<c:if test="${bike.bikeState==3 }">selected="selected"</c:if>>维护中</option>
									<option value="4"<c:if test="${bike.bikeState==4 }">selected="selected"</c:if>>锁定中</option>
								</select>
                                </div>
                                <div class="col-md-2">
                                	<span class="">渠道</span>
                                   <select name="channelid" id="channelidSelect" class="form-control">
								    <option value="-1">全部渠道</option>
									<c:forEach items="${channels }" var="channel">
										<option value="${channel.channelId }"  <c:if test="${channel.channelId==channelid }">selected="selected"</c:if>>${channel.channelName }</option>
									</c:forEach>
									</select>
                                </div> 
                                <div class="col-md-2 time-box">
                                    <span class="tit">投放时间</span>
                                   	<input placeholder="投放时间" id="cStarttime" type="text" name="bikePutStartTime" value='<fmt:formatDate value="${bikePutStartTime }"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="投放时间" id="cEndtime" type="text" name="bikePutEndTime" value='<fmt:formatDate value="${bikePutEndTime }"/>' class="form-control">
                                </div>
                              	</form>
                            </div>
                             <shiro:hasPermission name="adjustBikeButton">
		                          <a class="btn btn-default pull-right search-btn btn-success" onclick="adjustBike()">校准车辆</a>
		                    </shiro:hasPermission>  
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
										    <th></th>
										    <th>车辆编号</th>
											<th>车型</th>
											<th>渠道名称</th>
											<th>设备锁号</th>
											<th>车锁电压</th>
											<th>批次号</th>
											<th>预付款</th>
											<th>站点</th>
											<th>状态</th>
											<th>定位</th>
											<th>车辆详情</th>
											<th>租赁详情</th>
											<th>维护详情</th>
											<th width="200px">操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="bike" items="${bikeList }">
											<tr class="odd gradeA">
											    <td>
											    <input type="checkbox" name="bikeIds" value="${bike.bikeId }">
											    </td>
											    <td>${bike.bikeCode }</td>
												<td>${bike.models.modelsName }</td>
												<td>${bike.models.channel.channelName }</td>
												<td>
													<a href="cms/lock/list.action?flag=1&bikeLockCode=${bike.bikeLock.bikeLockCode }">
														${bike.bikeLock.bikeLockCode }
													</a>
												</td>
												<td>
													<c:if test="${bike.bikeLock.bikeLockVoltage<3.5}"><span style="color:red;">${bike.bikeLock.bikeLockVoltage}V</span></c:if>
													<c:if test="${bike.bikeLock.bikeLockVoltage>=3.5}">${bike.bikeLock.bikeLockVoltage}V</c:if>
												</td>
												<td>${bike.bikeLock.bikeLockSimCode }</td>
												<td><fmt:formatNumber pattern="0.00" value="${bike.models.modelsDeposit }"/></td>
												<td>${bike.fixedReturn.fixedReturnName }</td>
												<td class="center">
												<c:if test="${bike.bikeState==0 }"><span style="color:green">空闲中</span></c:if>
												<c:if test="${bike.bikeState==1 }"><span style="color:orange;">临时还车</span></c:if>
												<c:if test="${bike.bikeState==2 }"><span style="color:orange">租借中</span></c:if>
												<c:if test="${bike.bikeState==3 }"><span style="color:red">维护中</span></c:if>
												<c:if test="${bike.bikeState==4 }"><span style="color:red">锁定中</span></c:if>
												<c:if test="${bike.bikeState==5 }"><span style="color:red">冻结中</span></c:if>
												</td>
												<td><a class="btn btn-info" href="cms/bike/bikePosition.action?bikeId=${bike.bikeId }">定位</a></td>
												<td><a class="btn btn-info" href="cms/bike/bikeDetail.action?bikeId=${bike.bikeId }">详情</a></td>
												<td><a class="btn btn-info" href="cms/bikeRentInfo/bikeRentInfoList.action?bikeId=${bike.bikeId }">详情</a></td>
												<td><a class="btn btn-info" href="cms/bikeFixInfo/bikeFixInfoDetailList.action?bikeId=${bike.bikeId }">详情</a></td>
												<td>
												<shiro:hasPermission name="bikeStateButton">
												<a class="btn btn-primary" href="cms/bike/updateBikeJsp.action?bikeId=${bike.bikeId }&bikeState=${bike.bikeState }">状态</a> 
												</shiro:hasPermission>
												<shiro:hasPermission name="bikeUpdateButton">
												<a class="btn btn-primary" href="cms/bike/editJsp.action?bikeId=${bike.bikeId }" >修改</a>
												</shiro:hasPermission>
												<shiro:hasPermission name="bikeDeleteButton">
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${bike.bikeId }')">删除</a>
												</shiro:hasPermission>
												</td>
											</tr>
									</c:forEach>
									</tbody>
								</table>
								</form>
								<input  type="checkbox" id="selectAll" onclick="checkAll()" />全选
								
								 <button data-toggle="modal" data-target="#groupsDel" data-to="#groupsDel"  type="button" class="btn btn-primary" >
								  批量设置</button>
								<!-- <button class="btn btn-danger" onclick="bikestateAll()">批量设置</button> -->
								<shiro:hasPermission name="bikeUpdateButton">
								<button class="btn btn-primary" type="button" onclick="editAllJsp();">批量修改</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="bikeDeleteButton">
								<button class="btn btn-danger" type="button" onclick="deleteAll();">批量删除</button>
								</shiro:hasPermission>
							</div>
						  
					<!---状态弹框-->
						<div aria-hidden="true"  style="display: none;" id="groupsDel" class="modal fade">
						  <div class="modal-dialog" style="width:600px; height:600px;margin:50px auto;">
						    <div class="modal-content">
						      <div class="modal-header" style="padding:0;">
						        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						        <h4 class="modal-title"  style="height:50px;line-height:50px;padding-left:30px;border-bottom:1px solid #f5f5f5;">车辆状态</h4>
						      </div>
						      <div class="modal-dialog" style="width:300px; height:130px;margin-bottom:0;padding-left:30px;">
						             <select id="bikeStateSel" class="form-control">
									 <option value="0">空闲中</option>
									 <option value="1">临时还车</option>
									 <option value="2">租借中</option>
									 <option value="3">维护中</option>
									 <option value="4">锁定中</option>
									 <option value="5">冻结中</option>
									</select>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default btn-xs" data-dismiss="modal">取消</button>
						        <a class="btn btn-primary btn-xs" onclick="ok()" href="javascript:void(0)" data-dismiss="modal">确定</a>
						      </div>
						    </div>
						  </div>
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

			//

			/* 		  var lnglatXY = ['${bike.bikeLongitude}','${bike.bikeAtitude}'];
			 lnglatXYTo(lnglatXY); */
			 getFixed($("#bikeModelsId").val());
			 getFixedReturn($("#modelId").val());

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
			if ($("#bikeModelsId").val() == '-1') {
				$.alert("请选择车型", true, function() {
				}, 5000, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}
		 	/* if ($("#fixedReturnId").val() == '-1') {
				$.alert("请选择站点", true, function() {
				}, 5000, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}  */
			if ($("#bikePutAddress").val() == '') {
				$.alert("请输入投放地址", true, function() {
				}, 5000, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}
			if ($("#cStarttimeB").val() == '') {
				$.alert("请输入投放时间", true, function() {
				}, 5000, {
					className : 'ui-alert',
					width : 300
				});
				return false;
			}
			$("#importForm").submit();

		}
		
		function getFixed(obj){
			$.ajax({
				type:'post',
				data:'modelsId='+obj,
				url:'cms/fixedReturn/getFixed.action',
				success:function(data){
					$("#fixedReturnId").html('<option value="0">请选择还车点</option>');
					var str = '';
					if(null!=data&&data!=''){
						 for(var index in data){
							 str += '<option value="'+data[index].fixedReturnId+'">'+data[index].fixedReturnName+'</option>';
						 }
						 $("#fixedReturnId").append(str); 
					}
					
				}
			});
		}
		
		function getFixedReturn(obj){
			var bikeFixedReturnId = '${bike.bikeFixedReturnId }';
			$.ajax({
				type:'post',
				data:'modelsId='+obj,
				url:'cms/fixedReturn/getFixed.action',
				success:function(data){
					if(bikeFixedReturnId==0&&bikeFixedReturnId!=""){
						$("#bikeFixedReturnId").html('<option value="-1">全部还车点</option><option value="0" selected>暂不在还车点</option>');
					}else{
						$("#bikeFixedReturnId").html('<option value="-1">全部还车点</option><option value="0">暂不在还车点</option>');
					}
					
					var str = '';
					if(null!=data&&data!=''){
						 for(var index in data){
							 if(bikeFixedReturnId==data[index].fixedReturnId ){
								 str += '<option value="'+data[index].fixedReturnId+'" selected>'+data[index].fixedReturnName+'</option>';
							 }else{
								 str += '<option value="'+data[index].fixedReturnId+'">'+data[index].fixedReturnName+'</option>';
							 }
							 
						 }
						 $("#bikeFixedReturnId").append(str); 
					}
					
				}
			});
		}
		
	</script>
	<%@include file="common/buttom.jsp"%>
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

