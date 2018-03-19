<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">还车点信息详情</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
				
					<div class="row">
					
						<div class="panel-body form-horizontal">
						<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">还车点名字：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${fixedReturn.fixedReturnName  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">渠道名：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${channel.channelName  }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">简介：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${fixedReturn.fixedReturnBrief }</p>
								</div>
							</div>
						   <div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">管理员号码：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${fixedReturn.fixedReturnTel }</p>
								</div>
							</div>
							
							<c:forEach items="${fixedReturn.models }" var="models">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">车型：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${models.modelsName }</p>
								</div>
							</div>
							
							<c:forEach var="price" items="${models.modelRentPriceList }">
							
							<div class="form-group">
							<c:if test="${price.rentPriceType==1 }">
							<label for="inputEmail3" class="col-sm-2 control-label">游客租金：</label>
							</c:if>
							<c:if test="${price.rentPriceType==2 }">
							<label for="inputEmail3" class="col-sm-2 control-label">会员租金：</label>
							</c:if>
							<div class="col-sm-10">
							<div class="end-time">免费时段 :  ${price.rentFreeTime }分钟</div>
							<c:forEach var="rprice" items="${price.priceList }">
							  <c:if test="${price.rentPriceOption==1 }">
						        <div class="inputfileli">
								       <span class="inputfile">
			                                	时段:&nbsp;&nbsp;${rprice.fromTime }~${rprice.toTime }小时
			                                	&nbsp;&nbsp;&nbsp;&nbsp;${rprice.rentPrice }元/小时</span>
	                            </div>		
	                          </c:if> 
	                          <c:if test="${price.rentPriceOption==2 }">
						        <div class="inputfileli">
								       <span class="inputfile">
			                                	时段:&nbsp;&nbsp;${rprice.fromTime }~${rprice.toTime }个半小时
			                                	&nbsp;&nbsp;&nbsp;&nbsp;${rprice.rentPrice }元/半小时</span>
	                            </div>		
	                          </c:if>      	
	                        </c:forEach>
	                         <c:if test="${price.rentPriceOption==1 }">
		                        <div class="end-time">最后时段 : ${price.lastPrice }元/小时
		                        </div>
		                     </c:if>
		                      <c:if test="${price.rentPriceOption==2 }">
		                        <div class="end-time">最后时段 : ${price.lastPrice }元/半小时
		                        </div>
		                     </c:if>
	                            </div>
	                         </div>
	                         </c:forEach>
	                         
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">预付款：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${models.modelsDeposit }</p>
								</div>
							</div>
							</c:forEach>
							
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">还车点位置：</label>
								<div class="col-sm-10">
									<p class="form-control-static"><a href="cms/fixedReturn/fixedPosition.action?fixedReturnId=${fixedReturn.fixedReturnId }"><span id="lnglatstr"></span></a></p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">还车点范围距离：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${fixedReturn.fixedReturnDistance }米</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">可用车数量：</label>
								<div class="col-sm-10">
									<p class="form-control-static">${fixedReturn.fixedReturnBikeNum }辆</p>
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">还车点介绍内容：</label>
								<div class="col-sm-6">
									<p class="form-control-static">
									  <script id="editor" type="text/plain" name="fixedReturnContent"
											style="height: 400px;"></script>
									</p>
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
<%@include file="../common/buttom.jsp"%>
<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
	 AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
		    //实例化Geocoder
		    geocoder = new AMap.Geocoder({
		    });
		    //TODO: 使用geocoder 对象完成相关功能
		  })
		  
		  //
		  var lnglatXY = ['${fixedReturn.fixedReturnLng }','${fixedReturn.fixedReturnLat }'];
	      lnglatXYTo(lnglatXY);
		 
	}); 
   function lnglatXYTo(obj){
	   var lnglatXY = obj;
	   geocoder.getAddress(lnglatXY, function(status, result) {
		      if (status === 'complete' && result.info === 'OK') {
		           //获得了有效的地址信息:
		           address=result.regeocode.formattedAddress;//.split("市")[1];
		           address=address.substring(3,address.length);
		           $("#lnglatstr").html(address);

		      }else{
		            //获取地址失败
		      }
		    }); 
   } 
</script>

<script type="text/javascript">
	var ue = UE.getEditor('editor');
	ue.ready(function() {
	    ue.setContent('${fixedReturn.fixedReturnContent}', true);
	});
	</script>
</html>
