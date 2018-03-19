

var host = window.location.host+url; //测试


function index(){
	window.location.href ="http://"+host+"/index/goIndex.action";
}

function noUseCode(){
	$.ajax({
	 	type:"post",
		url:'http://'+host+'/user/inviteCode.action',
		dataType:'json',
		success:function(data){
			if("success" == data.message){
				window.location.href ='http://'+host+"/index/goIndex.action";
	    	}else{
	    		
	    		$.alert(data.message,true,function(){
	    			window.location.href ='http://'+host+"/index/goIndex.action";
	             },5000,{className:'ui-alert',width:300});
	    	}
			},
			error:function(data){
				reLogin(data);
			}
		});	
	
	
	
}