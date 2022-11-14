<%--
  Created by IntelliJ IDEA.
  User: Sfill_PC
  Date: 27.10.2022
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="static/main.css" rel="stylesheet">
    <title>Error</title>
</head>
<body>
<%
    String message = pageContext.getException().getMessage();
    String exception = pageContext.getException().getClass().toString();
%>
<h2><%=message%>
</h2>
<h2><%=exception%>
</h2>
<img src="static/img/dont_worry_too_much.jpg" alt="We are working on this problem">
<h1>We are working on this problem</h1>
</body>
</html>
