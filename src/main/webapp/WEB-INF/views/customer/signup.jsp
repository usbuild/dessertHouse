<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-26
  Time: 下午8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>用户注册</title>
    <jsp:include page="../libs.jsp"/>
</head>
<body>
<div class="signup-form">
    <form:form modelAttribute="customerSignUpForm" cssClass="form-horizontal">
        <form:errors path="*" cssClass="alert alert-error"/>
        <form:label path="name">用户名:</form:label><form:input path="name"/>
        <form:label path="password">密码:</form:label><form:password path="password"/>
        <form:label path="repassword">确认密码:</form:label><form:password path="repassword"/><br><br>
        <input type="submit" value="提交" class="btn btn-primary">
    </form:form>
</div>
</body>
</html>