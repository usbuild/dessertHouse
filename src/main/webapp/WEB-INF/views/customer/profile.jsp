<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-1
  Time: 下午12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tr>
        <td>欢迎您：</td>
        <td>${user.name}</td>
        <td><a href="/customer/account" class="router-link">账户设置</a></td>
    </tr>
    <tr>
        <td>账户余额：</td>
        <td>${user.amount}</td>
        <td><a href="/customer/pay">立即充值</a></td>
    </tr>
    <tr>
        <td>状态：</td>
        <td>
            <c:choose>
                <c:when test="${user.status == 'nouse'}">
                    尚未激活
                </c:when>
                <c:when test="${user.status == 'active'}">
                    已激活
                </c:when>
                <c:when test="${user.status == 'cancel'}">
                    已被注销
                </c:when>
            </c:choose>
        </td>
        <td><a href="/customer/password">修改密码</a></td>
    </tr>
</table>
<a href="/j_spring_security_logout" class="customer-exit">退出</a>