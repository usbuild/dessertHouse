<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-28
  Time: 下午8:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:form modelAttribute="passForm" cssClass="ajax-form" action="/customer/password">
    <fieldset>
        <legend>修改密码</legend>
        <s:errors path="*" cssClass="alert alert-error error-msg"/>
        <c:if test="${success}">
            <div class="alert alert-success error-msg">
                数据更新成功
            </div>
        </c:if>
        <s:label path="oldPassword"> 原始密码 </s:label>
        <s:password path="oldPassword"/>
        <s:label path="password">新密码</s:label>
        <s:password path="password"/>
        <s:label path="rePassword">确认密码</s:label>
        <s:password path="rePassword"/>
        <input type="submit" class="btn btn-primary">
    </fieldset>
</s:form>
