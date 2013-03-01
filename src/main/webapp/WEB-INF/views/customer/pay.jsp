<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-28
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="" class="ajax-form">
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