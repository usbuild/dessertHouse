<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-26
  Time: 下午2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户登录</title>
    <jsp:include page="../libs.jsp"/>
    <script type="text/javascript">
        seajs.use(['jquery'], function ($) {

        });
    </script>
</head>
<body>
<div class="login-form">
    <form name='f' action='/customer/j_spring_security_check' method='POST' class="form-horizontal">
        <fieldset>
            <legend><i>甜品店</i> &nbsp; &nbsp;&nbsp;用户登录</legend>
            <c:if test="${not empty param.error}">
                <div class="alert alert-error">
                        ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
                </div>
            </c:if>
            <div class="control-group">
                <label class="control-label" for="j_username">用户名</label>

                <div class="controls">
                    <input type='text' name='j_username' value='' id="j_username">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="j_password">密码</label>

                <div class="controls">
                    <input type='password' name='j_password' id="j_password"/>
                </div>
            </div>

            <div class="control-group">
                <label class="controls" for="remember_me">
                    <input id="remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/>
                    记住我
                </label>
            </div>

            <input name="submit" type="submit" value="登录" class="btn btn-primary"/>
        </fieldset>
    </form>
</div>
</body>
</html>