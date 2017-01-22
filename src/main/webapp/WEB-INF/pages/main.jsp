<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="from"><spring:message code="game.page.from"/>: ${game.startPage.title}</div>
<div id="to"><spring:message code="game.page.to"/>: ${game.endPage.title}</div>
<a href="<c:url value="/game"/>"><spring:message code="game.start"/></a>
</body>
</html>
