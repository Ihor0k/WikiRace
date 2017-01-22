<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .page-title:last-child, .page-title:first-child {
            font-weight: bold;
        }

        .page-title:not(:last-child):after {
            content: ' → ';
        }
    </style>
</head>
<body>
<div id="pages-wrapper">
    <c:forEach var="page" items="${game.pages}">
        <span class="page-title">${page.title}</span>
    </c:forEach>
</div>
<spring:message code="game.clicks"/>: ${game.pages.size()-1}
<a href="<c:url value="/game/new"/>">Start new game</a>
<script type="text/javascript">
    if (inIframe())
        window.top.location.href = window.location.href;

    function inIframe() {
        try {
            return window.self !== window.top;
        } catch (e) {
            return true;
        }
    }
</script>
</body>
</html>