<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String savePath = request.getContextPath();
    response.sendRedirect(savePath+"/report/admin/loginJsp.action");
%>