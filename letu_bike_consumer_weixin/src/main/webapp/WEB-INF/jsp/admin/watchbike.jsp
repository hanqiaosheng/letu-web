<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/all.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<base href="${basePath }">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
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
    <title>全域</title>
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
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<link rel="stylesheet" href="map/AMap.WalkingRender1120.css"/>
	<link rel="stylesheet" href="map/map.css"/>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
	<script type="text/javascript" src="map/WalkingRender1230.js"></script>
	<!-- 页面js -->
	<script type="text/javascript" src="html/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="html/js/mobile.js"></script>
</head>

<body>
  <a href="javascript:void(0)" onclick="regular()">
<img src="/images/geo.png" style="width: 15px;height: 34.77px;position: fixed;left: 50%;top: 50%;margin-left: -7.5px;margin-top: -34.77px; z-index:999;" id="centerPoint">
</a><!-- 屏幕中心图层-->
   <div class="g-body mapbox">
   <div id="mapContainer"></div>
    <div class="watchbox">
        <div class="tit flex-box"><i class="icon-locationfill"></i><span id="address"></span></div>
        <ul class="info flex-box">
            <li><p class="p1"><span id="distance"></span>米</p><p class="p2">车辆距离</p></li>
            <li><p class="p1"><span id="walkTime"></span>分钟</p><p class="p2">预计到达时间</p></li>
        </ul>
    </div>
    <div class="fix-bottom map-bar">
        <div class="local"></div>
        <div class="scanbox flex-box"> <a class="scan" id="scanCode"><i class="icon-scan"></i>扫码识别</a></div>
        <a  id="userPage">
        	<div class="people"><i class="icon-people"></i></div>
        </a>
    </div>
   </div>
   <script type="text/javascript" src="map/adminMain.js"></script>
</body>

</html>
