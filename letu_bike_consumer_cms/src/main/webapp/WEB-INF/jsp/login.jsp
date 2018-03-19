<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/all.jsp" %>
<html>
<head>
<base href="${basePath }"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>全域骑游</title>
<link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="css/cms/dist/fontface.css" rel="stylesheet"> -->
<link href="oldStyle/login-soft.css" rel="stylesheet" type="text/css"/>

 <script src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="oldStyle/jquery.backstretch.min.js" type="text/javascript"></script>

<script>
var  rule={
	    allright:true,
	    erroralert:function(obj,text) {
	        var parent=obj.parent();
	        obj.data('group-state',false);
	        $('#msg').html(text);
	        $('.alert-danger').slideDown();
	    },
	    success:function(obj) {
	        obj.data('group-state',true);
	        $('.alert-danger').slideUp()
	    },
	    checkstate:function() {//检查所有状态
	        rule.allright=true; 
	            $.each($('*[data-group-state]'),function (item) {
	                $(this).triggerHandler("blur");
	                if(!$(this).data('group-state')){
	                    rule.allright=false; 
	                }
	            })
	            return rule.allright 
	        
	    },
	    empty:function(obj,mess){//不能为空
	        var str=obj.val().replace(/(^\s+)|(\s+$)/g,"");
	        if(str==''){
	            this.erroralert(obj,mess)
	        }else{
	            this.success(obj);
	        }   
	    },
	    custom:function(obj,reg,mess,callback){
	        var myReg=eval(reg);
	        if (!myReg.test(obj.val())) {
	            this.erroralert(obj,mess)
	        }else{
	            callback?callback():this.success(obj);
	        }
	    }
	    
	};
$(function() {      	
   	 $.backstretch([
        "./oldStyle/1.jpg",
         "./oldStyle/2.jpg",
         "./oldStyle/3.jpg",
         "./oldStyle/4.jpg"
         ], {
         fade: 1000,
         duration: 8000
     }
     );

   	var flag = '${message}';
   	if(flag != ""){
   		if(flag=='telCode'){
   			$("#msg").text("验证码错误");
   		}else{
   			$("#msg").text("用户名和密码不存在");
   		}
  		var obj=$('.alert-danger');
   		obj.slideDown();
   		errTimeout = setTimeout(function() {//3秒隐藏错误提示
   			obj.slideUp();
        }, 3000);
   	}   
});
function tips(){
	var obj=$('.alert-danger');
  		obj.slideDown();
  		errTimeout = setTimeout(function() {//3秒隐藏错误提示
  			obj.slideUp();
       }, 3000);
}



</script>


</head>

<body class="login">
	<div class="logo">
		全域骑游
	</div>

	<div class="content">
		<form class="login-form" action="cms/admin/login.action" method="post" onsubmit="return rule.checkstate()">
			<h3 class="form-title">登录</h3>
			<div class="alert alert-danger">
				<span id="msg">请输入用户名和密码 </span>
			</div>
			<div class="form-group">
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username" id="username"/>
				</div>
			</div>
			<div class="form-group">
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
				</div>
			</div>
			<div class="form-actions">
				<label class="checkbox">
				<button type="submit" class="btn blue pull-right">
				登录 <!-- <i class="m-icon-swapright m-icon-white"></i> -->
				</button>
			</div>
		</form>
	</div>

<div class="copyright">
</div>
</body>
</html>