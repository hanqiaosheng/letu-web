<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="application/msexcel" %>
<!-- 以上这行设定本网页为excel格式的网页 -->
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
	<table class="table table-striped table-bordered table-hover"
		id="dataTables-example">
		<thead>
			<tr>
				<th>编号</th>
				<th>日期 </th>
				<th>周几</th>
				<th>景区</th>
				<th>性别</th>
				<th>租赁时间</th>
				<th>归还时间</th>
				<th>骑行时间</th>
				<th>租车点</th>
				<th>还车点</th>
				<th>出生年</th>
				<th>账号</th>
				<th>租金</th>
			</tr>
		</thead>
		<tbody>
		        <c:forEach var="bikeRentInfo" items="${bikeRentInfoList }">
				<tr class="odd gradeA">
					<td>${bikeRentInfo.rentInfoId }</td>
					<td><fmt:formatDate value="${bikeRentInfo.rentStarttime }" pattern="yyyy-MM-dd"/></td>
					<td>${bikeRentInfo.weekName }</td>
					<td>${bikeRentInfo.channel.channelName }</td>
					<c:if test="${bikeRentInfo.user.userGender==1 }">
					<td>男</td>
					</c:if>
					<c:if test="${bikeRentInfo.user.userGender==2 }">
					<td>女</td>
					</c:if>
					<c:if test="${bikeRentInfo.user.userGender==0 }">
					<td>不明确</td>
					</c:if>
					<td><fmt:formatDate value="${bikeRentInfo.rentStarttime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${bikeRentInfo.rentEndtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${bikeRentInfo.rentLongtime }</td>
					<td>${bikeRentInfo.startFixedName }</td>
					<td>${bikeRentInfo.endFixedName }</td>
					<td><fmt:formatDate value="${bikeRentInfo.user.userBirthday }" pattern="yyyy"/></td>
					<td>${bikeRentInfo.user.userTel }</td>					
					<td><fmt:formatNumber pattern="0.00" value="${bikeRentInfo.rentPrice }"></fmt:formatNumber></td>	
				</tr>
				</c:forEach>
		</tbody>
	</table>
</body>
</html>

