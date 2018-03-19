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
    <table border="1" class="table table-striped table-bordered table-hover" id="dataTables-example">
			<thead>
				<tr>
					<th>兑换方案名称</th>
					<th>兑换码</th>
					<th>用户账号</th>
					<th>用户名称</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userRedeemPlanList }" var="userRedeemPlan" varStatus="varStatus">
				<tr class="odd gradeA">
					<td>${userRedeemPlan.redeemPlan.planName }</td>
					<td>${userRedeemPlan.redeemPlan.planRedeemCode }</td>
					<td>${userRedeemPlan.user.userTel }</td>
					<td>${userRedeemPlan.user.userRealname }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${userRedeemPlan.urpCreateTime }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

