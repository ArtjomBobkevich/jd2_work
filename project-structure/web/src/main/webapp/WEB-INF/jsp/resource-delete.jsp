<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.06.2019
  Time: 12:00
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
<form action="${pageContext.request.contextPath}/resource-delete" method="post">
    <div>
        <select name="id" id="id">
            <c:forEach var="list" items="${requestScope.resources}">
                <option value="${list.id}">${list.resourceName}</option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="delete">
</form>
</body>
</html>