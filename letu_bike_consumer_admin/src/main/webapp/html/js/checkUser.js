//var host = window.location.host;

var host = window.location.host+url; //测试
$(document).ready(function(){ 
$("#userPage").click(function(){
	 $.ajax({
		 	type:"post",
			url:'http://'+host+'/user/getUser.action',
			dataType:'json',
			success:function(data){
				if(null == data.user){
					window.location.href ='http://'+host+"/html/regist.html";
		    	}else if(null == data.user.userIdcard){
		    		window.location.href ='http://'+host+"/html/realName.html";
		    	}else{
		    		window.location.href ='http://'+host+"/html/user.html#slide2";	   
		    	}
				},
				error:function(data){
					reLogin(data);
				}
			});
	
	
	
})
$("#feedbackScan").click(function(){
	$.isLoading.show();
	   var hidstr = "url="+encodeURIComponent(window.location.href);
	   $.ajax({
	       url : url+"/bikeRent/scanCode.action",
	       data:hidstr,
	       async : true,  
	       type : "post", 
	       dataType:"json",
	       beforeSend : function() {
	           //alert("before");
	       },
	       success : function(data) {
	    	   wx.config({
	    		      debug: false,
	    		      appId: data.appId,
	    		      timestamp: data.timestamp,
	    		      nonceStr: data.nonceStr,
	    		      signature: data.signature,
	    		      jsApiList: [
	    		        'scanQRCode'
	    		      ]
	    		  });
	    	   $.isLoading.hide();
		    	   wx.scanQRCode({
		    		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		    		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		    		    success: function (res) {
		    		    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		    		    $('#feedbackScan').html(result);
		    		}
		    		});
	    		  
	       },
			error:function(data){
				reLogin(data);
			}
	   }); 
})

})
