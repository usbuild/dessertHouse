<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-26
  Time: 下午2:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/static/lib/seajs/sea.js"></script>
<script type="text/javascript">
    seajs.config({
        alias: {
            'jquery': 'lib/jquery-1.9.1.min.js',
            'bootstrap': 'lib/bootstrap/js/bootstrap.min.js'
        },
        base: '/static',
        plugins: ['text', 'shim'],

        shim: {
            'jquery': {
                exports: 'jQuery'
            },
            'bootstrap': {
            }
        }
    });

    seajs.use(['bootstrap'], function(){
    });
</script>
<link type="text/css" rel="stylesheet" href="/static/lib/bootstrap/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="/static/css/main.css"/>

