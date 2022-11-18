
<%@ page import="ru.javarush.quest.entity.EntityQuest" %>
<%@ page import="ru.javarush.quest.entity.EntityInterface" %>
<%@ page import="ru.javarush.quest.entity.EntityStatistics" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Victory</title>
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
<span><%=entityInterfaceSession.getWinMessage()%></span>
<h2 class="one"><%=entityStatistics.getName() %></h2>
<h4>${message}</h4>
<br>
<h2>Restart</h2>
<form action="restart" method="post">
    <button>Restart</button>
</form>

</body>
</html>
