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
				<th></th>
				<th>退款帐号</th>
				<th>退款发起时间</th>
				<th>退款金额</th>
				<th>退款单号</th>
				<th>处理状态</th>
				<th>处理时间</th>
				<th>退款处理人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="refund" items="${refundList }">
				<tr class="odd gradeA">
					<td><input type="checkbox" name="refundIds"
						value="${refund.refundId }"></td>
					<td>${refund.account.aUser.userTel }</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
							value="${refund.refundCreatetime }" /></td>
					<td class="center"><fmt:formatNumber pattern="0.00" value="${refund.refundMoney }"/></td>
					<td class="center">${refund.refundOrderId }</td>
					<td><c:if test="${refund.refundState==0 }">未处理</c:if> <c:if
							test="${refund.refundState==1 }">通过</c:if> <c:if
							test="${refund.refundState==2 }">拒绝</c:if></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
							value="${refund.refundOperatetime }" /></td>
					<td>${refund.admin.adminRealname }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>


