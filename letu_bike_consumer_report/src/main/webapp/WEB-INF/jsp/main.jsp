<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@include file="common/top.jsp" %>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript" src="datepicker/dateinput-ch-ZN.js"></script>
<script type="text/javascript" src="datepicker/jquery-ui.js"></script>
<script language="javascript" src="datepicker/jquery-ui-timepicker-addon.min.js"></script>  
<script language="javascript" src="datepicker/jquery-ui-timepicker-zh-CN.js"></script>  
<link href="datepicker/jquery-ui.css" rel="stylesheet">
<script type="text/javascript">

function checkAll() {
	if($("#checkAllBox").val()=='1'){
		$(":checkbox").prop("checked",true);
		$("#checkAllBox").val("0");
	}else if($("#checkAllBox").val()=='0'){
		$(":checkbox").prop("checked",false);
		$("#checkAllBox").val("1");
	}
}

$(function(){
	refund({parameter:4,boxId:'chartsbox5',unit:'人',titleName:'新增注册统计表',seriesName:'新增注册用户',target:$(this),url:'report/user/userreport.action'});
	
})
</script>
<script type="text/javascript">
function refund(obj){
	var timezq = $('#'+obj.boxId+'zq').val();
	
	var str1 = "";
	$("input[name='channelId']:checked").each(function(){
		str1 += $(this).val()+","; 
		})
		if(timezq==undefined){
			timezq = 4;
		}
	var fromtime = $('#'+obj.boxId+'fromtime').val()||''
	var totime = $('#'+obj.boxId+'totime').val()||''
	if(obj.fromToTime){
		var data='str='+str1+'&fromtime='+fromtime+'&totime='+totime
	}else{
		var data='str='+str1+'&time='+timezq
	}
	$("#getFromTime").val(fromtime);
	$("#getToTime").val(totime);
	$("#getStr").val(str1);
	$.ajax({
		type:'post',
		data:data,
		url:obj.url,
		success:function(data){
			if(obj.target==undefined){
		    	 if(obj.boxId=="chartsbox5"){
		    		$("#today5").html(data.todayCount);
		    	 }else if(obj.boxId=="chartsbox2"){
		    		 $("#today2").html(data.todayCount);
		    	 }else if(obj.boxId=="chartsbox6"){
		    		 $("#today6").html(data.todayCount);
		    	 }else if(obj.boxId=="chartsbox7"){
		    		 $("#today7").html(data.todaytotalMoney);
		    	 }else if(obj.boxId=="chartsbox8"){
		    		 $("#today8").html(data.todaysumMoney);
		    	 }else if(obj.boxId=="chartsbox9"){
		    		 $("#today9").html(data.todaysumMoney);
		    	 }else if(obj.boxId=="chartsbox10"){
		    		 $("#today10").html(data.toadysumBuchongMoney);
		    	 }else if(obj.boxId=="chartsbox11"){
		    		 $("#today11").html(data.toadysumBalanceMoney);
		    	 }else if(obj.boxId=="chartsbox12"){
		    		 $("#today12").html(data.todaysumRefundMoney);
		    	 }else if(obj.boxId=="chartsbox13"){
		    		 $("#today13").html(data.todayCount);
		    	 }else if(obj.boxId=="chartsbox14"){
		    		 $("#today14").html(data.todayCount);
		    	 }else if(obj.boxId=="chartsbox15"){
		    		 $("#today15").html(data.todayCount);
		    	 }else if(obj.boxId=="chartsbox16"){
		    		 $("#today16").html(data.todayCount);
		    	 }
			}
			
			if(obj.target){
				if($('#'+obj.boxId).length){
		    		 obj.target.removeAttr('on')
		    		 $('#'+obj.boxId).remove() 
		    		 return
		    	 }
		    	 obj.target.attr('on',true)
		    	 var str = '<div class="boxidbox" id="'+obj.boxId+'" data-url="'+obj.url+'" data-unit="'+obj.unit+'" data-titlename="'+obj.titleName+'" data-seriesname="'+obj.seriesName+'" >';
		    	 str += '<div class="selectdate clearfix"><div class="tit">时间条件选择：</div>';
		    	 str += '<ul class="clearfix"><li data-val="1">一周</li><li data-val="2">一月</li><li data-val="3">三月</li><li data-val="4">一年</li></ul>';
		    	 str += '<input id="'+obj.boxId+'zq'+'" type="hidden"/>';
		    	 str += '<div class="timerate"><input placeholder="开始时间" class="cStarttime" id="'+obj.boxId+'fromtime'+'" type="text" name="fromTime" value="${fromTime }"><span class="line">-</span><input placeholder="结束时间" class="cEndtime" id="'+obj.boxId+'totime'+'" type="text" name="toTime" value="${toTime }"><button class="fromToTimeGo">确定</button></div>';
		    	 if(obj.boxId=="chartsbox5"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today5">'+data.todayCount+'</div><div>新增注册用户</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalCount+'</div><div>注册用户总量</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="userRegistReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/user/list.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    	 }else if(obj.boxId=="chartsbox2"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today2">'+data.todayCount+'</div><div>新增租车用户数量</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalCount+'</div><div>总租车用户数量</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="newRentUserReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/user/newUserList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		 
		    	 }else if(obj.boxId=="chartsbox6"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today6">'+data.todayCount+'</div><div>新增骑行数量</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalCount+'</div><div>骑行总数量</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="bikeRentReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/bikeRentInfo/bikeRentInfoList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		 
		    	 }else if(obj.boxId=="chartsbox7"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today7">'+data.todaytotalMoney+'</div><div>今日收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalMoney+'</div><div>总收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.sumBuchongMoney+'</div><div>预付款收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.sumBalanceMoney+'</div><div>余额收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="totalMoneyReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/totalMoneyList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		 
		    	 }else if(obj.boxId=="chartsbox8"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today8">'+data.todaysumMoney+'</div><div>新增保险收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.sumMoney+'</div><div>保险总收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="insuranceReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/insuranceLogList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		 
		    	 }else if(obj.boxId=="chartsbox9"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today9">'+data.todaysumMoney+'</div><div>新增租赁消费</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.sumMoney+'</div><div>租赁消费总数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="rentConsumeReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/rentMoneyWater.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		 
		    	 }else if(obj.boxId=="chartsbox10"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today10">'+data.toadysumBuchongMoney+'</div><div>新增预付款充值</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.sumBuchongMoney+'</div><div>预付款充值总收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="depositReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/depositList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		 
		    	 }else if(obj.boxId=="chartsbox11"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today11">'+data.toadysumBalanceMoney+'</div><div>新增余额充值</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.sumBalanceMoney+'</div><div>余额充值总收益</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="balanceReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/balanceList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		 
		    	 }else if(obj.boxId=="chartsbox12"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today12">'+data.todaysumRefundMoney+'</div><div>新增预付款退款</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.sumRefundMoney+'</div><div>预付款退款总数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="deRefundReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/moneyLogList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		
		    	 }else if(obj.boxId=="chartsbox13"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today13">'+data.todayCount+'</div><div>当前订单数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalCount+'</div><div>总订单数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 //str += '<a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/moneyLogList.action"+"'"+')" href="">更多信息</a>';
		    		
		    	 }else if(obj.boxId=="chartsbox14"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today14">'+data.todayCount+'</div><div>当前还车数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalCount+'</div><div>总订单数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 //str += '<a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/moneyWater/moneyLogList.action"+"'"+')" href="">更多信息</a>';
		    		
		    	 }else if(obj.boxId=="chartsbox15"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today15">'+data.todayCount+'</div><div>当前订单数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalCount+'</div><div>总订单数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="bikeRentReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/bikeRentInfo/bikeRentInfoList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		
		    	 }else if(obj.boxId=="chartsbox16"){
		    		 str += '</div><div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge" id="today16">'+data.todayCount+'</div><div>当前订单数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<div class="col-lg-3 col-md-6"><div class="panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-xs-3"><i class="fa fa-comments fa-5x"></i></div>';
		    		 str += '<div class="col-xs-9 text-right"><div class="huge">'+data.totalCount+'</div><div>总订单数</div></div></div></div><div class="panel-footer"></div></div></div>';
		    		 str += '<shiro:hasPermission name="bikeRentReport"><a style="float:right" class="btn btn-info" id="'+obj.boxId+'gotoUrl'+'" onclick="gotoUrl('+"'"+obj.boxId+"',"+"'"+"report/bikeRentInfo/bikeRentInfoList.action"+"'"+')" href="">更多信息</a></shiro:hasPermission>';
		    		
		    	 }
		    	 str += '<div id="'+obj.boxId+'sub'+'"></div></div>';
		    	 $('.chartsbox').append(str);
		    	 getDate(obj.boxId);
			}
			
	    	 var categories=function(){
	    		 var arr=[]
	    		 $.each(data.jsonArray,function(i,item){
	    			 arr.push(item.period)
	    		 })
	    		 
	    		 return arr
	    		
	    	 }
	    	 var series=function(){
	    		 var arr=[]
	    		 $.each(data.jsonArray,function(i,item){
	    			 arr.push(item.increase)
	    		 })
	    		 return arr
	    	 }
	    	 var chart = new Highcharts.Chart(obj.boxId+'sub', {
	    		 chart: {
	    	            type: 'areaspline',
	    	            zoomType: 'x'	
	    	        },
	    		    title: {
	    		        text: obj.titleName,
	    		        x: -20
	    		    },
	    		    subtitle: {
	    		        text: '',
	    		        x: -20
	    		    },               
	    		    xAxis: {
	    		    	
	    		        categories: categories()
	    		        
	    		    },
	    		    yAxis: {
	    		        title: {
	    		            text: ''
	    		        },
	    		        allowDecimals: false,
	    		        plotLines: [{
	    		            value: 0,
	    		            width: 1,
	    		            color: '#808080'
	    		        }]
	    		    },
	    		    plotOptions: {
	    		    	areaspline: {
	                        fillColor: {
	                            linearGradient: {
	                                x1: 0,
	                                y1: 0,
	                                x2: 0,
	                                y2: 1
	                            },
	                            stops: [
	                                [0, Highcharts.getOptions().colors[0]],
	                                [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                            ]
	                        },
	                        marker: {
	                            radius: 2
	                        },
	                        lineWidth: 1,
	                        states: {
	                            hover: {
	                                lineWidth: 1
	                            }
	                        },
	                        threshold: null
	                    }
	                },
	    		    tooltip: {
	    		        valueSuffix: obj.unit
	    		    },
	    		    credits: {
	    		    	enabled: false
	    		    },
	    		    series: [{
	    		        name: obj.seriesName,
	    		        data: series()
	    		    }]
	    		});

		}
	})
}

function gotoUrl(boxid,url){
	$('#'+boxid+'gotoUrl').attr("href",url+'?str='+$("#getStr").val()+'&fromtime='+$("#getFromTime").val()+'&totime='+$("#getToTime").val());
	
}

function getDate(boxid){
	$('#'+boxid+'fromtime').datepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		yearRange:"-2:+10",
		onSelect:function(dateText,inst){
			$('#'+boxid+'totime').datepicker("option","minDate",dateText);
		}
		
	});
	$('#'+boxid+'totime').datepicker({
	changeMonth: true, 
	changeYear: true, 
	showMonthAfterYear: true,
	dateFormat: "yy-mm-dd",
	yearRange:"-2:+10",
	onSelect:function(dateText,inst){
       $('#'+boxid+'fromtime').datepicker("option","maxDate",dateText);
    }
});
}

</script>

 
 <script type="text/javascript">
 $(function(){
 		$('body').on({
 			'click':function(){
 				
 				$(this).parent().find('li').removeClass('on')
 				$(this).addClass('on')
 				$(this).parent().next().val($(this).data('val'))
 				var boxId=$(this).parents('.boxidbox').attr('id')
 				var url=$(this).parents('.boxidbox').data('url')
 				var unit=$(this).parents('.boxidbox').data('unit')
 				var titleName=$(this).parents('.boxidbox').data('titlename')
 				var seriesName=$(this).parents('.boxidbox').data('seriesname')
 				if($(this).hasClass('fromToTimeGo')){
 					if($('#'+boxId+'fromtime').val()==''){
						 alert("请选择开始时间!");
						 return;
					 }
 					 if($('#'+boxId+'totime').val()==''){
 						 alert("请选择结束时间!");
 						 return;
 					 }
 					$(this).parents('.selectdate').find('li').removeClass('on')
 					refund({parameter:4,boxId:boxId,unit:unit,titleName:titleName,seriesName:seriesName,url:url,fromToTime:true});
 				}else{
 					refund({parameter:4,boxId:boxId,unit:unit,titleName:titleName,seriesName:seriesName,url:url});
 				}
 				
 			}
 		},'.selectdate li,.fromToTimeGo')
 	})

    </script>
    
<body>
<%@include file="common/body.jsp" %>

<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">首页</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
			
				<div class="col-lg-12">
				
					<div class="panel panel-default">
						
						<div class="panel-heading clearfix ">
                            <div class="row">
                                <div class="caption font-red-intense col-md-12">
                                    <i class="fa fa-search font-red-intense"></i>
                                    
                                    <button class="btn btn-default hide-search pull-right" type="button">隐藏</button>
                                </div>
                            </div>
                            <br>
                           	<form action="report/user/newUserList.action" method="post" id="searchForm">
                               <input type="hidden" name="pageIndex" id="pageIndex">
                               <input type="hidden" id="exportFlag" name="flag">
                               <input type="hidden" id="getFromTime">
                               <input type="hidden" id="getToTime">
                               <input type="hidden" id="getStr">
                               <div class="row tables-btn-box">
                                <div class="col-md-10 channelselet">
                                   <span>渠道选择：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="checkAllBox" type="hidden" value="1" />
						            <input  type="checkbox"  onclick="checkAll();" />全选/全不选</span>
                                    <div class="checkedChannelId checkbox" style="margin-left: 30px">
                                     <c:forEach var="channel" items="${channelList }">
										<tr class="odd gradeA">
											<label><input type='checkbox' name='channelId' value="${channel.channelId}"> ${channel.channelName}</label>&nbsp;&nbsp;
										</tr>
								     </c:forEach>
                                    </div>
                                    
                                </div>
                               </div>
                            </form>
                           <div class="btnsbox">
                            <input type="button" on="true" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox5',unit:'人',titleName:'新增注册统计表',seriesName:'新增注册用户',target:$(this),url:'report/user/userreport.action'})" value="新增注册">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox2',unit:'人',titleName:'新增租车用户统计表',seriesName:'新增租车用户',target:$(this),url:'report/user/newuserreport.action'})" value="新增租车用户">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox6',unit:'次',titleName:'车辆骑行统计表',seriesName:'车辆骑行',target:$(this),url:'report/bikeRentInfo/bikereport.action'})" value="车辆骑行">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox7',unit:'元',titleName:'总收益统计表',seriesName:'总收益',target:$(this),url:'report/moneyWater/total.action'})" value="总收益">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox8',unit:'元',titleName:'保险收益统计表',seriesName:'保险收益',target:$(this),url:'report/moneyWater/insurance.action'})" value="保险收益">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox9',unit:'元',titleName:'租赁消费统计表',seriesName:'租赁消费',target:$(this),url:'report/moneyWater/rent.action'})" value="租赁消费">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox10',unit:'元',titleName:'预付款充值统计表',seriesName:'预付款充值',target:$(this),url:'report/moneyWater/deposit.action'})" value="预付款充值">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox11',unit:'元',titleName:'余额充值统计表',seriesName:'余额充值',target:$(this),url:'report/moneyWater/balance.action'})" value="余额充值">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox12',unit:'元',titleName:'预付款退款统计表',seriesName:'预付款退款',target:$(this),url:'report/moneyWater/refund.action'})" value="预付款退款">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox13',unit:'个',titleName:'订单数统计表',seriesName:'订单数',target:$(this),url:'report/bikeRentInfo/rentNumByFixed.action'})" value="订单数">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox14',unit:'个',titleName:'还车数统计表',seriesName:'还车数',target:$(this),url:'report/bikeRentInfo/returnNumByFixed.action'})" value="还车数">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox15',unit:'个',titleName:'骑行时间订单统计表',seriesName:'骑行时间订单数',target:$(this),url:'report/bikeRentInfo/rentNumByRideTime.action'})" value="骑行时间订单数">
                            <input type="button" class="changebtn" onclick="refund({parameter:4,boxId:'chartsbox16',unit:'个',titleName:'骑行时间订单统计表（细）',seriesName:'骑行时间订单数',target:$(this),url:'report/bikeRentInfo/rentDetailNumByRideTime.action'})" value="骑行时间订单数（细）">
                            </div>
                            
                       </div>
                      
						<!-- /.panel-heading -->
						<div class="panel-body chartsbox">

                        </div>
							
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>


    <script src="js/highcharts.js"></script>

   
</body>
<%@include file="common/buttom.jsp" %>
    
</html>
