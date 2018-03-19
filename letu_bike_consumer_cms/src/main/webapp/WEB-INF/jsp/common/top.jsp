<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="all.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${basePath }">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>${title }</title>
    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="dist/css/global.css" rel="stylesheet">

	<script src="bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
	function pageChanged(flag) {
		var pageIndex = Number("${pageIndex}"); //当前页码
		switch (flag) {
		case ("prev"):
			if(pageIndex <= 1){
				pageIndex = 1;
			}else{
				pageIndex -= 1;
			}
			break;
		case ("next"):
			if(pageIndex >= "${totalPage}"){
				pageIndex = "${totalPage}";
			}else{
				pageIndex += 1;
			}
			break;
		}
		$("#pageIndex").val(pageIndex);
		$("#exportFlag").val(0);
		$("#searchForm").submit();
	}

	function jump() {
		var check = /^\d+$/;
		if(!check.test($("#pageNum").val())){
			$.alert("请输入正整数！",true,function(){
            },false,{className:'ui-alert',width:300});
			return false;
		}
		var num = Number($("#pageNum").val());
		var totalPage = Number("${totalPage }");
		if (num == 0 || num == "") {
			num = 1;
		} else if (num > totalPage) {
			$.alert("当前页面最多${totalPage }页！",true,function(){
            },false,{className:'ui-alert',width:300});
		} else {
			$("#pageIndex").val(num);
			$("#exportFlag").val(0);
			$('#searchForm').submit();
		}
		return true;
	}
</script>

</head>
</html>
