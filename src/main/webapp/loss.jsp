
<%@ page import="ru.javarush.quest.entity.EntityQuest" %>
<%@ page import="ru.javarush.quest.entity.EntityInterface" %>
<%@ page import="ru.javarush.quest.entity.EntityStatistics" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loss</title>
    <link href="static/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
<%
    EntityInterface entityInterfaceSession = (EntityInterface) session.getAttribute("entityInterface");
    EntityInterface entityInterfaceRequest = (EntityInterface) request.getAttribute("entityInterface");
    EntityStatistics entityStatistics = (EntityStatistics) session.getAttribute("entityStatistics");
%>
<%=entityInterfaceSession.getLossMessage()%>
<span class="one"> <%=entityStatistics.getName() %></span>
<br>
<h4>${message}</h4>
<h4>${countLevel}</h4>
<br>
<h2>Restart</h2>
<br>
<form action="restart" method="post">
    <button>Restart</button>
</form>
</body>
</html>
