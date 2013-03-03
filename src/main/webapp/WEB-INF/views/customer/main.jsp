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
    require(['bootstrap', 'backbone', 'apprise'], function () {

        var routerFunc = function (url) {
            return function () {
                $.get("/customer/" + url, {}, function (data) {
                    $(".customer-container").slideUp(function () {
                        $(this).html(data).slideDown();
                    });
                });
            };
        };

        $(function () {
            var Router = Backbone.Router.extend({
                routes: {
                    "customer/account": "account",
                    "customer/": "index",
                    "customer": "index",
                    "customer/pay": "pay",
                    "customer/password": "password",
                    "customer/record": "record"
                },

                account: routerFunc("account"),
                index: routerFunc(""),
                pay: routerFunc("pay"),
                password: routerFunc("password"),
                record: routerFunc("record")
            });

            var router = new Router;
            Backbone.history.start({pushState: true});

            $('.customer-profile').hover(function () {
                $('.customer-profile-container').show();
            }, function () {
                $('.customer-profile-container').hide();
            });

            $(document).on('click', '.router-link', function (evt) {
                evt.preventDefault();
                router.navigate($(this).attr("href"), true);
            });
            $(document).on("click", '.order-detail-btn', function (evt) {
                evt.preventDefault();
                $.get("/customer/sale/" + $(this).attr("data-id"), {}, function (e) {
                    apprise(e);
                });
            });
            $(document).on("click", '.cancel-account', function (evt) {
                evt.preventDefault();
                $.get("/customer/disable", {}, function (e) {
                    if (e.code == 0) {
                        apprise("您的帐号已注销", {}, function () {
                            window.location.href = "/customer/login";
                        });
                    } else {
                        apprise("帐号注销失败");
                    }
                }, 'json');
            });


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

            var updateProfile = function () {
                $.get("/customer/profile", {}, function (data) {
                    $(".customer-profile-container").html(data);
                });
            };
            updateProfile();


            $(document).on('click', "#search-btn", function (evt) {
                evt.preventDefault();
                searchModel.fetch({data: {
                    key: $("#search-input").val(),
                    date: $("#form-search-date").val(),
                    shopId: $("#form-shop-id").val(),
                    page: 0
                }});
            });

            $(document).on("submit", "form.ajax-form", function (evt) {
                evt.preventDefault();
                $("form .error-msg").remove();
                $.post($(this).attr('action'), $(this).serialize(), function (data) {
                    $(".customer-container").html(data);
                    updateProfile();
                });
            });

            var orderModel = {};

            $(document).on("click", ".order-btn", function (evt) {
                evt.preventDefault();
                var store = searchModel.get('data')[$(this).attr("data-id")];
                if (orderModel[store.id] != undefined) {
                    apprise("已订购此商品");
                    return false;
                }
                apprise('请输入订购数目', {'input': true, 'textOk': '确认', 'textCancel': '取消'}, function (num) {
                    if (!/\d+/.test(num) || num > store['amount'] || num <= 0) {
                        apprise("数量输入非法");
                        return false;
                    } else {
                        store['amount'] = num;
                        $("#order-result-table").append(_.template($("#order-template").html(), {item: store}));
                        orderModel[store.id] = store;
                    }
                });
            });

            $(document).on("click", ".order-cancel", function (evt) {
                delete orderModel[$(this).attr('data-id')];
                $(this).parents("tr").remove();
            });

            $(document).on("click", ".order-submit", function (evt) {
                var d = [];
                var m = {};
                for (var a in orderModel) {
                    d.push(a);
                    m[a] = orderModel[a].amount;
                }
                if (d.length > 0) {
                    $.post("/customer/reserve", m, function (e) {
                        if (e.code == 0) {
                            apprise("订购成功", {}, function () {
                                updateProfile();
                                window.location.href = "/customer/record";
                            });
                        } else {
                            apprise(e.data);
                        }
                    }, 'json');
                }
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
        <td><@= item.goods.sid @></td>
        <td><@= item.goods.name @></td>
        <td><@= item.price @></td>
        <td><@= item.amount @></td>
        <td><@= item.goods.goodsType.name @></td>
        <td><a class="btn order-btn" href="#" data-id='<@= key@>'><i class="icon-ok"></i></a></td>
    </tr>
    <@ }); @>
</script>

<script type="text/html" id="order-template">
    <tr>
        <td><@= item.goods.sid @></td>
        <td><@= item.goods.name @></td>
        <td><@= item.price @></td>
        <td><@= item.goods.goodsType.name@></td>
        <td><@= item.amount @></td>
        <td><a class="btn order-cancel" href="#" data-id="<@= item.id @>"><i class="icon-remove"></i></a></td>
    </tr>
</script>
</head>
<body class="customer-body">

<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand router-link" href="/customer">甜品屋</a>

            <div class="nav-collapse collapse pull-right">
                <ul class="nav pull-right">
                    <li class="customer-profile"><a href="#">${user.name}</a>

                        <div class="customer-profile-container">

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