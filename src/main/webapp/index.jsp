<%@ page import="ru.javarush.quest.entity.EntityQuest" %>
<%@ page import="ru.javarush.quest.entity.EntityInterface" %>
<%@ page import="ru.javarush.quest.entity.EntityStatistics" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
</head>
<body>
<%
    EntityInterface entityInterfaceSession = (EntityInterface) session.getAttribute("entityInterface");
    EntityInterface entityInterfaceRequest = (EntityInterface) request.getAttribute("entityInterface");
%>
<div id="container">
    <h1>Start GAME!!!</h1>
    <br/>
    <%--Это не надо, без него работает--%>
    <c:if test="${blank == null}">
        <c:set var="blank" scope="session" value="false"/>
    </c:if>
    <hr>
    <c:if test="${blank}">
    <h4 class="one">Введите имя!/Enter name!</h4>
        <%-- bootstrap alert--%>
    <div id="alert" class="alert alert-warning alert-dismissible fade show" role="alert">
        <strong> Enter Name !!!</strong> The name cannot contain characters * / \
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    </c:if>

    <%--    bootstrap form--%>
    <form method="post" action="/start">
        <div class="form-group row">
            <input type="hidden" name="formname" value="prologue"/>
            <label class="col-sm-2 col-form-label">Enter name</label>
            <c:if test="${username == null}">
                <div class="col-sm-10">
                    <input name="username" type="text" class="form-control" value="">
                </div>
            </c:if>
            <c:if test="${username != null}">
                <div class="col-sm-10">
                    <input name="username" type="text" class="form-control" value="${username}">
                </div>
            </c:if>
        </div>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-sm-2 col-form-label ">Language selection</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="choiceRU" name="choiceLanguage" value="RU"
                               checked>
                        <label class="form-check-label" for="choiceRU">
                            RU
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" id="choiceEN" name="choiceLanguage" value="EN">
                        <label class="form-check-label" for="choiceEN">
                            EN
                        </label>
                    </div>
                </div>
            </div>
        </fieldset>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Start / Поехали</button>
            </div>
        </div>
    </form>
    <hr>
    <c:if test="${username != null}">
    <div class="statistic">
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
    </div>
    </c:if>
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
<%--При подключении bootstrap, на всякий случай, эту строку сделать последней, что бы свой css то-же работал  --%>
<link href="static/main.css" rel="stylesheet">
</html>