
$(document).ready(function(){ 
		$.ajax({
		 	type:"post",
			url:url+'/user/already.action',
			dataType:'json',
			beforeSend: function(request) {
	            request.setRequestHeader("fromFlag", "2");
	        },
			success:function(data){
				if("unlogin" == data.message||"telphone"!=data.message){
					window.location.href =url+"/index/goIndex.action";
		    	}
				},
				error:function(data){
					reLogin(data);
				}
			});	
		
	
});