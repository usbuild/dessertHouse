<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-8
  Time: 下午1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/user/admin/add_shop" class="form-inline" method="post" id="add-shop-form">
    <label for="shop_name">名称</label>
    <input type="text" id="shop_name"/>
    <input type="submit" class="btn" value="添加"/>
</form>
<table class="table">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>操作</th>
    </tr>
    <c:forEach var="shop" items="${shops}">
        <tr>
            <td>${shop.id}</td>
            <td>${shop.name}</td>
            <td>
                <a class="btn shop-delete-btn" href="#" data-id='${shop.id}'><i class="icon-remove"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>