<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-28
  Time: 下午4:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="span4 pay-left">
        <form action="/customer/pay" class="ajax-form">
            <fieldset>
                <legend>充值缴费</legend>
                <c:if test="${success}">
                    <div class="alert alert-success">
                        充值成功
                        <c:if test="${upgrade}">
                            您的账户已可用！
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${fail}">
                    <div class="alert alert-danger">
                        充值失败
                    </div>
                </c:if>
                <div class="control-group">
                    <div class="control-label">
                        <label for="amount">金额</label>
                    </div>
                    <div class="controls">
                        <input type="text" name="amount" id="amount"/>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary" value="充值">
            </fieldset>
        </form>
    </div>
    <div class="span8 pay-right">
        <fieldset>
            <legend>历史记录</legend>
            <div class="pay-history-table">
                <table class="table">
                    <tr>
                        <th>编号</th>
                        <th>时间</th>
                        <th>金额</th>
                    </tr>
                    <c:forEach var="record" items="${records}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td><fmt:formatDate value="${record.createTime}" pattern="yyyy-MM-dd H:m:s"/></td>
                            <td>${record.amount}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </fieldset>
    </div>
</div>