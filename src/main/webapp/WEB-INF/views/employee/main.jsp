<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-2
  Time: 下午9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>店员后台</title>
<jsp:include page="../libs.jsp"/>
<script type="text/javascript">
require(["jquery", "bootstrap", "backbone", "apprise", "jquery.ui.effects"], function () {
    var baseUrl = "/user/employee";

    var adjMainContainer = function (callback) {
        var mainContainer = $(".main-container");
        var employeeContainer = $(".employee-container");
        mainContainer.css("max-height", employeeContainer.height() + 'px');
        callback(employeeContainer);
        mainContainer.css("max-height", "");
        mainContainer.css("height", employeeContainer.height() + 'px');
    };

    var models = [];
    var registerModel = function (model) {
        models.push(model);
    };
    var invalidateModels = function () {
        for (var x in models) {
            models[x].clear();
        }
    };
    var routerFunc = function (url) {
        return function () {
            $.get(baseUrl + url, {}, function (e) {
                invalidateModels();

                $("li.active").removeClass("active");
                $("ul.main-nav a[href $= '" + baseUrl + url + "']").parents("li").addClass("active");
                $(".employee-container").hide("drop", {direction: "left"}, 300, function () {
                    adjMainContainer(function (ec) {
                        ec.html(e);
                        ec.fadeIn(200);
                    });
                });
            });
        };
    };
    var Router = Backbone.Router.extend({
        routes: {
            "user/employee": "index",
            "user/employee/": "index",
            "user/employee/store": "store",
            "user/employee/goods": "goods",
            "user/employee/info": "info"
        },
        index: routerFunc("/"),
        store: routerFunc("/store"),
        info: routerFunc("/info"),
        goods: routerFunc("/goods")
    });

    var router = new Router;
    Backbone.history.start({pushState: true});
    $(document).on('click', '.router-link', function (evt) {
        evt.preventDefault();
        router.navigate($(this).attr("href"), true);
    });

    var SearchModel = Backbone.Model.extend({
        url: "/user/employee/search"
    });


    var SearchView = Backbone.View.extend({
        initialize: function () {
            this.model.on("change", this.render, this);
        },
        render: function () {
            var model = this.model;
            var $container = $(this.$el.selector);
            adjMainContainer(function () {
                $container.html(_.template($('#search-store-template').html(), {'model': model}));
            });
        }
    });

    var StoreView = Backbone.View.extend({
        initialize: function () {
            this.model.on("change", this.render, this);
        },
        render: function () {
            var model = this.model;
            var $container = $(this.$el.selector);
            adjMainContainer(function () {
                $container.html(_.template($('#store-template').html(), {'model': model}));
            });
        }
    });


    var searchModel = new SearchModel;
    new SearchView({model: searchModel, el: "#search-store-table"});

    var StoreModel = Backbone.Model.extend({
        url: "/user/employee/store/list/"
    });

    var storeModel = new StoreModel;
    new StoreView({model: storeModel, el: "#store-table"});

    registerModel(searchModel);
    registerModel(storeModel);
    $(document).on("change", "#form-search-date", function () {
        storeModel.fetch({
            data: {
                date: $("#form-search-date").val()
            }
        });
    });


    $(document).on("submit", ".form-store-search", function (evt) {
        evt.preventDefault();
        searchModel.fetch({data: {
            key: $("#search-store-input").val()
        }
        });
    });

    //handle store management

    $(document).on("click", "#store-table .buy-btn", function () {
        var item = $(this);

    });

    $(document).on("click", ".add-goods-type", function (evt) {
        evt.preventDefault();
        apprise("请输入分类名称", {input: true}, function (r) {
            if (r === false) return;
            $.post("/user/employee/type/add", {name: r}, function (e) {
                if (e.code == 0) {
                    $("#type-combo").append('<option value="' + r + '">' + r + '</option>');
                } else {
                    apprise("添加失败!");
                }
            }, "json")
        });
    });
    $(document).on("click", ".add-goods", function (evt) {
        evt.preventDefault();
        $.post("/user/employee/goods/add", $(".add-goods-form").serialize(), function (e) {
            if (e.code == 0) {
                $(".add-goods-form")[0].reset();
                $.get("/user/employee/goods/list", {}, function (e) {
                    adjMainContainer(function () {
                        $("#goods-table").replaceWith(e);
                    });
                });
            } else {
                apprise("添加失败");
            }
        }, 'json');
    });

    var orderModel = {};

    var updateTotalAmount = function () {
        var total = 0;
        for (var i in orderModel) {
            total += orderModel[i].price * orderModel[i].amount;
        }
        $("#total-amount").val(total);
    };

    $(document).on("click", "#search-store-table .buy-btn", function (evt) {
        evt.preventDefault();
        var store = searchModel.get('data')[$(this).attr("data-id")];
        if (orderModel[store.id] != undefined) {
            apprise("已购买此商品");
            return;
        }
        apprise('请输入订购数目', {'input': true, 'textOk': '确认', 'textCancel': '取消'}, function (num) {
            if (num === false) return;
            if (!/\d+/.test(num) || num > store['amount'] || num <= 0) {
                apprise("数量输入非法");
            } else {
                store['amount'] = num;
                adjMainContainer(function () {
                    $("#buy-store-table").append(_.template($("#buy-template").html(), {item: store}))
                });
                orderModel[store.id] = store;
                updateTotalAmount();
            }
        });
    });


    $(document).on("click", ".buy-cancel", function () {
        delete orderModel[$(this).attr('data-id')];
        var tr = $(this);
        updateTotalAmount();
        adjMainContainer(function () {
            tr.parents("tr").remove();
        });
    });
    var customer = null;
    $(document).on("keydown", "#customer-info", function (evt) {
        if (evt.keyCode == 13) {
            $.get("/user/employee/customer/" + $(this).val(), function (e) {
                var content;
                adjMainContainer(function () {
                    if (e.code == 0) {
                        customer = e.data;
                        $("#customer-info-div").removeClass("alert alert-success alert-danger").addClass("alert alert-success").html("余额:" + customer.amount);
                    } else {
                        $("#customer-info-div").removeClass("alert alert-success alert-danger").addClass("alert alert-danger").html("查无此人");
                        customer = null;
                    }
                })
            }, 'json');
        }
    });
    $(document).on("click", ".buy-submit", function () {
        var d = [];
        var m = {};
        for (var a in orderModel) {
            d.push(a);
            m[a] = orderModel[a].amount;
        }
        if (d.length > 0) {
            $.post("/user/employee/bought", {goods: m, customer: $("#customer-info").val(), pay: $("#cash-amount").val()}, function (e) {

                if (e.code == 0) {
                    apprise("订购成功", {}, function () {
                        routerFunc("/")();
                    });
                } else {
                    apprise(e.data);
                }

            }, 'json');
        }
    });

});
</script>
<script type="text/html" id="search-store-template">
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
        <td><a class="btn buy-btn" href="#" data-id='<@= key@>'><i class="icon-ok"></i></a></td>
    </tr>
    <@ }); @>
</script>
<script type="text/html" id="buy-template">
    <tr>
        <td><@= item.goods.sid @></td>
        <td><@= item.goods.name @></td>
        <td><@= item.price @></td>
        <td><@= item.goods.goodsType.name@></td>
        <td><@= item.amount @></td>
        <td><a class="btn buy-cancel" href="#" data-id="<@= item.id @>"><i class="icon-remove"></i></a></td>
    </tr>
</script>

<script type="text/html" id="store-template">
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
        <td>
            <a class="btn buy-btn" href="#" data-id='<@= key@>'><i class="icon-edit"></i></a>
            <a class="btn buy-btn" href="#" data-id='<@= key@>'><i class="icon-remove"></i></a>
        </td>
    </tr>
    <@ }); @>
</script>
</head>
<body class="user-body">
<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand router-link" href="/user/employee/">甜品屋</a>
            <ul class="nav main-nav">
                <li><a href="/user/employee/" class="router-link">销售</a></li>
                <li><a href="/user/employee/store" class="router-link">管理库存</a></li>
                <li><a href="/user/employee/goods" class="router-link">管理商品</a></li>
            </ul>
            <div class="nav-collapse collapse pull-right">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.name} <b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/employee/info" class="router-link">个人信息</a></li>
                            <li><a href="/user/j_spring_security_logout">注销</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="main-container">
    <div class="employee-container"></div>
</div>
</body>
</html>