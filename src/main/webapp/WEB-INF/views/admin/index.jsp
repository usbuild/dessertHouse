<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-7
  Time: 下午6:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form:form modelAttribute="user" cssClass="form-inline">
    <form:label path="name">用户名</form:label>
    <form:input path="name"/>
    <form:label path="password">密码</form:label>
    <form:password path="password"/>
    <form:label path="repassword">重复密码</form:label>
    <form:password path="repassword"/>
    <br><br>
    <form:label path="role">身份</form:label>
    <form:select path="role">
        <form:option value="employee">店员</form:option>
        <form:option value="manager">经理</form:option>
        <form:option value="admin">管理员</form:option>
    </form:select>
    <form:label path="shopId">所属店铺</form:label>
    <form:select path="shopId">
        <c:forEach var="shop" items="${shops}">
            <form:option value="${shop.id}">${shop.name}</form:option>
        </c:forEach>
    </form:select>
    <input type="submit" class="btn btn-primary" value="添加">
</form:form>
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