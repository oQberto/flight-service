<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Main Page" %>
</h1>
<br/>
<button type="button">
    <a href="${pageContext.request.contextPath}/flights">Flights</a>
</button>
</body>
</html>