<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.06.2019
  Time: 13:39
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
<form action="${pageContext.request.contextPath}/add-heading-by-resource" method="post">
    <div>
        resource
        <select name="resource_id" id="resource_id">
            <c:forEach var="list" items="${requestScope.resources}">
                <option value="${list.id}">${list.resourceName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        heading
        <select name="heading_id" id="heading_id">
            <c:forEach var="list" items="${requestScope.headingList}">
                <option value="${list.id}">${list.headingName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <input type="submit" value="save">
    </div>
</form>
</body>
</html>