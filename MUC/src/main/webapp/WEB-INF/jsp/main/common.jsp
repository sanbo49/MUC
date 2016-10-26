<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
String path=request.getContextPath(); 
// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
%> 
<c:set value="${basePath}" var="basePath" scope="session"/>
<base href="<%=basePath%>"> 
<link href="static/css/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/jquery.form.js"></script>
<script type="text/javascript" src="static/js/global.js"></script>
<script type="text/javascript" src="static/js/jquery.tab.js"></script>


<!-- jqueryPage.js -->
<script type="text/javascript" language="javascript" src="static/js/pagination/jqueryPage.js"></script>
<link rel="stylesheet" type="text/css" href="static/js/pagination/pagination.css"/>
<!-- jQuery-Templates -->
<script type="text/javascript" language="javascript" src="static/js/jQuery-Templates/jquery.tmpl.min.js"></script>
