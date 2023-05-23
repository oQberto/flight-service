<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 5/24/2023
  Time: 12:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="ticket" items="${requestScope.tickets}">
    <li>${ticket.description}</li>
</c:forEach>
</body>
</html>
