<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.05.2019
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:forEach var="list" items="${requestScope.persons}">
        <a>${list.avatar}</a><br>
        <a>${list.login}</a><br>
        <a>${list.identification}</a><br>
        <%--<a>${list.lastName}</a><br>--%>
        <a>${list.age}</a><br>
        <a>${list.mail}</a><br>
        <a>${list.personRole}</a><br>
        <a>--------------------------</a><br>
    </c:forEach>
</div>
</body>
</html>