<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 31.05.2019
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/filter-by-person" method="post">

    <%--<select id='login' multiple>--%>
        <%--<c:forEach var="list" items="${requestScope.login}">--%>
            <%--<a>${list.login}</a><br>--%>
            <%--<a>${list.login}</a><br>--%>
            <%--<a>${list.identification}</a><br>--%>
            <%--&lt;%&ndash;<a>${list.lastName}</a><br>&ndash;%&gt;--%>
            <%--<a>${list.age}</a><br>--%>
            <%--<a>${list.mail}</a><br>--%>
            <%--<a>${list.personRole}</a><br>--%>
            <%--<a>--------------------------</a><br>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>
        <div>
            <p>логин</p>
            <label for="login">
                <input id="login" type="text" name="login" value="${param.login}"/>
            </label><br>
        </div>
        <div>
            <p>почта</p>
            <label for="mail">
                <input id="mail" type="text" name="mail" value="${param.mail}"/>
            </label><br>
        </div>
    <div>
    <p>права</p>
        <label for="role">
            <input id="role" type="text" name="role" value="${param.role}"/>
        </label><br>
    </div>
    <div>
        <p>сколько пропустить</p>
        <label for="offset">
            <input id="offset" type="text" name="offset" value="${param.offset}"/>
        </label><br>
    </div>
    <div>
        <p>сколько показать</p>
        <label for="limit">
            <input id="limit" type="text" name="limit" value="${param.limit}"/>
        </label><br>
    </div>
        <p>кол-во записей</p>
        <label for="size">
            <input id="size" type="text" name="size" value="${param.size}"/>
        </label><br>
        </div>
    <input type="submit" value="выбрать">
</form>
</body>
</html>
