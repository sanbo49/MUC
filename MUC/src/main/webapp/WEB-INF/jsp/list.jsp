<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello.jsp</title>
</head>
<body>
中华人民共和国

<table>    
 <c:forEach items="${ls}" var="p">
 <tr>
     <td>${p.id}</td>
    <td>${p.userName}</td>
    <td>${p.age}</td>
        
</tr>
 </c:forEach>
 
 </table>    
    
</body>
</html>