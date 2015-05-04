<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 29.04.15
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/language.jsp"%>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
<c:set var="page" value="Controller?executionCommand=SHOW_ORDER_FORM" scope="session" />

<form class="form" role="form">

    <h4>Spetification</h4>

    <div class="form-group">
        <label >Name</label>
        <input type="text" name="order"  class="form-control" placeholder="Enter name of job" required="" autofocus="">
    </div>

    <div class="form-group">
        <label>Jobs</label>
        <input type="text" name="job" class="form-control" placeholder="Enter qualification" required="" autofocus="">
    </div>

</form>
<%@ include file="../common/footer.jspf"%>
