<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02.04.2019
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <span>${requestScope.person.avatar}</span><br>
    <span>${requestScope.person.login}</span><br>
    <span>${requestScope.person.identification}</span><br>
    <span>${requestScope.person.age}</span><br>
    <span>${requestScope.person.mail}</span><br>
    <span>${requestScope.person.personRole}</span><br>
    <a>--------------------------</a><br>
</div>
</body>
</html>
