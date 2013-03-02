<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-2
  Time: 下午4:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table">
    <tr>
        <th>编号</th>
        <th>总额</th>
        <th>消费时间</th>
        <th>详细信息</th>
    </tr>

    <c:forEach items="${reserves}" var="reserve" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${reserve.total}</td>
            <td><fmt:formatDate value="${reserve.createTime}" pattern="yyyy-MM-dd h:m:s"/></td>
            <td><a class="btn order-detail-btn" href="javascript:;" data-id="${reserve.id}"><i class="icon-th"></i></a></td>
        </tr>
    </c:forEach>
</table>