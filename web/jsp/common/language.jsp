<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 02.05.15
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%----<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Language</title>
</head>
<body>
<form id="lang" method="post" action="Controller">
    <input type="hidden" name="executionCommand" value="CHANGE_LANGUAGE">
    <div class="col-md-2">
    <div class="controls">
    <select name="choice" class="form-control"  onchange="this.form.submit()" >
        <option>
            <c:out value="${sessionScope.user.language}"/>
        </option>
        <c:if test="${sessionScope.user.language eq 'en'}">
            <option>ru</option>
        </c:if>
        <c:if test="${sessionScope.user.language eq 'ru'}">
            <option>en</option>
        </c:if>
    </select>
        </div>
        </div>
</form>
</body>
</html> ------%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>

<html>
<body>
<form action="Controller" method="get">
    <label>Language</label>
    <input type="hidden" name="executionCommand" value="CHANGE_LANGUAGE"/>
    <input type="hidden" name="hiddenPageID" value="${pageID}" />
    <div class="col-md-2">
        <div class="controls">
    <select id="language" class="form-control"  name="language" onchange="this.form.submit()">
        <option value="en" ${localeValue == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${localeValue == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
            </div>
    </div>
</form>
</body>
</html>

