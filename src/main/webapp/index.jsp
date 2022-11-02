<%@ page import="ru.javarush.quest.entity.Entity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="static/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
<h1>Start GAME!!!</h1>
<br/>
<%--Это не надо, без него работает--%>
<c:if test="${blank == null}">
    <c:set var="blank" scope="session" value="false"/>
</c:if>
<hr>

<c:if test="${blank}">
    <h4 class="one">Введите имя !!!</h4>
    <script>
        alert("Enter Name !!!")
    </script>
</c:if>

<h4>Введите имя</h4>
<form method="post" action="/start"<%--"/init-servlet"--%>>
    <input type="hidden" name="formname" value="prologue"/>
    <label>
        <c:if test="${username == null}">
            <input name="username" type="text" value="">
        </c:if>
        <c:if test="${username != null}">
            <input name="username" type="text" value="${username}"<%-- placeholder=${username}--%>>
        </c:if>
    </label>
    <br>
    <br>
    <span>Выбор языка / Language selection</span>
    <br>
    <br>
    <div>
        <input type="radio" id="choiceRU" name="choiceLanguage" value="RU" checked>
        <label for="choiceRU">RU</label>
    </div>
    <div>
        <input type="radio" id="choiceEN" name="choiceLanguage" value="EN">
        <label for="choiceEN">EN</label>
    </div>
    <input type="submit" value="Ответить"/>
</form>
<br>
<hr>

<c:if test="${blank_statistic}">
    <div class="statistic">
        <div>
            <div><b>${statistic[0]}</b></div>
            <div>${statistic[1]} <i><%= session.getAttribute("ip")%>
            </i></div>
            <div>${statistic[2]} <i><%= session.getAttribute("username") %>
            </i></div>
            <div>${statistic[3]} <i><%= session.getAttribute("gamesquanity")%>
            </i></div>
        </div>
    </div>
</c:if>

</body>
</html>