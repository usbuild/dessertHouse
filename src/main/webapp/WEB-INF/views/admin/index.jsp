<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-7
  Time: 下午6:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table">
    <tr>
        <th>用户名</th>
        <th>身份</th>
        <th>店铺</th>
        <th>操作</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.name}</td>
            <td>${user.role}</td>
            <td><c:if test="${user.shop != null}"> ${user.shop.name}</c:if></td>
            <td>
                <a class="btn goods-edit-btn" href="#" data-id='${user.id}'><i class="icon-edit"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>