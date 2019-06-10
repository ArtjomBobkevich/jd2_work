<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.06.2019
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="list" items="${requestScope.resources}">
    <a>${list.resourceName}</a><br>
    <a>${list.heading}</a><br>
    <a>${list.category}</a><br>
    <a>${list.person}</a><br>
    <a>${list.price}</a><br>
    <a>${list.text}</a><br>
    <a>--------------------------</a><br>
</c:forEach>
</body>
</html>