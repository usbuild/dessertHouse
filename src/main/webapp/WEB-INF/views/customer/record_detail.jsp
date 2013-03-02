<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-2
  Time: 下午5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>种类</th>
        <th>数量</th>
        <th>价格</th>
    </tr>
    <c:forEach items="${list}" varStatus="status" var="item">
        <tr>
            <td>${status.index + 1}</td>
            <td>${item.store.goods.name}</td>
            <td>${item.store.goods.goodsType.name}</td>
            <td>${item.amount}</td>
            <td>${item.store.price}</td>
        </tr>
    </c:forEach>
</table>