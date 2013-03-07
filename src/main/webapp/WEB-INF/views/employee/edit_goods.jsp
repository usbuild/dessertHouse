<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-4
  Time: 下午6:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<td><input type="text" value="${goods.sid}" class="var-sid"/></td>
<td><input type="text" value="${goods.name}" class="var-name"/></td>
<td>
    <select value="${goods.goodsType.name}" class="var-type">
        <c:forEach var="item" items="${types}">
            <option value="${item.name}"
                    <c:if test="${goods.goodsType.name eq item.name}">selected="selected"</c:if>>${item.name}</option>
        </c:forEach>
    </select>
</td>
<td>
    <a class="btn goods-edit-confirm" href="#" data-id='${goods.id}'><i class="icon-ok"></i></a>
    <a class="btn goods-edit-cancel" href="#" data-id='${goods.id}'><i class="icon-remove"></i></a>
</td>