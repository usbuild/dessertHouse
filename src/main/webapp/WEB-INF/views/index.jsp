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
<div class="index-main-div clearfix">
    <div class="logo">
        <img src="/static/images/logo.png" alt="logo" width="170px">
    </div>
    <div class="content">

        <sec:authorize access="isAuthenticated()">
            <div class="login">欢迎您, <a href="/customer/"><sec:authentication property="name"/></a>&nbsp;!</div>
        </sec:authorize>

        <sec:authorize access="isAnonymous()">
            <div class="not-login">
                <a href="/customer/login" class="btn">用户登录</a>
                <a href="/customer/signup" class="btn">用户注册</a>
            </div>
        </sec:authorize>
    </div>
</div>
<hr>
<div class="footer">
    &copy;<a href="https://github.com/usbuild">Usbuild</a>, All Right Reserved. 2013
</div>
</body>
</html>