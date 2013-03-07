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


<form:form modelAttribute="storeForm" cssClass="form-inline" id="add-store-form">
    <form:select name="date" id="form-search-date" path="date">
        <c:forEach items="${dates}" var="date">
            <option value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>">
                <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
            </option>
        </c:forEach>
    </form:select>
    <form:select path="goodsId">
        <c:forEach items="${allGoods}" var="item">
            <form:option value="${item.id}">${item.name}</form:option>
        </c:forEach>
    </form:select>
    <form:label path="amount">数量</form:label>
    <form:input path="amount"/>
    <form:label path="price">价格</form:label>
    <form:input path="price"/>
    <button type="submit" id="add-store-btn" class="btn btn-primary">添加</button>
</form:form>


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
                <a class="btn del-store-btn" href="#" data-id='${item.id}'><i class="icon-remove"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>