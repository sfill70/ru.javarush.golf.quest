<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="static/main.css" rel="stylesheet">
    <title>Error</title>
</head>
<body>

<%--Не работает, слетает переход на данную страницу--%>
<%--<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>--%>

<%--<h2><%=message%>
</h2>
<h2><%=exception%>
</h2>--%>

<img src="static/img/dont_worry_too_much.jpg" alt="We are working on this problem">
<h1>We are working on this problem</h1>
</body>
</html>
