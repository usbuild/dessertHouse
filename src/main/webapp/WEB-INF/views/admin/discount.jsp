<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-7
  Time: 下午6:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table">
    <tr>
        <th>等级</th>
        <th>优惠</th>
        <th>修改</th>
    </tr>
    <c:forEach var="item" items="${discount}">
        <tr>
            <td>${item.level}</td>
            <td>${item.discount}</td>
            <td>
                <a class="btn discount-edit-btn" href="#" data-id='${item.id}'><i class="icon-edit"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>