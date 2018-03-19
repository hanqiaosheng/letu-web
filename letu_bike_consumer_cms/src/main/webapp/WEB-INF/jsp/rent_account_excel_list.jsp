<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="application/msexcel" %>
<!DOCTYPE html>
<head>
    <title></title>
    <base href="${basePath}"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
  
 </head>
<body>

	<table border="1" class="table table-striped table-bordered table-hover"
		id="dataTables-example">
		<thead>
			<tr>
				<th>编号</th>
				<th>时间</th>
				<th>车辆编号</th>
				<th>渠道</th>
				<th>金额</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bikeRentInfo" items="${bikeRentInfos }">
				<tr class="odd gradeA">
					<td>${bikeRentInfo.rentInfoId }</td>
					<td><fmt:formatDate value="${bikeRentInfo.rentEndtime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bikeRentInfo.bike.bikeCode }</td>
					<td>${bikeRentInfo.channel.channelName }</td>
					<td><fmt:formatNumber pattern="0.00"
							value="${bikeRentInfo.rentPrice }"></fmt:formatNumber></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>

