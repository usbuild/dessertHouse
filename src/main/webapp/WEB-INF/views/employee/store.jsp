<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-3
  Time: 上午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<select name="date" id="form-search-date">
    <c:forEach items="${dates}" var="date">
        <option value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>">
            <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></option>
    </c:forEach>
</select>
<br>

<table class="table" id="store-table">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>价格</th>
        <th>数量</th>
        <th>类型</th>
        <th>操作</th>
    </tr>
    <c:forEach var="item" items="${goods}" varStatus="status">
        <tr>
            <td>${item.goods.sid}</td>
            <td>${item.goods.name}</td>
            <td>${item.price}</td>
            <td>${item.amount}</td>
            <td>${item.goods.goodsType.name}</td>
            <td>
                <a class="btn buy-btn" href="#" data-id='${item.id}'><i class="icon-edit"></i></a>
                <a class="btn buy-btn" href="#" data-id='${item.id}'><i class="icon-remove"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>