<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-4
  Time: 下午2:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table class="table" id="goods-table">
    <tr>
        <th>序号</th>
        <th>编号</th>
        <th>名称</th>
        <th>类型</th>
        <th>操作</th>
    </tr>
    <c:forEach var="item" items="${goodsList}" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${item.sid}</td>
            <td>${item.name}</td>
            <td>${item.goodsType.name}</td>
            <td>
                <a class="btn buy-btn" href="#" data-id='${item.id}'><i class="icon-edit"></i></a>
                <a class="btn buy-btn" href="#" data-id='${item.id}'><i class="icon-remove"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>