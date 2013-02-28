<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-28
  Time: 下午3:07
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
预定列表：
<table class="table">
    <tr>
        <th>编号</th>
        <th>名称</th>
        <th>价格</th>
        <th>数量</th>
        <th>类型</th>
        <th>操作</th>
    </tr>
    <tr>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
        <td>5</td>
        <td><a class="btn" href="#"><i class="icon-remove"></i></a></td>
    </tr>
</table>