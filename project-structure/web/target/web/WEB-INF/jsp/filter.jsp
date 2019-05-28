<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 28.05.2019
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/filter" method="post">
    <div>
        <p>автор</p>
        <label for="author">
            <input id="author" type="text" name="author" value="${param.author}"/>
        </label><br>
    </div>
    <input type="submit" value="выбрать">
    <div>
        <p>сколько пропустить</p>
        <label for="offset">
            <input id="offset" type="text" name="offset" value="${param.offset}"/>
        </label><br>
    </div>
    <input type="submit" value="выбрать">
    <div>
        <p>сколько показать</p>
        <label for="limit">
            <input id="limit" type="text" name="limit" value="${param.limit}"/>
        </label><br>
    </div>
    <input type="submit" value="выбрать">
</form>
</body>
</html>