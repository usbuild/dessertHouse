<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-3
  Time: 上午8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-search form-store-search">
    <label>
        商品名称：
        <input type="text" class="input-large search-query" id="search-store-input">
    </label>
    <button class="btn">搜索</button>
</form>


搜索列表：
<table class="table" id="search-store-table">
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
<table class="table" id="buy-store-table">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>价格</th>
        <th>类型</th>
        <th>订购数</th>
        <th>操作</th>
    </tr>
</table>
<div id="total-amount">0</div>
<div class="div-box">
    <input type="text" id="cash-amount" value="0" placeholder="现金支付"><br><br>
    <input type="text" id="customer-info" placeholder="用户名"/>
</div>
<button class="btn btn-primary buy-submit">提交</button>
<div id="customer-info-div" class=""></div>
