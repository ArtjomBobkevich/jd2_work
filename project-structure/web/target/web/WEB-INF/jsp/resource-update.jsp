<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.06.2019
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/resource-save" method="post">
    <div>
        <select name="id" id="id">
            <c:forEach var="list" items="${requestScope.resources}">
                <option value="${list.id}">${list.resourceName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="resourceName">avatar
            <input id="resourceName" type="text" name="resourceName" required placeholder="resourceName"/>
        </label><br>
    </div>
    <div>
        <label for="foto">login
            <input id="foto" type="text" name="foto" required placeholder="foto"/>
        </label><br>
    </div>
    <div>
        <select name="headingId" id="headingId">
            <c:forEach var="list" items="${requestScope.heading}">
                <option value="${list.headingId}">${list.headingName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="categoryId" id="categoryId">
            <c:forEach var="list" items="${requestScope.category}">
                <option value="${list.categoryId}">${list.categoryName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <select name="personId" id="personId">
            <c:forEach var="list" items="${requestScope.person}">
                <option value="${list.personId}">${list.login}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="price">mail
            <input id="price" type="text" name="price" required placeholder="price"/>
        </label><br>
    </div>
    <div>
        <label for="text">password
            <input id="text" type="text" name="text" required placeholder="text"/>
        </label><br>
    </div>
    <div>
        <input type="submit" value="save">
    </div>
</form>

</body>
</html>
