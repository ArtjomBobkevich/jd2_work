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
        <a>${resources.resourceName}</a><br>
        <%--<a>${resources.heading}</a><br>--%>
        <a>${resources.person}</a><br>
    </c:forEach>
</div>
<form action="${pageContext.request.contextPath}/resources-by-criteria" method="post">
    <input type="hidden" name="resource" value="${param.resourceName}"/>
    <input type="hidden" name="category" value="${param.category}"/>
    <input type="hidden" name="price" value="${param.price}"/>
    <input type="hidden" name="offset" value="${param.offset}"/>
    <input type="hidden" name="limit" value="${param.limit}"/>
    <input type="hidden" name="l" value="${param.l}"/>
    <input type="submit" name="page" value="back">
    <input type="submit" name="page" value="next">
</form>
</body>
</html>