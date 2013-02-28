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
        require(['bootstrap', 'backbone'], function () {
            $(function () {
                var Router = Backbone.Router.extend({
                    routes: {
                        "customer/account": "account",
                        "customer/": "index",
                        "customer": "index",
                        "customer/pay": "pay"
                    },

                    account: function () {
                        $.get("/customer/account", {}, function (data) {
                            $(".customer-container").slideUp(function () {
                                $(this).html(data).slideDown();
                            });
                        });
                    },

                    index: function () {
                        $.get("/customer", {}, function (data) {
                            $(".customer-container").slideUp(function () {
                                $(this).html(data).slideDown();
                            });
                        });
                    },
                    pay: function () {
                        $.get("/customer/pay", {}, function (data) {
                            $(".customer-container").slideUp(function () {
                                $(this).html(data).slideDown();
                            });
                        });
                    }
                });
                var router = new Router;
                Backbone.history.start({pushState: true});

                $('.customer-profile').hover(function () {
                    $('.customer-profile-container').show();
                }, function () {
                    $('.customer-profile-container').hide();
                });

                $('.router-link').click(function (evt) {
                    evt.preventDefault();
                    router.navigate($(this).attr("href"), true);
                });

                /*
                 Underscore setting
                 */
                _.templateSettings = {
                    interpolate: /\<\@\=(.+?)\@\>/gim,
                    evaluate: /\<\@([\s\S]+?)\@\>/gim,
                    escape: /\<\@\-(.+?)\@\>/gim
                };

                var SearchModel = Backbone.Model.extend({
                    url: '/customer/search/'
                });
                var searchModel = new SearchModel;

                var SearchView = Backbone.View.extend({
                    initialize: function () {
                        this.model.on("change", this.render, this);
                        this.el = "#search-result-table";
                    },
                    render: function () {
                        $(this.el).html(_.template($('#search-template').html(), {'model': this.model}));
                    }
                });
                new SearchView({model: searchModel});

                $(document).on('click', "#search-btn", function (evt) {
                    evt.preventDefault();
                    searchModel.fetch({data: {
                        key: $("#search-input").val(),
                        page: 0
                    }});
                });

            });
        });
    </script>

    <script type="text/html" id="search-template">
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>价格</th>
            <th>数量</th>
            <th>类型</th>
            <th>操作</th>
        </tr>
        <@ _.each(model.get('data'), function(item, key, list) { @>
        <tr>
            <td><@= item.sid @></td>
            <td><@= item.name @></td>
            <td><@= item.price @></td>
            <td><@= item.amount @></td>
            <td><@= item.goodsType.name @></td>
            <td><a class="btn" href="#"><i class="icon-ok"></i></a></td>
        </tr>
        <@ }); @>
    </script>
</head>
<body class="customer-body">

<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand router-link" href="/customer">甜品屋</a>

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
                                    <td><a href="/customer/account" class="router-link">账户设置</a></td>
                                </tr>
                                <tr>
                                    <td>账户余额：</td>
                                    <td>${user.amount}</td>
                                    <td><a href="/customer/pay">立即充值</a></td>
                                </tr>
                                <tr>
                                    <td>状态：</td>
                                    <td>${user.status}</td>
                                    <td><a href="/customer/changepass">修改密码</a></td>
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

</div>
</body>
</html>