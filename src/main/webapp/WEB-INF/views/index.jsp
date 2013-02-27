<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-1-16
  Time: 下午3:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>欢迎光临甜品屋</title>
    <jsp:include page="libs.jsp"/>
</head>
<body class="index-body">
<sec:authorize access="isAuthenticated()">
    欢迎您！ <sec:authentication property="name"/>
</sec:authorize>

<sec:authorize access="isAnonymous()">
    <a href="/customer/login" class="btn">用户登录</a>
    <a href="/customer/signup" class="btn">用户注册</a>
</sec:authorize>
</body>
</html>