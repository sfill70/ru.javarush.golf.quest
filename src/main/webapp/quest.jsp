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
<h1><%= "Level"%> ${countLevel} </h1>
<br/>
<a href="restart">Restart</a>

<hr>
<span>current language: ${language}</span>
<h4><% EntityInterface entityInterface2 = (EntityInterface) request.getAttribute("entityInterface");
    EntityInterface entityInterface = (EntityInterface)session.getAttribute("entityInterface");
//    PrintWriter out1 = response.getWriter();

    if (entityInterface != null ) {
        out.println("<h4>"+entityInterface.getNegativeNameButton()+"</h4>");
    }
    if (entityInterface2 != null ) {
        out.println("<h4>"+entityInterface2.getNegativeNameButton()+"</h4>");
    }
%></h4>
<h4><%=entityInterface.getStatistic()[1]%></h4>
<p><%       out.print(entityInterface.getLossMessage());%></p>
<h4>${entityInterface.winMessage}</h4>
<hr>
<h4>${message}</h4>
<h4>${loss}</h4>
<h4><%=request.getAttribute("loss")%></h4>
<%--<h4><%= EntityQuest %></h4>--%>
<%--<h4><%= EntityQuest %></h4>--%>

<form method="POST" action="/init-servlet<%--/${countLevel}--%>">
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