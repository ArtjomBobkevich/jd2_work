<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.06.2019
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/person-update" method="post">
    <div>
        <select name="id" id="id">
            <c:forEach var="list" items="${requestScope.loginList}">
                <option value="${list.id}">${list.login}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="avatar">avatar
            <input id="avatar" type="text" name="avatar" required placeholder="avatar"/>
        </label><br>
    </div>
    <div>
        <label for="login">login
            <input id="login" type="text" name="login" required placeholder="login"/>
        </label><br>
    </div>
    <div>
        <label for="firstName">firstName
            <input id="firstName" type="text" name="firstName" required placeholder="firstName"/>
        </label><br>
    </div>
    <div>
        <label for="lastName">lastName
            <input id="lastName" type="text" name="lastName" required placeholder="lastName"/>
        </label><br>
    </div>
    <div>
        <label for="age">age
            <input id="age" type="number" name="age" required placeholder="age"/>
        </label><br>
    </div>
    <div>
        <label for="mail">mail
            <input id="mail" type="text" name="mail" required placeholder="mail"/>
        </label><br>
    </div>
    <div>
        <label for="password">password
            <input id="password" type="password" name="password" required placeholder="password"/>
        </label><br>
    </div>
    <div>
        <input type="submit" value="save">
    </div>
</form>
</body>
</html>
