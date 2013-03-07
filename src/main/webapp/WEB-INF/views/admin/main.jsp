<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-7
  Time: 下午6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../libs.jsp"/>
    <script type="text/javascript">
        require(['jquery', 'bootstrap', 'backbone', "jquery.ui.effects"], function () {
            var baseUrl = "/user/admin";
            var startAdj = function () {
                $(".main-container").css("max-height", $(".admin-container").height() + 'px');
            };
            var endAdj = function () {
                $(".main-container").css("max-height", "").css("height", $(".admin-container").height() + 'px');
            };
            var routerFunc = function (url) {
                return function (id) {
                    var u = baseUrl + url;
                    if (id != undefined) u += "/" + id;
                    $.get(u, {}, function (e) {
                        $("li.active").removeClass("active");
                        $("ul.main-nav a[href $= '" + baseUrl + url + "']").parents("li").addClass("active");
                        $(".admin-container").hide("drop", {direction: "left"}, 300, function () {
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
                    "user/admin/": "index",
                    "user/admin": "index",
                    "user/admin/info": "info",
                    "user/admin/discount": "discount"
                },
                index: routerFunc("/"),
                info: routerFunc("/info"),
                discount: routerFunc("/discount")
            });
            var router = new Router;
            Backbone.history.start({pushState: true});

            $(document).on('click', '.router-link', function (evt) {
                evt.preventDefault();
                router.navigate($(this).attr("href"), true);
            });
        });
    </script>
</head>
<body class="user-body">
<div class="navbar navbar-inverse navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand router-link" href="/user/admin/">甜品屋</a>
            <ul class="nav main-nav">
                <li><a href="/user/admin/" class="router-link">成员管理</a></li>
                <li><a href="/user/admin/discount" class="router-link">折扣管理</a></li>
            </ul>

            <div class="nav-collapse collapse pull-right">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.name} <b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/user/admin/info" class="router-link">个人信息</a></li>
                            <li><a href="/user/j_spring_security_logout">注销</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="main-container">
    <div class="admin-container">
    </div>
</div>
</body>
</html>