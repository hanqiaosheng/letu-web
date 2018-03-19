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
										    
											<th>账号</th>
											<th>姓名</th>
											<th>身份证</th>
											<th>注册时间</th>
											<th>最后使用车辆时间</th>
											
										</tr>
									</thead>
									<tbody>
									<c:forEach var="user" items="${userList }">
											<tr class="odd gradeA">
											    <td>${user.userTel }</td>
											    <td>${user.userRealname }</td>
												<td>${user.userIdcard }</td>
												<td><fmt:formatDate pattern="yyyy-MM-dd " value="${user.userCreatetime }"/></td>
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${user.userLastusebiketime }"/></td>
												
											</tr>
									</c:forEach>
									</tbody>
	</table>

</body>
</html>

