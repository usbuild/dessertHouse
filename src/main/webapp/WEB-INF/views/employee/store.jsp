<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-3
  Time: 上午9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<form class="form-search">--%>
    <%--<label for="form-shop-id">分店</label>--%>
    <%--<select name="shop_id" id="form-shop-id">--%>
        <%--<c:forEach items="${shops}" var="shop">--%>
            <%--<option value="${shop.id}">${shop.name}</option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>
    <%--<label for="form-search-date">日期</label>--%>
    <%--<select name="date" id="form-search-date">--%>
        <%--<c:forEach items="${dates}" var="date">--%>
            <%--<option value="<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>">--%>
            <%--<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></option>--%>
        <%--</c:forEach>--%>
    <%--</select>--%>
    <%--<label>--%>
        <%--商品名称：--%>
        <%--<input type="text" class="input-large search-query" id="search-input">--%>
    <%--</label>--%>
    <%--<button class="btn" id="search-btn">搜索</button>--%>
<%--</form>--%>


搜索列表：
<table class="table" id="search-result-table">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>价格</th>
        <th>数量</th>
        <th>类型</th>
        <th>操作</th>
    </tr>
</table>
预定列表(<a href="/customer/record" class="router-link">订购记录</a> )：
<table class="table" id="order-result-table">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>价格</th>
        <th>类型</th>
        <th>订购数</th>
        <th>操作</th>
    </tr>
</table>
<button class="btn btn-primary order-submit">提交</button>