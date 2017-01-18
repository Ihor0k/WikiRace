<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        .page-title:not(:last-child):after {
            content: ' → ';
        }
    </style>
</head>
<body>
You reached from ${game.startPage.title} to ${game.endPage.title} for ${game.pages.size()-1} clicks:
<br>
<div id="pages-wrapper">
    <c:forEach var="page" items="${game.pages}">
        <span class="page-title">${page.title}</span>
    </c:forEach>
</div>
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