<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-5
  Time: 下午1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>经理界面</title>
    <jsp:include page="../libs.jsp"/>
    <script type="text/javascript">
        require(['jquery', 'bootstrap', 'backbone', "apprise", "jquery.ui.effects"], function () {
            var baseUrl = "/user/manager";
            var startAdj = function () {
                $(".main-container").css("max-height", $(".manager-container").height() + 'px');
            };
            var endAdj = function () {
                $(".main-container").css("max-height", "").css("height", $(".manager-container").height() + 'px');
            };
            var routerFunc = function (url) {
                return function (id) {
                    var u = baseUrl + url;
                    if (id != undefined) u += "/" + id;
                    $.get(u, {}, function (e) {
                        $("li.active").removeClass("active");
                        $("ul.main-nav a[href $= '" + baseUrl + url + "']").parents("li").addClass("active");
                        $(".manager-container").hide("drop", {direction: "left"}, 300, function () {
                            startAdj();
                            $(this).html(e);
                            $(this).fadeIn(200);
                            endAdj();
                        });
                    });
                };
            };
            var Router = Backbone.Router.extend({
                routes: {
                    "user/manager/": "index",
                    "user/manager": "index",
                    "user/manager/shop/:id": "shop",
                    "user/manager/trend": "trend",
                    "user/manager/info": "info",
                    "user/manager/hot": "hot"
                },
                index: routerFunc("/"),
                shop: routerFunc("/shop"),
                trend: routerFunc("/trend"),
                info: routerFunc("/info"),
                hot: routerFunc("/hot")
            });
            var router = new Router;
            Backbone.history.start({pushState: true});

            $(document).on('click', '.router-link', function (evt) {
                evt.preventDefault();
                router.navigate($(this).attr("href"), true);
            });
            $(document).on("change", "#shop-select", function (evt) {
                router.navigate($(this).val(), true);
            });

        });
    </script>
</head>
<body class="user-body">
<div class="navbar navbar-inverse navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand router-link" href="/user/manager/">甜品屋</a>
            <ul class="nav main-nav">
                <li><a href="/user/manager/" class="router-link">会员信息</a></li>
                <li><a href="/user/manager/shop/1" class="router-link">销售状况</a></li>
                <li><a href="/user/manager/hot" class="router-link">热门商品</a></li>
                <li><a href="/user/manager/trend" class="router-link">趋势预测</a></li>
            </ul>

            <div class="nav-collapse collapse pull-right">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.name} <b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/manager/info" class="router-link">个人信息</a></li>
                            <li><a href="/user/j_spring_security_logout">注销</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="main-container">
    <div class="manager-container">
    </div>
</div>
</body>
</html>