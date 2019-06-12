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
<form action="${pageContext.request.contextPath}/resource-update" method="post">
    <div>
        <select name="id" id="id">
            <c:forEach var="list" items="${requestScope.resource}">
                <option value="${list.id}">${list.resourceName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="resourceName">resourceName
            <input id="resourceName" type="text" name="resourceName" required placeholder="resourceName"/>
        </label><br>
    </div>
    <div>
        <label for="foto">foto
            <input id="foto" type="text" name="foto" required placeholder="foto"/>
        </label><br>
    </div>
    <div>
        category
        <select name="categoryId" id="categoryId">
            <c:forEach var="list" items="${requestScope.category}">
                <option value="${list.id}">${list.categoryName}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        author
        <select name="personId" id="personId">
            <c:forEach var="list" items="${requestScope.person}">
                <option value="${list.id}">${list.login}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="price">price
            <input id="price" type="number" name="price" required placeholder="price"/>
        </label><br>
    </div>
    <div>
        <label for="text">text
            <input id="text" type="text" name="text" required placeholder="text"/>
        </label><br>
    </div>
    <div>
        <input type="submit" value="save">
    </div>
</form>

</body>
</html>