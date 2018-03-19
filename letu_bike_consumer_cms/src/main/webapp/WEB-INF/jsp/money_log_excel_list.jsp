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

	<table border="1" class="table table-striped table-bordered table-hover"
		id="dataTables-example">
		<thead>
			<tr>
				<th>编号</th>
				<th>时间</th>
				<th>项目</th>
				<th>账号</th>
				<th>金额</th>
				<th>操作人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="moneyLog" items="${moneyLogs }">
				<tr class="odd gradeA">
					<td>${moneyLog.moneyLogId }</td>
					<td><fmt:formatDate value="${moneyLog.moneyLogCreateTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
					<c:if test="${moneyLog.moneyLogStreamType==2 }">
							预付款退款
					</c:if>
					<c:if test="${moneyLog.moneyLogStreamType==3 }">
							预付款补充
					</c:if>
					</td>
					<td>${moneyLog.account.aUser.userTel }</td>
					<td><c:if test="${moneyLog.moneyLogStreamType==2 }">
							<fmt:formatNumber pattern="-0.00"
								value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
						</c:if>
						 <c:if test="${moneyLog.moneyLogStreamType!=2 }">
							<fmt:formatNumber pattern="0.00"
								value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
						</c:if>
					</td>
					<td>${moneyLog.admin.adminRealname }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>

