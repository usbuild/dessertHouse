<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-5
  Time: 下午3:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
    <div class="span6" id="status-container" style="height: 400px; margin: 0 auto"></div>
    <div class="span6" id="area-container" style="height: 400px; margin: 0 auto"></div>
</div>
<div class="row-fluid">
    <div class="span6" id="gender-container" style="height: 400px; margin: 0 auto"></div>
    <div class="span6" id="age-container" style="height: 400px; margin: 0 auto"></div>
</div>
<script type="text/javascript">
    require(['jquery', "highcharts"], function () {

        var chartOptions = {
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage}%</b>',
                percentageDecimals: 2
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function () {
                            return '<b>' + this.point.name + '</b>: ' + this.percentage + ' %';
                        }
                    }
                }
            }
        };

        new Highcharts.Chart({
            chart: {
                renderTo: 'area-container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '会员居住区'
            },
            tooltip: chartOptions['tooltip'],
            plotOptions: chartOptions['plotOptions'],
            series: [
                {

                    type: 'pie',
                    name: '会员居住区',
                    data: [
                        <c:forEach var="m" items="${area}">
                        [ '${m.key}', ${m.value} ],
                        </c:forEach>
                    ]
                }
            ]
        });

        new Highcharts.Chart({
            chart: {
                renderTo: 'status-container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '会员卡状态'
            },
            tooltip: chartOptions['tooltip'],
            plotOptions: chartOptions['plotOptions'],
            series: [
                {

                    type: 'pie',
                    name: '会员卡状况',
                    data: [
                        <c:forEach var="m" items="${status}">
                        [ '${m.key}', ${m.value} ],
                        </c:forEach>
                    ]
                }
            ]
        });
        new Highcharts.Chart({
            chart: {
                renderTo: 'gender-container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '性别分布'
            },
            tooltip: chartOptions['tooltip'],
            plotOptions: chartOptions['plotOptions'],
            series: [
                {

                    type: 'pie',
                    name: '性别分布',
                    data: [
                        <c:forEach var="m" items="${gender}">
                        [ '${m.key}', ${m.value} ],
                        </c:forEach>
                    ]
                }
            ]
        });

        new Highcharts.Chart({
            chart: {
                renderTo: 'age-container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '年龄分布'
            },
            tooltip: chartOptions['tooltip'],
            plotOptions: chartOptions['plotOptions'],
            series: [
                {

                    type: 'pie',
                    name: '年龄分布',
                    data: [
                        <c:forEach var="m" items="${age}">
                        [ '${m.key}', ${m.value} ],
                        </c:forEach>
                    ]
                }
            ]
        });
    });
</script>