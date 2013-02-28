<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-26
  Time: 下午2:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/static/lib/require.js"></script>
<script type="text/javascript">
    require.config({
        baseUrl: '/static',
        paths: {
            'jquery': 'lib/jquery-1.9.1.min',
            'bootstrap': 'lib/bootstrap/js/bootstrap.min',
            'underscore': 'lib/underscore-min',
            'backbone': 'lib/backbone-min'
        },

        shim: {
            'jquery': {
                exports: 'jQuery'
            },
            'underscore': {
                exports: '_'
            },
            'backbone': {
                deps: ['underscore'],
                exports: 'Backbone'
            },
            'bootstrap': {
                deps: ['jquery']
            }
        }
    });

</script>
<link type="text/css" rel="stylesheet" href="/static/lib/bootstrap/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="/static/css/main.css"/>

