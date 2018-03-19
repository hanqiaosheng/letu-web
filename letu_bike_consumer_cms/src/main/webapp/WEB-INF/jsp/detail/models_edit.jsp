<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" type="text/css" href="datepicker/jquery.datetimepicker.css"/>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<body>
<%@include file="../common/body.jsp"%>



<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">修改车型</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/models/editModels.action" id="modelsForm" method="post">
							    <input type="hidden" name="modelsId" value="${models.modelsId }">
							    <label>型号：</label>
							    <div class="form-group">
									<input class="form-control" value="${models.modelsCode }" type="text" id="modelsCode" name="modelsCode" required="required">
								</div>
							    <br>
							    <label>是否定点：</label>
								<input type="radio"<c:if test="${models.modelsIsfixedPoint==1 }">checked="checked"</c:if> name="modelsIsfixedPoint" value="1" onclick="checkIsFixed(1)"> 是
							    <input type="radio"<c:if test="${models.modelsIsfixedPoint==0 }">checked="checked"</c:if> name="modelsIsfixedPoint" value="0" onclick="checkIsFixed(0)"> 否
							    <br>
							    <div id="fixed" style="display: none;">
							    <input type="button" class="btn btn-primary" value="新建定点" onclick="newFixed()">
							    <br>
							    <c:forEach items="${models.fixedReturnList }" var="fixed" varStatus="varStatus">
							    <div class="newFixedReturn${varStatus.index+1 }">
							    <label>还车点名称：<input type="button" class="btn btn-danger" value="删除" onclick="cleanFixed('${fixed.fixedReturnId}','${varStatus.index+1 }','${models.modelsId}')"></label>
							    <select name="fixedReturnId" class="form-control fixedname">
								<c:forEach  items="${fixedReturns }" var="fixedReturn">
									<option value="${fixedReturn.fixedReturnId }"  <c:if test="${fixedReturn.fixedReturnId==fixed.fixedReturnId }">selected="selected"</c:if>>${fixedReturn.fixedReturnName }</option>
								</c:forEach>
								</select>
							    <br>
							    </div>
							    </c:forEach>
							    <div id="newFixedReturn">
							    
							    
							    </div>
							    </div>
							    <br>
							    <label>车型名：</label>
							    <div class="form-group">
									<input class="form-control" value="${models.modelsName }" type="text" id="modelsName" name="modelsName" required="required">
								</div>
								
							    <label>保险类别：</label>
							    <div class="form-group">
							        <select id="modelsInpriceId" class="form-control" name="modelsInpriceId" required="required" onchange="getInPrice(this.value)">
				                     	<c:forEach var="insurancePrice" items="${insurancePrices}">
			                   				<option value="${insurancePrice.inPriceId }" <c:if test="${insurancePrice.inPriceId==models.modelsInpriceId }">selected</c:if>>
			                   				${insurancePrice.inPriceName }
				                     		</option>
				                     	</c:forEach>
		                     	    </select> 
		                     	    <span style="color: red;" id="insurancePrice"></span>
								</div>
							    <br>
							    <label>预付款：</label>
								<div class="form-group">
									<input class="form-control" value="${models.modelsDeposit }" type="text" id="modelsDeposit" name="modelsDeposit" required="required">
								</div>
							    <br>
							    
							    <label>渠道名 ：</label>
							    <select id="channelVal" class="form-control" name="modelsChannelId" >
							    <c:forEach var="channel" items="${channelList }">
							    <option value="${channel.channelId }"<c:if test="${channel.channelId==models.modelsChannelId}">selected="selected"</c:if>>${channel.channelName }</option>
							    </c:forEach>
							    </select>
							    <br>
							    <label>简介：</label>
								<div class="form-group">
									<input class="form-control" value="${models.modelsContent }" type="text" name="modelsContent">
								</div>
							    <br>
							    
							    <label>车辆限制：</label>
							    <input type="radio" <c:if test="${models.modelsRentLimit==1 }">checked="checked"</c:if> name="modelsRentLimit" value="1"> 不限制车辆使用
							    <input type="radio" <c:if test="${models.modelsRentLimit==2 }">checked="checked"</c:if> name="modelsRentLimit" value="2"> 仅限会员使用
							    <br>
							    
							    <label>租赁费用：</label>
							    <input type="radio" <c:if test="${models.modelsRentType==1 }">checked="checked"</c:if> name="modelsRentType" value="1" onclick="isneedTo(1)"> 不需要会员计费
							    <input type="radio" <c:if test="${models.modelsRentType==2 }">checked="checked"</c:if> name="modelsRentType" value="2" onclick="isneedTo(2)"> 需要会员计费
							    <br><br>
							    <c:forEach var="price" items="${prices }">
							    <c:if test="${price.rentPriceType==1 }">
							    <label>游客费用：</label>
							    <div class="form-group">
					        	<div class="panel panel-default">
						        	<div class="panel-heading clearfix ">
						        		<button class="btn btn-default btn-success btn-sm addbtn" type="button" onclick="file.addNode($('.inputfileli:last'))">添加</button>
						        	</div>
						        	<div class="panel-body form-inline">
						        	<div class="end-time">
	                                	计费规则选择：
	                                		<select id="rentoption" class="form-control" name="rentoption" onchange="toCount(this.value)">
	                                		 <option value="1" <c:if test="${price.rentPriceOption==1 }">selected</c:if>>按一小时计费</option>
	                                		 <option value="2" <c:if test="${price.rentPriceOption==2 }">selected</c:if>>按半小时计费</option>
	                                		</select>
	                                </div><br>
						        	 <div class="end-time">免费时段
	                                		<input class="form-control" type="text" onkeyup="value=value.replace(/[^\d]/ig,'')" id="rentFreeTime" name="rentFreeTime" required="required" value="${price.rentFreeTime }">
	                                	分钟
	                                	</div><br>
						        	  <c:forEach var="rprice" items="${price.priceList }">
						        		<div class="inputfileli">
								        	<span class="inputfile">
			                                	<label>分段计费</label><input class="form-control" type="text" name="fromTime" required="required" value="${rprice.fromTime }">~<input class="form-control" type="text" name="toTime" required="required" value="${rprice.toTime }"><span class="timeunit">小时</span>
			                                	&nbsp;&nbsp;&nbsp;&nbsp;需<input class="form-control" type="text" name="rentPrice" required="required" value="${rprice.rentPrice }"><span class="unit">元/小时</span></span><button class="btn btn-default btn-danger btn-xs" type="button" id="firstnode" style="font-size:14px; margin-left:20px;" onclick="file.subNode($(this))">删除</button>
	                                	</div>
	                                	</c:forEach> 
	                                	<div id="add-list"></div>
	                                	<div class="end-time">
	                                	最后时段
	                                		<input class="form-control" id="lastPrice" type="text" name="rentPrice" required="required" value="${price.lastPrice }">
	                                	<span class="unit">元/小时</span><span class="text-danger">（超出设置时间后的计费规则）</span>
	                                	</div>  
	                                	<div class="end-time">
	                                	封顶费用：
	                                		<input id="rentPriceMax" class="form-control" value="${price.rentPriceMax }" type="text" name="rentPriceMax" required="required">
	                                	元/天
	                                	</div>
						        	</div>
                                </div>
								</div>
							    <br>
							    </c:if> 
							    <c:if test="${price.rentPriceType==2 }">
							    <div id="showOrHide" style="display: none;">
							    <label>会员费用：</label>
							    <div class="form-group">
					        	<div class="panel panel-default">
						        	<div class="panel-heading clearfix ">
						        		<button class="btn btn-default btn-success btn-sm addbtn" type="button" onclick="villager.addNode($('.inputfileli2:last'))">添加</button>
						        	</div>
						        	<div class="panel-body form-inline">
						        	<div class="end-time">
	                                	计费规则选择：
	                                		<select id="rentoption2" class="form-control" name="rentoption2" onchange="toCount2(this.value)">
	                                		 <option value="1" <c:if test="${price.rentPriceOption==1 }">selected</c:if>>按一小时计费</option>
	                                		 <option value="2" <c:if test="${price.rentPriceOption==2 }">selected</c:if>>按半小时计费</option>
	                                		</select>
	                                </div><br>
						        	<div class="end-time">免费时段
	                                		<input class="form-control" type="text" onkeyup="value=value.replace(/[^\d]/ig,'')" id="rentFreeTime2" name="rentFreeTime2" required="required" value="${price.rentFreeTime }">
	                                	分钟
	                                	</div><br>
						        	  <c:forEach var="rprice" items="${price.priceList }">
						        		<div class="inputfileli2">
								        	<span class="inputfile">
			                                	<label>分段计费</label><input class="form-control" type="text" name="fromTime2" required="required" value="${rprice.fromTime }">~<input class="form-control" type="text" name="toTime2" required="required" value="${rprice.toTime }"><span class="timeunit2">小时</span>
			                                	&nbsp;&nbsp;&nbsp;&nbsp;需<input class="form-control" type="text" name="rentPrice2" required="required" value="${rprice.rentPrice }"><span class="unit2">元/小时</span></span><button class="btn btn-default btn-danger btn-xs" type="button" id="firstnode" style="font-size:14px; margin-left:20px;" onclick="villager.subNode($(this))">删除</button>
	                                	</div>
	                                	</c:forEach>
	                                	<div id="add-list2"></div>
	                                	<div class="end-time">
	                                	最后时段
	                                		<input class="form-control" id="lastPrice2" type="text" name="rentPrice2" required="required" value="${price.lastPrice }">
	                                	<span class="unit2">元/小时</span><span class="text-danger">（超出设置时间后的计费规则）</span>
	                                	</div>
	                                	<div class="end-time">
	                                	封顶费用：
	                                		<input id="rentPriceMax2" class="form-control" type="text" value="${price.rentPriceMax }" name="rentPriceMax2" required="required">
	                                	元/天
	                                	</div>
						        	</div>
                                </div>
								</div>
							    <br>
							    </div>
							    </c:if>
							    </c:forEach>
							    <c:if test="${prices.size()==1 }">
							    <div id="showOrHide" style="display: none;">
							    <label>会员费用：</label>
							    <div class="form-group">
					        	<div class="panel panel-default">
						        	<div class="panel-heading clearfix ">
						        		<button class="btn btn-default btn-success btn-sm addbtn" type="button" onclick="villager.addNode($('.inputfileli2:last'))">添加</button>
						        	</div>
						        	<div class="panel-body form-inline">
						        	<div class="end-time">
	                                	计费规则选择：
	                                		<select id="rentoption2" class="form-control" name="rentoption2" onchange="toCount2(this.value)">
	                                		 <option value="1" <c:if test="${price.rentPriceOption==1 }">selected</c:if>>按一小时计费</option>
	                                		 <option value="2" <c:if test="${price.rentPriceOption==2 }">selected</c:if>>按半小时计费</option>
	                                		</select>
	                                </div><br>
						        	<div class="end-time">免费时段
	                                		<input class="form-control" type="text" onkeyup="value=value.replace(/[^\d]/ig,'')" id="rentFreeTime2" name="rentFreeTime2" required="required" value="${price.rentFreeTime }">
	                                	分钟
	                                	</div><br>
						        	  <c:forEach var="rprice" items="${price.priceList }">
						        		<div class="inputfileli2">
								        	<span class="inputfile">
			                                	<label>分段计费</label><input class="form-control" type="text" name="fromTime2" required="required" value="${rprice.fromTime }">~<input class="form-control" type="text" name="toTime2" required="required" value="${rprice.toTime }"><span class="timeunit2">小时</span>
			                                	&nbsp;&nbsp;&nbsp;&nbsp;需<input class="form-control" type="text" name="rentPrice2" required="required" value="${rprice.rentPrice }"><span class="unit2">元/小时</span></span><button class="btn btn-default btn-danger btn-xs" type="button" id="firstnode" style="font-size:14px; margin-left:20px;" onclick="villager.subNode($(this))">删除</button>
	                                	</div>
	                                	</c:forEach>
	                                	<div id="add-list2"></div>
	                                	<div class="end-time">
	                                	最后时段
	                                		<input class="form-control" id="lastPrice2" type="text" name="rentPrice2" required="required" value="${price.lastPrice }">
	                                	<span class="unit2">元/小时</span><span class="text-danger">（超出设置时间后的计费规则）</span>
	                                	</div>
	                                	<div class="end-time">
	                                	封顶费用：
	                                		<input id="rentPriceMax2" class="form-control" type="text" value="${price.rentPriceMax }" name="rentPriceMax2" required="required">
	                                	元/天
	                                	</div>
						        	</div>
                                </div>
								</div>
							    <br>
							    </div>
							   </c:if>
								
								<div style="text-align: center;">
									<input type="button" value="修改" class="btn btn-primary" onclick="checkTime()">
									<a class="btn btn-danger" href="javascript:history.go(-1)">取消</a>
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
<script type="text/javascript">
  $(function(){
	 var modelsIsfixedPoint = '${models.modelsIsfixedPoint}';
	 checkIsFixed(modelsIsfixedPoint);
  })
</script>
<script type="text/javascript">
$(document).on({
	keyup:function(){
		this.value=this.value.replace(/[^\d]/ig,'')
		var n=$(this).next().val()
		if(n==''){return}
		Number($(this).val())<Number(n)?'':this.value=Number(n)-1
		

	}
},'[name="fromTime"]');
$(document).on({
	keyup:function(){
		this.value=this.value.replace(/[^\d]/ig,'')
		var n=$(this).prev().val()
		if(n==''){return}
		Number($(this).val())>Number(n)?'':this.value=Number(n)+1
	}
},'[name="toTime"]');

$(document).on({
	keyup:function(){
		this.value=this.value.replace(/[^\d]/ig,'')
		var n=$(this).next().val()
		if(n==''){return}
		Number($(this).val())<Number(n)?'':this.value=Number(n)-1
		

	}
},'[name="fromTime2"]');
$(document).on({
	keyup:function(){
		this.value=this.value.replace(/[^\d]/ig,'')
		var n=$(this).prev().val()
		if(n==''){return}
		Number($(this).val())>Number(n)?'':this.value=Number(n)+1
	}
},'[name="toTime2"]');

var file={
		init:function(obj){
			obj.next().trigger('click').change(function(){
				var str=$(this).val();
				var filename=str.substring(str.lastIndexOf("\\")+1);
			});
			
		},
		addNode:function(obj){
			
			$('#add-list').append('<div class="inputfileli">'+
		        	'<span class="inputfile">'+
                	'<label>分段计费：</label>'+
                      '<input class="form-control" id="fromTime type="text" name="fromTime" required="required">~'+
                      '<input class="form-control" id="toTime" type="text" name="toTime" required="required"><span class="timeunit">小时</span>  '+
                      '&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-control" type="text" name="rentPrice" required="required"><span class="unit">元/小时</span>'+
            	'</span>'+
            	'<button class="btn btn-default btn-danger btn-xs" type="button" id="firstnode" style="font-size:14px; margin-left:20px;" onclick="file.subNode($(this))">删除</button>'+
        	'</div>')	
        	
        	toCount($("#rentoption").val());
		},
		subNode:function(obj){
			obj.parent().remove()
		}
	}
</script>
<script type="text/javascript">
 function checkTime(){
	 var all=true,flag=true,priceFlag=true,all2=true,flag2=true,priceFlag2=true;
	 if($("#modelsCode").val()==""){
		$.alert('型号不能为空')
		return
	 }
	 
	 if($("#modelsName").val()==""){
			$.alert('车型名不能为空')
			return
		}
	 if($("#modelsDeposit").val()==""){
			$.alert('预付款不能为空')
			return
		 }
	 if($("#rentFreeTime").val()==""){
			$.alert('免费时段不能为空')
			return
		 }
	 if($("#lastPrice").val()==""){
			$.alert('最后时段费用不能为空')
			return
		 }
	 if($("#rentPriceMax").val()==""){
			$.alert('封顶费用不能为空')
			return
		 }
		 
	$.each($('.inputfileli'),function(i){
		var val1=Number($(this).find('input').eq(0).val());
		var val2=Number($(this).find('input').eq(1).val());
		var val3=$(this).find('input').eq(2).val();
		var nextval=Number($(this).next().find('input').eq(0).val()||0)
		if(nextval&&val2!=nextval){
			flag=false
		}
		if(val2<=val1){
			all=false
		}
		if(val3==""){
			priceFlag=false
		}
	})
	if($('[name="fromTime"]').eq(0).val()!=undefined){
		if($('[name="fromTime"]').eq(0).val()!=0){$.alert('开始时间必须为0');return}
	}
	
	if(!all){
		$.alert('结束时间不能小于开始时间')
		return
	}
	if(!flag){
		$.alert('相邻时间段的结束时间和开始时间要相等')
		return
	}
	if(!priceFlag){
		$.alert('费用不能为空')
		return
	}
	if($("#showOrHide").css('display')!='none'){
		 if($("#rentFreeTime2").val()==""){
				$.alert('免费时段不能为空')
				return
			 }
		 if($("#lastPrice2").val()==""){
				$.alert('最后时段费用不能为空')
				return
			 }
		 if($("#rentPriceMax2").val()==""){
				$.alert('封顶费用不能为空')
				return
			 }
		$.each($('.inputfileli2'),function(i){
			var val1=Number($(this).find('input').eq(0).val());
			var val2=Number($(this).find('input').eq(1).val());
			var val3=$(this).find('input').eq(2).val();
			var nextval=Number($(this).next().find('input').eq(0).val()||0)
			if(nextval&&val2!=nextval){
				flag2=false
			}
			if(val2<=val1){
				all2=false
			}
			if(val3==""){
				priceFlag2=false
			}
		})
		if($('[name="fromTime2"]').eq(0).val()!=undefined){
			if($('[name="fromTime2"]').eq(0).val()!=0){$.alert('开始时间必须为0');return}
		}
		
		
		if(!all2){
			$.alert('结束时间不能小于开始时间')
			return
		}
		if(!flag2){
			$.alert('相邻时间段的结束时间和开始时间要相等')
			return
		}
		if(!priceFlag2){
			$.alert('费用不能为空')
			return
		}
	}
	$("#modelsForm").submit();
	 
 }
</script>
<script type="text/javascript">
  var count = $(".fixedname").size();
  function newFixed(){
	 count+=1;
	  var str =  '<div class="newFixedReturn'+count+'">'+
	     ' <label>还车点名称：</label><input type="button" class="btn btn-danger" value="删除" onclick="removeFixed('+count+')"><select name="fixedReturnId" class="form-control">';
	    
		  <c:forEach var="fixedReturn" items="${fixedReturns }">
		     str += '<option value='+"${fixedReturn.fixedReturnId}"+'>'+"${fixedReturn.fixedReturnName}"+'</option>';
		  </c:forEach>
	     str += '</select><br></div>'
	    
	 $("#newFixedReturn").append(str);
	 checkIsFixed(1);
  }
  
  function cleanFixed(objA,objB,objC){
	  $.ajax({
		  type:'post',
		  url:'cms/models/deleteFixed.action',
		  data:'fixedReturnId='+objA+'&modelsId='+objC,
		  success:function(data){
			  var tip = ".newFixedReturn"+objB;
			  $(tip).remove();
		  }
	  })
  }
  
  
</script>
<script type="text/javascript">
function removeFixed(obj){
	var tip = ".newFixedReturn"+obj;
	 $(tip).remove();
}
</script>
<script type="text/javascript">
  function checkIsFixed(obj){
	  if(obj==1){
		  $("#fixed").show();
	  }else if(obj==0){
		 $("#fixed").hide();
	  }
  }
</script>
<script type="text/javascript">



$(function(){
	var id = $("#modelsInpriceId").val();
	getInPrice(id);
})

function getInPrice(obj){
	  $.ajax({
			type:'post',
			data:'inPriceId='+obj,
			url:'cms/insurancePrice/getInPrice.action',
			success:function(data){
				if(null!=data&&data!=""&&data.inPrice!=""&&data.inPrice!=null){
					  $("#insurancePrice").html("该类别的保险费用为"+data.inPrice+"元");
				}else{
					$("#insurancePrice").html("");
				}
			}
	   });
}
</script>
<script type="text/javascript">
   $(function(){
	   toCount($("#rentoption").val());
	   toCount2($("#rentoption2").val());
	   isneedTo($("[name='modelsRentType']:checked").val());
   })
</script>
<script type="text/javascript">
   function isneedTo(obj){
	   if(obj==1){
		   $("#showOrHide").hide();
	   }else if(obj==2){
		   $("#showOrHide").show();
	   }
   }
   
   var villager={
			init:function(obj){
				obj.next().trigger('click').change(function(){
					var str=$(this).val();
					var filename=str.substring(str.lastIndexOf("\\")+1);
				});
				
			},
			addNode:function(obj){
				
				$('#add-list2').append('<div class="inputfileli2">'+
			        	'<span class="inputfile">'+
	                	'<label>分段计费：</label>'+   
	                      '<input class="form-control" id="fromTime2" type="text" name="fromTime2" required="required">~'+
	                      '<input class="form-control" id="toTime2" type="text" name="toTime2" required="required"><span class="timeunit2">小时</span>  '+
	                      '&nbsp;&nbsp;&nbsp;&nbsp;<input class="form-control" type="text" name="rentPrice2" required="required"><span class="unit2">元/小时</span>'+
	            	'</span>'+
	            	'<button class="btn btn-default btn-danger btn-xs" type="button" id="firstnode" style="font-size:14px; margin-left:20px;" onclick="villager.subNode($(this))">删除</button>'+
	        	'</div>')	
	        	
	        	toCount2($("#rentoption2").val());
			},
			subNode:function(obj){
				obj.parent().remove()
			}
		}
   
   function toCount(obj){
	   if(obj==1){
		   $(".unit").html("元/小时");
		   $(".timeunit").html("小时");
	   }else if(obj==2){
		   $(".unit").html("元/半小时");
		   $(".timeunit").html("个半小时");
	   }
	   
	  
   }
   function toCount2(obj){
	   if(obj==1){
		   $(".unit2").html("元/小时");
		   $(".timeunit2").html("小时");
	   }else if(obj==2){
		   $(".unit2").html("元/半小时");
		   $(".timeunit2").html("个半小时");
	   }
	   
	  
   }
</script>
<%@include file="../common/buttom.jsp"%>
<%@include file="../common/maskMap.jsp"%>
<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#channelVal").select2();
    })
</script>
</body>
</html>
