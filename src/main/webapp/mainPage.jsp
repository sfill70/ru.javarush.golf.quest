<%@ page import="ru.javarush.quest.entity.Entity" %>
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
<h1><%= "Level" %>
</h1>
<br/>
<a href="restart">Restart</a>

<hr>
<span>current language: ${language}</span>
<hr>
<h4>${message}</h4>

<form method="POST" action="init-servlet">
    <div class="padding-inside">
        <input type="hidden" name="formname" value="endgame"/>
        <div>
            <input type="radio" id="choice1" name="choice" value="positiveAnswer" checked>
            <label for="choice1">${positiveButton}</label>
        </div>
        <div>
            <input type="radio" id="choice2" name="choice" value="negativeAnswer">
            <label for="choice2">${negativeButton}</label>
        </div>
        <div>
            <input type="submit" value="Ответить"/>
        </div>
    </div>
</form>
<br>
<hr>
<div class="statistic">
    <div><b>${statistic[0]}</b></div>
    <div>${statistic[1]} <i><%= session.getAttribute("ip")%>
    </i></div>
    <div>${statistic[2]} <i><%= session.getAttribute("username") %>
    </i></div>
    <div>${statistic[3]} <i><%= session.getAttribute("gamesquanity")%>
    </i></div>
</div>

</body>
</html>