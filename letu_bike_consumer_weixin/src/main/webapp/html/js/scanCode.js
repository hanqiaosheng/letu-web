//var host = window.location.host;
	function scanCode(){
		$.isLoading.show();
		 $.ajax({
			 	type:"post",
				url:url+'/user/getUser.action',
				dataType:'json',
				beforeSend: function(request) {
		            request.setRequestHeader("fromFlag", "2");
		        },
				success:function(data){
					if(null == data.user){
						window.location.href =url+"/index/goIndex.action";
			    	}else if(null == data.user.userTel||"" == data.user.userTel){
			    		window.location.href =url+"/html/regist.html";
			    	}else if(null == data.user.userIdcard||"" == data.user.userIdcard){
			    		window.location.href =url+"/html/realName.html";
			    	}else if(0!=data.user.userState){
			    		$.isLoading.hide();
			    		if(null==data.bikeRentInfo.rentEndtime){
				    		$.alert("您有租赁尚未结束",false,function(){
				    			window.location.href=url+"/html/bikeUsing.html?rentId="+data.bikeRentInfo.rentInfoId+"&bikeState="+data.bike.bikeState;
				             },5000,{width:300});
			    		}else{
			    			$.alert("您有订单尚未支付",false,function(){
			    				window.location.href=url+"/html/bikeEnd.html?rentId="+data.bikeRentInfo.rentInfoId;
				             },5000,{width:300});
			    		}
			    	}else{
	    				 	   $.isLoading.hide();
	    				 	  wx.ready(function () {
	    				 		 wx.scanQRCode({
    				    		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
    				    		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
    				    		    success: function (res) {
	    				    		    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
	    				    		    var results=[];
	    				    		    results=result.split("?bike=",2);
	    				    		    if(results[1]!=undefined){
	    				    		    	var bikeCode=results[1];
	 	    				    		    $.ajax({
	 	    				    				type : "post",
	 	    				    				url : url+'/bikeRent/checkBike.action',
	 	    				    				data : "bikeCode=" + bikeCode,
	 	    				    				dataType : 'json',
	 	    				    				success : function(data) {
	 	    				    					if (data.code == 1){
	 	    				    						unlock(bikeCode);
	 	    				    						//JavaScript:window.location.href="openLock.html?bikeCode="+bikeCode+"&geoPoint="+geoPoint.toString();
	 	    				    					}
	 	    				    					else{
	 	    				    						$.alert(data.message,false,function(){
	 			    						             },5000,{width:300});
	 	    				    					}
	 	    				    				},
	 	    				    				error:function(data){
	 	    				    					reLogin(data);
	 	    				    				}
	 	    				    			});
	    				    		    }else{
	    				    		    	$.alert("您扫码的二维码有误，请您选择正确的二维码",false,function(){
	    						             },false,{width:300});
	    				    		    }
	    				    		},
	    				    		fail:function (res) {
//	    				    			alert("接口调用失败");
	    				    			window.location.reload();
	    				    		}
	    				    	});
	    				 	  })
//	    			    	   alert("end");
	    				    }
			    		},
					error:function(data){
						 $.isLoading.hide();
						reLogin(data);
					}
				});
		
	}

