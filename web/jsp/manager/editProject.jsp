<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 05.05.15
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="page" value="Controller?executionCommand=VIEW_EDIT_PROJECT" scope="session" />
<!-- Header -->
<%@ include file="../common/header.jspf"%>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
<fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <a class="btn btn-lg btn-primary" href="Controller?executionCommand=SHOW_PROJECTS">Назад</a>
        <form class="form-signin" action="Controller" method="get">
            <input type="hidden" name="executionCommand" value="EDIT_PRIJECT" />
            <h3><fmt:message key="jsp.customer.specifications.add.header" bundle="${msg}"/></h3>
            <div class="form-group">
                <label>ID</label>
                <input type="text" name="id" class="form-control" value="${project.id}">
            </div>
            <div class="form-group">
                <label>Status</label>
                <input type="text" name="status" class="form-control" value="${project.status}" disabled>
            </div>
            <div class="form-group">
                <label>Developer ID</label>
                <input type="text" name="did" class="form-control" value="${project.employees}" disabled>
            </div>
            <div class="form-group">
                <label><fmt:message key="jsp.customer.specifications.table.name" bundle="${msg}"/></label>
                <input type="text" name="project" class="form-control" value="${project.name}" required="" autofocus="">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Time</label>
                <input type="text" name="time"  class="form-control" id="exampleInputPassword1" value="${project.time}">
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

