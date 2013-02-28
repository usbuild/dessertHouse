<%@ page import="com.lecoding.components.GenderType" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: usbuild
  Date: 13-2-28
  Time: 下午2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<s:form modelAttribute="user" cssClass="form form-horizontal ajax-form">
    <fieldset>
        <legend>个人信息</legend>
        <div class="control-group">
            <s:label path="gender" cssClass="control-label">性别</s:label>
            <div class="controls">
                <label style="display: inline-block; margin-right: 20px">
                    <s:radiobutton path="gender" value="<%=GenderType.male%>" cssClass="radio"/>男
                </label>
                <label style="display: inline-block;">
                    <s:radiobutton path="gender" value="<%=GenderType.female%>" cssClass="radio"/>女
                </label>
            </div>
        </div>

        <div class="control-group">
            <s:label path="age" cssClass="control-label">年龄</s:label>
            <div class="controls">
                <s:input path="age"/>
            </div>
        </div>

        <div class="control-group">
            <s:label path="area_id" cssClass="control-label">地区</s:label>
            <div class="controls">
                <s:select path="area_id" items="${areas}"/>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" class="btn btn-primary" value="提交"/>
                <button class="btn btn-danger">暂停帐号</button>
            </div>
        </div>
    </fieldset>
</s:form>