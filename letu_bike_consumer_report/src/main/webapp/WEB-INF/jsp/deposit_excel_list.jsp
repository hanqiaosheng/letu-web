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
											<th>时间</th>
											<th>账号</th>
											<th>姓名</th>
											
											<th>金额（元）</th>
											<!-- <th>处理时间</th>
											<th>退款处理人</th> -->
											<!-- <th>退款详情</th>
											<th>操作</th> -->
										</tr>
									</thead>
									<tbody>
									<c:forEach var="moneyLog" items="${moneyLogList }">
											<tr class="odd gradeA">
											    <td>${moneyLog.moneyLogId }</td>								
												<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${moneyLog.moneyLogCreateTime  }"/></td>
												<td>${moneyLog.account.aUser.userTel }</td>
												<td>${moneyLog.account.aUser.userRealname }</td>
												
												
												<td>
												<c:if test="${moneyLog.moneyLogStreamType==3 }">
												<fmt:formatNumber pattern="0.00" value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
												</c:if>
												<c:if test="${moneyLog.moneyLogStreamType==4 }">
												<fmt:formatNumber pattern="0.00" value="${moneyLog.moneyLogOpreateMoney }"></fmt:formatNumber>
												</c:if>
												</td>
											</tr>
									</c:forEach>
									</tbody>
	</table>

</body>
</html>

