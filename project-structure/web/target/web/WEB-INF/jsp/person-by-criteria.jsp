<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 31.05.2019
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="list" items="${requestScope.person}">
    <a >${list.login}</a><br>
    <a >${list.age}</a><br>
    <a >${list.personRole}</a><br>
    <a>------------------</a><br>
</c:forEach>
</body>
</html>
