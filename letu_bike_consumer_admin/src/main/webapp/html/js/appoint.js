//var host = window.location.host;

var host = window.location.host+url; //测试
$(document).ready(function(){ 
$("#appointBtn").click(function(){
//	var geos=geoPoint.toString().split(',');
//	var lnglat = new AMap.LngLat(geos[0],geos[1]);
//	if(lnglat.distance(lastMarker.getPosition())>500){
//	}
	 $.ajax({
		 	type:"post",
			url:'http://'+host+'/user/getUser.action',
			dataType:'json',
			success:function(data){
				if(null == data.user){
					window.location.href ='http://'+host+"/html/regist.html";
		    	}else if(0==data.account.accountDeposit){
		    		window.location.href ='http://'+host+"/html/deposit.html";
		    	}else if(null == data.user.userIdcard){
		    		window.location.href ='http://'+host+"/html/realName.html";
		    	}else if(null == data.user.userInviteCode){
		    		window.location.href ='http://'+host+"/html/registSuccess.html";
		    	}else{
		    		appoint();
		    	}
				},
				error:function(data){
					reLogin(data);
				}
			});
	
	
	
});


$("#cancleBtn").click(function(){
	$.isLoading.show('取消中...');
	var bikeId = $("#appBikeId").val();
	var appointId = $("#appointId").val();
	var userId = $("#userId").val();
	var dataStr ="bikeId="+bikeId+"&appointId="+appointId+"&userId="+userId;
	 $.ajax({
		 	type:"post",
		 	url:'http://'+host+'/appoint/appointCancel.action',
		 	data:dataStr,
			dataType:'json',
			success:function(data){
				cancel();
				$.isLoading.hide();
				document.getElementById("centerPoint").style.display="none";
				$.alert(data.message,true,function(){
	             },5000,{className:'ui-alert',width:300});
				$('#appointNow').show();
				$('#appointing').hide();
				clickListener=new AMap.event.addListener(map, "click", cancel);
				dragListener=new AMap.event.addListener(map, "moveend", _moveend);
				},
				error:function(data){
					reLogin(data);
				}
			});
	
	
	
});






});
//预约
function appoint(){
	$.isLoading.show('预约中...');
	var bikeId = $("#appBikeId").val();
	var appointTime = $("#exc-number").val();
	var appointmentPlanId = $("#planId").val();
	var dataStr ="bikeId="+bikeId+"&appointmentTime="+appointTime+"&appointmentPlanId="+appointmentPlanId;
	 $.ajax({
		 	type:"post",
			url:'http://'+host+'/appoint/appointing.action',
			data:dataStr,
			dataType:'json',
			success:function(data){
				$.isLoading.hide();
				if(0==data.code){
					$.alert(data.message,false,function(){
		             },3000,{className:'ui-alert',width:300});
				}else if(1==data.code){
  					$.alert("预约成功！",false,function(){
		             },3000,{className:'ui-alert',width:300});
					$('#appointNow').hide();
					$('#appointing').show();
					$("#appointId").val(data.data.appointmentId);
					AMap.event.removeListener(dragListener);
					AMap.event.removeListener(clickListener);
				}else if(3==data.code){
					document.getElementById("centerPoint").style.display="none";
					$.alert(data.message,true,function(){
						window.location.href=url+"/html/bikeEnd.html?rentId="+data.data.bikeRentInfo.rentInfoId+"&max="+data.data.maxFreeMoeny;
		             },5000,{className:'ui-alert',width:300});
				}else if(4==data.code){
					cancel();
					document.getElementById("centerPoint").style.display="none";
					$.alert(data.message,true,function(){
						AMap.event.removeListener(clickListener);
						AMap.event.removeListener(dragListener);
						 var icon = new AMap.Icon({
					          image: '/images/bike.png',
					          map:map,
					          size: new AMap.Size(50, 56.1),
					          imageSize: new AMap.Size(50, 56.1)
					        });
						 var marker = new AMap.Marker({
	                         position: [data.data.bike.bikeLongitude,data.data.bike.bikeAtitude],
	                         icon: icon,
	                         offset: {x: -22.7,y: -50.5},
	                         extData:data.data.bike.bikeId
	                       }); 
						 marker.setMap(map);
						routeSearch(marker,geoPoint);
						$('#appointNow').hide();
						$('#appointing').show();
						$('.news-box').hide();
				    	$('.watchbox').show();
						$("#appointId").val(data.data.appointment.appointmentId);
		             },5000,{className:'ui-alert',width:300});
					
				}
				},
				error:function(data){
					reLogin(data);
				}
			});
	
}