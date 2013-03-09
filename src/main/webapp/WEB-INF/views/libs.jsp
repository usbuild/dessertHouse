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
            'backbone': 'lib/backbone-min',
            'jquery.form': 'lib/jquery.form',
            'apprise': 'lib/apprise/apprise-1.5.min',
            'jquery.ui.effects': 'lib/jquery-ui-effects.min',
            'highcharts':'lib/highcharts/highcharts'
        },

        shim: {
            'jquery': {
                exports: 'jQuery'
            },
            'underscore': {
                exports: '_'
            },
            'backbone': {
                deps: ['jquery','underscore'],
                exports: 'Backbone'
            },
            'bootstrap': {
                deps: ['jquery']
            },
            'jquery.form': {
                deps: ['jquery']
            },
            'apprise': {
                deps: ['jquery'],
                exports:'apprise'
            },
            'jquery.ui.effects': {
                deps: ['jquery']
            }
        }
    });

    require(['backbone'], function () {
        /*
         Underscore setting
         */
        _.templateSettings = {
            interpolate: /\<\@\=(.+?)\@\>/gim,
            evaluate: /\<\@([\s\S]+?)\@\>/gim,
            escape: /\<\@\-(.+?)\@\>/gim
        };
    })

</script>
<link type="text/css" rel="stylesheet" href="/static/lib/bootstrap/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="/static/lib/apprise/apprise.min.css"/>
<link type="text/css" rel="stylesheet" href="/static/css/main.css"/>

