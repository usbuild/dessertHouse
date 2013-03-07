<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-3-5
  Time: 下午3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
所有店面：
<select name="shop" id="shop-select">
    <c:forEach var="shop" items="${shops}">
        <option value="/user/manager/shop/${shop.id}"
                <c:if test="${selected eq shop.id}">selected="selected"</c:if>>${shop.name}</option>
    </c:forEach>
</select>

<div class="row-fluid">
    <div class="span12" id="sale-container" style="height: 400px; margin: 0 auto"></div>
</div>
<script type="text/javascript">
    require(['jquery', "highcharts"], function () {
        new Highcharts.Chart({
            chart: {
                renderTo: 'sale-container',
                type: 'line',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: '',
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
                            this.x + ': ' + this.y + '°C';
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
                    data: [
                        <c:forEach var="sale" items="${sales}">
                        ${sale.value},
                        </c:forEach>
                    ]
                },
                {
                    name: '预定',
                    data: [
                        <c:forEach var="reserves" items="${reserves}">
                        ${sale.value},
                        </c:forEach>
                    ]
                }
            ]
        });
    });
</script>