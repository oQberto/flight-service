<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 5/22/2023
  Time: 8:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Flights</title>
</head>
<body>
<h1>Flights list: </h1>
<ul>
    <c:forEach var="flight" items="${requestScope.flights}">
        <li>
            <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">${flight.description}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
