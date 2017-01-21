<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <spring:url value="/resources/css/main.css" var="stylesheet"/>
    <spring:url value="/resources/js/main.js" var="script"/>
    <link rel="stylesheet" href="${stylesheet}">
</head>
<body>
<c:url var="endWiki" value="${game.endPage.url}"/>
<c:url var="src" value="/wiki/${game.startPage.pageName}"/>
<c:url var="surrender" value="/game/new"/>
<c:if test="${not empty game.pages.peekLast()}">
    <c:set var="src" value="/wiki/${game.pages.peekLast().pageName}"/>
</c:if>
<div id="header">
    <div id="logo">
        <div id="temp" style="width: 150px; height: 50px"></div>
    </div>
    <div id="surrender-wrapper">
        <a class="button" href="${surrender}">Surrender</a>
    </div>
    <div id="game-info">
        <div id="goal-wrapper">
            Goal: <a id="goal" href="${endWiki}" target="_blank">${game.endPage.title}</a>
            <div id="popup">${game.endPage.description}</div>
        </div>
        <div id="clicks-count-wrapper">
            <div class="right">Clicks:
                <span id="clicks-count">${game.pages.size() <= 1 ? 0 : game.pages.size() - 1}</span>
            </div>
        </div>
    </div>
    <div id="authorization">
        <a id="sign-up" class="button">Sign Up</a>
        <a id="log-in" class="button">Log In</a>
    </div>
</div>
<div id="wiki-wrapper">
    <iframe id="wiki" src="${src}"></iframe>
</div>
<script type="text/javascript" src="${script}"></script>
</body>
</html>
