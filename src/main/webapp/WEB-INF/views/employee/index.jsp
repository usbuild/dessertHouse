<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-3
  Time: 上午8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-search">
    <label>
        商品名称：
        <input type="text" class="input-large search-query" id="search-input">
    </label>
    <button class="btn" id="search-btn">搜索</button>
</form>


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
商品列表
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