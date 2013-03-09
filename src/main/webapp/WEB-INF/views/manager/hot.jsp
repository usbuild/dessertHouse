<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-7
  Time: 下午4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row-fluid">
    <div class="span12" id="hot-container" style="height: 400px; margin: 0 auto"></div>
</div>


<script type="text/javascript">
    require(['jquery', "highcharts"], function () {
        new Highcharts.Chart({
            chart: {
                renderTo: 'hot-container',
                type: 'column'
            },
            title: {
                text: '销量Top10'
            },
            xAxis: {
                categories: [
                    <c:forEach var="i" items="${hots}" varStatus="status">
                    '${i.key}',
                    </c:forEach>
                ]
            },
            yAxis: {
                min: 0,
                title: {
                    text: '销量'
                }
            },
            legend: {
                layout: 'vertical',
                backgroundColor: '#FFFFFF',
                align: 'left',
                verticalAlign: 'top',
                x: 100,
                y: 70,
                floating: true,
                shadow: true
            },
            tooltip: {
                formatter: function () {
                    return '' +
                            this.x + ': ' + this.y + ' ';
                }
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [
                {
                    name: '销量',
                    data: [
                        <c:forEach var="i" items="${hots}" varStatus="status">
                        ${i.value},
                        </c:forEach>
                    ]

                }
            ]
        });
    });
</script>
