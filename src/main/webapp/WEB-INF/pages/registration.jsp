<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<form:form id="form-sign-up" modelAttribute="user" method="post">
    <span class="form-header"></span>
    <spring:bind path="username">
        <form:input type="password" path="username" placeholder="Username" autofocus="true"/>
        <form:errors path="username"/>
    </spring:bind>
    <spring:bind path="password">
        <form:input type="password" path="password" placeholder="Password"/>
        <form:errors path="password"/>
    </spring:bind>
    <button class="button" type="submit">Submit</button>
</form:form>
</body>
</html>
