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
    <a  href="${requestScope['javax.servlet.forward.request_uri']}
?resourceName=
<%= request.getParameter("resourceName") %>
&category=
<%= request.getParameter("category") %>
&price=
<%= request.getParameter("price") %>
&offset=
<%= Integer.parseInt(request.getParameter("offset"))-Integer.parseInt(request.getParameter("limitConst")) %>
&limit=
<%= Integer.parseInt(request.getParameter("limit"))-Integer.parseInt(request.getParameter("limitConst")) %>
&limitConst=
<%= request.getParameter("limitConst") %>
">предыдущая</a>
    <a  href="${requestScope['javax.servlet.forward.request_uri']}
?resourceName=
<%= request.getParameter("resourceName") %>
&category=
<%= request.getParameter("category") %>
&price=
<%= request.getParameter("price") %>
&offset=
<%= Integer.parseInt(request.getParameter("offset"))+Integer.parseInt(request.getParameter("limitConst")) %>
&limit=
<%= Integer.parseInt(request.getParameter("limit"))+Integer.parseInt(request.getParameter("limitConst")) %>
&limitConst=
<%= request.getParameter("limitConst") %>
">следующая</a>
</div>
</body>
</html>
