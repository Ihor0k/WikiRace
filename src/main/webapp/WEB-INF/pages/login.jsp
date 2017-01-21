<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<form id="form-sign-in" action="<c:url value="/login"/>" method="post">
    <span class="form-header"></span>
    <input name="username" placeholder="Username" autofocus="true">
    <input name="password" placeholder="Password" type="password">
    <form:errors path="password"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button class="button" type="submit">Log in</button>
    <a href="<c:url value="/registration"/>">Create an account</a>
</form>
</body>
</html>
