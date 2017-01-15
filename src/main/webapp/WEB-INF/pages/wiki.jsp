<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>${page.title}</title>
    <spring:url value="/resources/css/wiki.css" var="stylesheet"/>
    <link rel="stylesheet" href="${stylesheet}"/>
    <meta name="title" content="${page.title}">
    <meta name="url" content="${page.url}">
</head>
<body>
${page.html}
</body>
</html>
