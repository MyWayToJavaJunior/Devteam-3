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

<c:set var="page" value="Controller?executionCommand=SHOW_ORDER_FORM" scope="session" />
<!-- Header -->
<%@ include file="../common/header.jspf"%>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
<fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <a class="btn btn-lg btn-primary" href="Controller?executionCommand=SHOW_SPECIFICATIONS">Назад</a>
        <form class="form-signin" action="Controller" method="get">
            <input type="hidden" name="executionCommand" value="EDIT_ORDER_DETAIL" />
            <h3><fmt:message key="jsp.customer.specifications.add.header" bundle="${msg}"/></h3>
            <div class="form-group">
                <label>ID</label>
                <input type="text" name="id" class="form-control" value="${editSp.id}">
            </div>
            <div class="form-group">
                <label>Status</label>
                <input type="text" name="status" class="form-control" value="${editSp.status}" disabled>
            </div>
            <div class="form-group">
                <label>User ID</label>
                <input type="text" name="uid" class="form-control" value="${editSp.user_id}" disabled>
            </div>
            <div class="form-group">
                <label><fmt:message key="jsp.customer.specifications.table.name" bundle="${msg}"/></label>
                <input type="text" name="order" class="form-control" value="${editSp.name}" required="" autofocus="">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1"><fmt:message key="jsp.customer.specifications.table.jobs.number" bundle="${msg}"/></label>
                <input type="text" name="job"  class="form-control" id="exampleInputPassword1" value="${editSp.jobs}">
            </div>
            <input class="btn" type="submit" value="Update" /><br />
        </form>
    </div>
</div>

<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<%@ include file="../common/footer.jspf"%>
