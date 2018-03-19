<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/all.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<base href="${basePath }">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="viewport"
	content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<title>乐途</title>
<link rel="stylesheet" href="html/font/iconfont.css">
<link rel="stylesheet" href="html/css/animate.min.css">
<link rel="stylesheet" href="html/css/base.css">
<script type="text/javascript" src="html/js/zepto.min.js"></script>
<link rel="stylesheet" href="html/js/dialog.css">
<script type="text/javascript" src="html/js/dialog.js"></script>
<script type="text/javascript" src="html/js/iscroll.js"></script>
<link rel="stylesheet" href="html/js/swiper-3.2.7.min.css">
<script type="text/javascript" src="html/js/swiper-3.2.7.jquery.min.js"></script>

</head>

<body>
	<div class="g-body">
		<div id="useBike">
			<div class="weui_cells weui_cells_radius marginlr">
			<div class="surebtn">
				<a class="btn margin-top-20" href="weixin/admin/bikePosition.action">车辆地位</a>
			</div>
			<div class="surebtn">
				<a class="btn margin-top-20">车辆信息</a>
			</div>
			<div class="surebtn">
				<a class="btn margin-top-20">反馈信息</a>
			</div>
			<div class="surebtn">
				<a class="btn margin-top-20">车辆维护</a>
			</div>
			<div class="surebtn">
				<a class="btn margin-top-20">保险申请</a>
			</div>
			
			<div class="surebtn">
				<a class="btn margin-top-20">扫码识别</a>
			</div>
		</div>

		<div class="usedTit red" style="display: none" id="eMessage"></div>




	</div>
</body>

</html>
