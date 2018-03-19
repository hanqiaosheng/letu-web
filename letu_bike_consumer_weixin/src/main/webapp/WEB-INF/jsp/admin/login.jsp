<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/all.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<base href="${basePath }">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="address=no">
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <title>管理员登录</title>
    <link rel="stylesheet" href="html/font/iconfont.css">
    <link rel="stylesheet" href="html/css/animate.min.css">
    <link rel="stylesheet" href="html/css/base.css">
    <script type="text/javascript" src="html/js/zepto.min.js"></script>
    <script type="text/javascript" src="html/js/fastclick.js"></script>
    <link rel="stylesheet" href="html/js/dialog.css">
    <script type="text/javascript" src="html/js/dialog.js"></script>
    <script type="text/javascript" src="html/js/iscroll.js"></script>
    <link rel="stylesheet" href="html/js/swiper-3.2.7.min.css">
    <script type="text/javascript" src="html/js/swiper-3.2.7.jquery.min.js"></script>
    
    <!--  -->
    <script type="text/javascript" src="html/js/mobile.js"></script>
</head>

<body>
   <div class="g-body">
       <ul class="regist-step marginlr">
           <li><p>全域单车管路员登录</p></li>
       </ul>
      <span style="color: red">${message }</span>
       <form method="post" id="formSub" class="g-form" action="weixin/admin/login.action">
            <div class="weui_cells weui_cells_radius marginlr">
               <div class="weui_cell">
                    <div class="weui_cell_hd"><label class="weui-label">账号</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="text" name="username" id="username" placeholder="请输入账号" onblur="rule.empty($(this),'账号不能为空');" data-group-state="false">
                    </div>
                </div>
                <div class="weui_cell" id="getcode">
                    <div class="weui_cell_hd"><label class="weui-label">密码</label></div>
                    <div class="weui_cell_bd weui_cell_primary">
                        <input class="weui_input" type="password" name="password" placeholder="请输入密码" onblur="rule.empty($(this),'密码不能为空');" data-group-state="false">
                    </div>
                </div>

            </div>
            <div class="surebtn">
                <a class="btn margin-top-20 weui_btn_primary">登录</a>
            </div>
        </form>
   </div>
<script type="text/javascript">
function getStatesFn(){
	
	$("#formSub").submit();	
	
}




</script>
</body>

</html>
