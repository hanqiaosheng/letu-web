$(document).ready(function(){ 
	   $.ajax({
	       url : url+"/user/userPage.action",
	       async : true,  
	       type : "post", 
	       dataType:"json",
	       beforeSend : function() {
	           //alert("before");
	       },
	       success : function(data) {
	    	    $('#userHead').attr("src",data.data.user.userProfileImage);
		       	$('#deposit').html("&yen;"+data.data.account.accountDeposit);
		       	$('#realName').html(data.data.user.userRealname);
		       	$('#inviteCode').html(data.data.user.userInviteCode);
		       	$('#aDetail').attr("href","accountDetail.html?accountId="+data.data.account.accountId);
		       	
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