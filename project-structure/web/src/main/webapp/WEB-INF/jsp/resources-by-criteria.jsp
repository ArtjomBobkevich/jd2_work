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

    <form action="${pageContext.request.contextPath}/resources-by-criteria" method="post">
        <div>
            <p>название ресурса</p>
            <label for="resourceName">
                <input id="resourceName" type="text" name="resourceName" value="${param.resourceName}"/>
            </label><br>
        </div>
        <div>
            <p>категория</p>
            <label for="category">
                <input id="category" type="text" name="category" value="${param.category}"/>
            </label><br>
        </div>
        <div>
            <div>
                <p>цена</p>
                <label for="price">
                    <input id="price" type="text" name="price" value="${param.price}"/>
                </label><br>
            </div>
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
        <input type="submit" value="далее">
    </form>
    <div>
        <c:forEach var="resources" items="${requestScope.resource}">
            <a>${resources.resourceName}</a><br>
            <a>${resources.heading}</a><br>
            <a>${resources.person}</a><br>
        </c:forEach>
    </div>
        <%--<a href="${requestScope['javax.servlet.forward.request_uri']}--%>
        <%--?resourceName=--%>
        <%--<%= request.getParameter("resourceName") %>--%>
        <%--&category=--%>
        <%--<%= request.getParameter("category") %>--%>
        <%--&price=--%>
        <%--<%= request.getParameter("price") %>--%>
        <%--&offset=--%>
        <%--<%= Integer.parseInt(request.getParameter("offset"))-Integer.parseInt(request.getParameter("limit")) %>--%>
        <%--&limit=--%>
        <%--<%= Integer.parseInt(request.getParameter("limit"))-Integer.parseInt(request.getParameter("limit")) %>--%>
        <%--">предыдущая</a>--%>
        <%--<a href="${requestScope['javax.servlet.forward.request_uri']}--%>
        <%--?resourceName=--%>
        <%--<%= request.getParameter("resourceName") %>--%>
        <%--&category=--%>
        <%--<%= request.getParameter("category") %>--%>
        <%--&price=--%>
        <%--<%= request.getParameter("price") %>--%>
        <%--&offset=--%>
        <%--<%= Integer.parseInt(request.getParameter("offset"))+Integer.parseInt(request.getParameter("limit")) %>--%>
        <%--&limit=--%>
        <%--<%= Integer.parseInt(request.getParameter("limit"))+Integer.parseInt(request.getParameter("limit")) %>--%>
        <%--">следующая</a>--%>
</body>
</html>
