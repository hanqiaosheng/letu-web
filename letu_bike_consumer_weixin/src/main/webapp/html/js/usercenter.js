$(document).ready(function(){ 
	   $.ajax({
	       url : url+"/user/userPage.action",
	       async : true,  
	       type : "post", 
	       dataType:"json",
	       beforeSend : function(request) {
	    	   request.setRequestHeader("fromFlag", "2");
	       },
	       success : function(data) {
	    	   if(null!=data.data.user.userProfileImage&&data.data.user.userProfileImage!=""){
	    		   $('#userHead').attr("src",data.data.user.userProfileImage);
	    	   }else{
	    		   $('#userHead').attr("src","http://oriwbtk9q.bkt.clouddn.com/1024.png");
	    	   }
	    	   
	    	    
		       	$('#deposit').html("&yen;"+data.data.account.accountDeposit);
		       	$('#rechargeBalance').html("&yen;"+data.data.account.accountAvailableBalance);
		       	$("#realName").html(data.data.user.userTel.substring(0,3)+"****"+data.data.user.userTel.substring(8,11));
		       	$('#inviteCode').html(data.data.user.userInviteCode);
		       	$('#aDetail').attr("href","accountDetail.html?accountId="+data.data.account.accountId);
		       	if(null!=data.data.user.userChannelId&&""!=data.data.user.userChannelId&&0!=data.data.user.userChannelId){
		       		$("#wallet").show();
		       	}else{
		       		$("#wallet").hide();
		       	}
		       	$("#levelimg").attr("src","/images/level/v"+data.data.user.userLevel+"@3x.png")
		       	var level = ".level"+data.data.user.userLevel;
		       	$(level).append(
		       		 '<div class="chezi">'+
	                 '<div class="cheti"><img src="/images/cheti.png"></div>'+
	                 '<div class="chelun chelun1"><img src="/images/chelun.png"></div>'+
	                 '<div class="chelun chelun2"><img src="/images/chelun.png"></div>'+
	                 '<div class="cheta"><img src="/images/cheta.png"></div></div>'		
		       	)
		       	var option ="#"+data.data.user.userLevel;
		       	$(option).attr("selected",true);
		       	
	       },
			error:function(data){
				reLogin(data);
			}
	   }); 
})