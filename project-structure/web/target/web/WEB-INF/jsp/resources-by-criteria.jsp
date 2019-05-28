<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.05.2019
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <c:forEach var="resources" items="${requestScope.resource}">
        <a >${resources.resourceName}</a><br>
        <a >${resources.heading}</a><br>
        <a >${resources.person}</a><br>
    </c:forEach>
</div>
</body>
</html>
