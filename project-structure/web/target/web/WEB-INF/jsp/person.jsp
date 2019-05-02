<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.05.2019
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:forEach var="persons" items="${requestScope.person}">
        <a >${persons.login}</a><br>
        <span>${persons.login}</span><br>
        <span>${persons.first_name}</span><br>
        <span>${persons.last_name}</span><br>
        <span>${persons.age}</span><br>
        <span>${persons.mail}</span><br>
        <span>${persons.personRole}</span><br>
    </c:forEach>
</div>
</body>
</html>
