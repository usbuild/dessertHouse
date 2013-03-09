<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-5
  Time: 下午3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
    <div class="span6" id="pref-container" style="height: 400px; margin: 0 auto"></div>
    <div class="span6" id="trend-container" style="height: 400px; margin: 0 auto"></div>
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
                renderTo: 'pref-container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '会员喜好'
            },
            tooltip: chartOptions['tooltip'],
            plotOptions: chartOptions['plotOptions'],
            series: [
                {

                    type: 'pie',
                    name: '喜好种类',
                    data: [
                        <c:forEach var="m" items="${types}">
                        [ '${m.key}', ${m.value} ],
                        </c:forEach>
                    ]
                }
            ]
        });



        /*
         */
        function regression(x, y, typ) {
            var type = (typ == null) ? 'linear' : typ;
            var N = x.length;
            var slope;
            var intercept;
            var SX = 0;
            var SY = 0;
            var SXX = 0;
            var SXY = 0;
            var SYY = 0;
            var Y = [];
            var X = [];

            if (type == 'linear') {
                X = x;
                Y = y;
            }
            else if (type == 'exp' || type == 'exponential') {
                for (var i = 0; i < y.length; i++) {
                    // ignore points <= 0, log undefined.
                    if (y[i] <= 0) {
                        N--;
                    }
                    else {
                        X.push(x[i]);
                        Y.push(Math.log(y[i]));
                    }
                }
            }

            for (var i = 0; i < N; i++) {
                SX = SX + X[i];
                SY = SY + Y[i];
                SXY = SXY + X[i] * Y[i];
                SXX = SXX + X[i] * X[i];
                SYY = SYY + Y[i] * Y[i];
            }

            slope = (N * SXY - SX * SY) / (N * SXX - SX * SX);
            intercept = (SY - slope * SX) / N;

            return [slope, intercept];
        }

        function linearRegression(X, Y) {
            var ret;
            ret = regression(X, Y, 'linear');
            return [ret[0], ret[1]];
        }

        function fitData(data) {
            var ret;
            var res;
            var x = [];
            var y = [];
            var ypred = [];

            for (i = 0; i < data.length; i++) {
                x.push(i);
                y.push(data[i]);
            }

            ret = linearRegression(x, y);
            for (var i = 0; i < x.length; i++) {
                res = ret[0] * x[i] + ret[1];
                ypred.push([x[i], res]);
            }
            return ypred;
        }


        var sourceData = [
            <c:forEach var="sale" items="${sales}" varStatus="status">
            ${sale.value},
            </c:forEach>
        ];

        new Highcharts.Chart({
            chart: {
                renderTo: 'trend-container',
//                type: 'line',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: '趋势预测',
                x: -20 //center
            },
            xAxis: {
                categories: [
                    <c:forEach var="sale" items="${sales}">
                    '${sale.key}',
                    </c:forEach>
                ]
            },
            yAxis: {
                title: {
                    text: '销售额(元)'
                },
                plotLines: [
                    {
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }
                ]
            },
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' +
                            this.x + ': ' + this.y + '元';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [
                {
                    name: '销售',
                    data: sourceData
                },
                {
                    name: "趋势线",
                    type: 'line',
                    marker: {enabled: false},
                    data: (function () {
                        return fitData(sourceData);
                    })()
                }
            ]
        });


    });
</script>