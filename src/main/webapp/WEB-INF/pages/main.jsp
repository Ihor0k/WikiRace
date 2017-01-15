<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="from">From: ${game.startPage.title}</div>
<div id="to">To: ${game.endPage.title}</div>
<a href="<c:url value="/game"/>">Start game</a>
</body>
</html>
