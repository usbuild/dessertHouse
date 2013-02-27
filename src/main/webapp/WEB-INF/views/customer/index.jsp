<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-26
  Time: 下午6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Dessert House 甜品屋</title>
    <jsp:include page="../libs.jsp"/>

    <script type="text/javascript">
        require(['bootstrap'], function () {
            $('.customer-profile').hover(function () {
                $('.customer-profile-container').show();
            }, function () {
                $('.customer-profile-container').hide();
            });
        });
    </script>
</head>
<body class="customer-body">

<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="javascript:;">甜品屋</a>

            <div class="nav-collapse collapse pull-right">
                <form class="navbar-search pull-left">
                    <input type="text" class="search-query" placeholder="搜索商品">
                </form>


                <ul class="nav pull-right">
                    <li class="customer-profile"><a href="#">${user.name}</a>

                        <div class="customer-profile-container">
                            <table>
                                <tr>
                                    <td>欢迎您：</td>
                                    <td>${user.name}</td>
                                    <td><a href="#">账户设置</a></td>
                                </tr>
                                <tr>
                                    <td>账户余额：</td>
                                    <td>${user.amount}</td>
                                    <td><a href="#">立即充值</a></td>
                                </tr>
                                <tr>
                                    <td>状态：</td>
                                    <td colspan="2">${user.status}</td>
                                </tr>
                            </table>
                            <a href="/j_spring_security_logout" class="customer-exit">退出</a>
                        </div>
                    </li>

                </ul>
            </div>
        </div>
    </div>
</div>

<div class="customer-container">
    <form class="form-search">
        <label>
            商品名称：
            <input type="text" class="input-large search-query">
        </label>
        <button type="submit" class="btn">搜索</button>
    </form>
    搜索列表：
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

            <td><a class="btn" href="#"><i class="icon-ok"></i></a></td>
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
</div>
</body>
</html>