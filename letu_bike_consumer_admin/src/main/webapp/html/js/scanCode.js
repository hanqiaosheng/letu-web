//var host = window.location.host;

var host = window.location.host; //测试
$(document).ready(function(){ 
$("#scanCode").click(function(){
		   var hidstr = "url="+encodeURIComponent(window.location.href.split('#')[0]);
		   $.ajax({
		       url : "/api/bikeMessage/scanCode.action",
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
		    	   wx.ready(function () {
			    	   wx.scanQRCode({
			    		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
			    		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
			    		    success: function (res) {
			    		    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
			    		    var results=[];
			    		    results=result.split("?bike=",2);
			    		    if(results[1]!=undefined){
			    		    	window.location.href="/#/bikeInformation/"+results[1];
			    		    }else{
			    		    	$.alert("您扫码的二维码有误，请您选择正确的二维码",true,function(){
					             },5000,{className:'ui-alert',width:300});
			    		    	
			    		    }
			    		}
			    		});
		    	   })
		    		  
		       },
		       error : function() {
		       }
		   }); 
	
})


})
