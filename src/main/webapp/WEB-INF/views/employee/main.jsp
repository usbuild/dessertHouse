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
            var routerFunc = function (url) {
                return function () {
                    $.get(baseUrl + url, {}, function (e) {
                        $("li.active").removeClass("active");
                        $("ul.main-nav a[href $= '" + baseUrl + url + "']").parents("li").addClass("active");
                        $(".employee-container").hide("slide", {direction: "left"}, 300, function () {
                            var mainContainer = $(".main-container");
                            mainContainer.css("max-height", $(this).height() + 'px');
                            $(this).html(e);
                            $(this).show("slide", {direction: "right"}, 300);
                            mainContainer.css("max-height", "");
                            mainContainer.css("height", $(this).height() + 'px');
                        });
                    });
                };
            };
            var Router = Backbone.Router.extend({
                routes: {
                    "user/employee": "index",
                    "user/employee/": "index",
                    "user/employee/store": "store",
                    "user/employee/info": "info"
                },
                index: routerFunc("/"),
                store: routerFunc("/store"),
                info: routerFunc("/info")
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

<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand router-link" href="/user/employee/">甜品屋</a>
            <ul class="nav main-nav">
                <li><a href="/user/employee/" class="router-link">销售</a></li>
                <li><a href="/user/employee/store" class="router-link">管理商品</a></li>
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