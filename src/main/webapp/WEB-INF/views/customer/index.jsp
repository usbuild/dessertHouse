<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-26
  Time: 下午6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Dessert House 甜品屋</title>
</head>
<body>
欢迎您<sec:authentication property="name"/>
<a href="<c:url value="/j_spring_security_logout"/>">注销</a>
</body>
</html>