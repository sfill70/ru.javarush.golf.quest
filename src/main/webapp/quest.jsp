<%@ page import="ru.javarush.quest.entity.EntityQuest" %>
<%@ page import="ru.javarush.quest.entity.EntityInterface" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="ru.javarush.quest.entity.EntityStatistics" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - mainPage</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body id="container">
<%
    EntityInterface entityInterfaceSession = (EntityInterface) session.getAttribute("entityInterface");
    EntityInterface entityInterfaceRequest = (EntityInterface) request.getAttribute("entityInterface");
    EntityStatistics entityStatistics = (EntityStatistics) session.getAttribute("entityStatistics");
%>
<h1><%= "Level"%> ${countLevel} </h1>
<br/>
<br/>
<a href="restart">Restart</a>
<br/>
<br/>
<br/>
<span>current language: <%=entityStatistics.getLanguage() %></span>
<br/>
<br/>
<br/>
<div class="message"><h4 class="text">${message}</h4></div>

<form method="POST" action="/quest-servlet">
    <div class="padding-inside">
        <input type="hidden" name="formname" value="endgame"/>
        <div class="form-group">
            <input type="radio" id="choice1" name="choice" value="positiveAnswer" checked>
            <label for="choice1"><%=entityInterfaceSession.getPositiveNameButton()%>
            </label>
        </div>
        <div class="form-group">
            <input type="radio" id="choice2" name="choice" value="negativeAnswer">
            <label for="choice2"><%=entityInterfaceSession.getNegativeNameButton()%>
            </label>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value=<%=entityInterfaceSession.getAnswerButton()%>>
        </div>
    </div>
</form>
<br>
<div class="statistic">
    <div><b><%=entityInterfaceSession.getStatistic()[0]%>
    </b></div>
    <div><%=entityInterfaceSession.getStatistic()[1]%><i><%= session.getAttribute("ip")%>
    </i></div>
    <div><%=entityInterfaceSession.getStatistic()[2]%><i><%=entityStatistics.getName() %>
    </i></div>
    <div><%=entityInterfaceSession.getStatistic()[3]%><i><%=entityStatistics.getGamesQuanity()%>
    </i></div>
</div>
</body>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<link href="static/main.css" rel="stylesheet">
</html>