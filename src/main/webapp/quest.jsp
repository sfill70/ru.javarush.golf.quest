<%@ page import="ru.javarush.quest.entity.EntityQuest" %>
<%@ page import="ru.javarush.quest.entity.EntityInterface" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - mainPage</title>
    <link href="static/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
<%
    EntityInterface entityInterfaceSession = (EntityInterface) session.getAttribute("entityInterface");
    EntityInterface entityInterfaceRequest = (EntityInterface) request.getAttribute("entityInterface");
%>
<h1><%= "Level"%> ${countLevel} </h1>
<br/>
<a href="restart">Restart</a>

<hr>
<span>current language: ${language}</span>
<hr>
<h4>${message}</h4>

<form method="POST" action="/init-servlet">
    <div class="padding-inside">
        <input type="hidden" name="formname" value="endgame"/>
        <div>
            <input type="radio" id="choice1" name="choice" value="positiveAnswer" checked>
            <label for="choice1"><%=entityInterfaceSession.getPositiveNameButton()%></label>
        </div>
        <div>
            <input type="radio" id="choice2" name="choice" value="negativeAnswer">
            <label for="choice2"><%=entityInterfaceSession.getNegativeNameButton()%></label>
        </div>
        <div>
            <input type="submit" value="Ответить"/>
        </div>
    </div>
</form>
<br>
<hr>
<div class="statistic">
    <div><b><%=entityInterfaceSession.getStatistic()[0]%>
    </b></div>
    <div><%=entityInterfaceSession.getStatistic()[1]%><i><%= session.getAttribute("ip")%>
    </i></div>
    <div><%=entityInterfaceSession.getStatistic()[2]%><i><%= session.getAttribute("username") %>
    </i></div>
    <div><%=entityInterfaceSession.getStatistic()[3]%><i><%= session.getAttribute("gamesquanity")%>
    </i></div>
</div>
<%--<div class="statistic">
    <div><b><%=entityInterfaceSession.getStatistic()[0]%></b></div>
    <div>${statistic[1]} <i><%= session.getAttribute("ip")%>
    </i></div>
    <div>${statistic[2]} <i><%= session.getAttribute("username") %>
    </i></div>
    <div>${statistic[3]} <i><%= session.getAttribute("gamesquanity")%>
    </i></div>
</div>--%>

</body>
</html>