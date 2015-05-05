<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 28.04.15
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!-- Header -->
<%@ include file="../common/header.jspf"%>

<div class="container">
    <div align="center">
        <p> <%@ include file="../common/language.jsp"%> </p>
        <fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
        <fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>
        <c:set var="page" value="Controller?executionCommand=SHOW_CURRENT_PROJECTS" scope="session" />
    </div>
</div>

<a class="btn btn-primary btn-large" href="Controller?command=DEV">  <fmt:message key="jsp.employee.button.home" bundle="${msg}"/> </a>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="row">

        <h2 class="sub-header">
            <fmt:message key="jsp.employee.current.body.header.current" bundle="${msg}"/>
        </h2>
        <table class="table table-bordered table-striped">

            <thead>
            <tr>
                <td><b><fmt:message key="jsp.employee.complete.table.name" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.employee.complete.table.specifications" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.employee.complete.table.time" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.employee.complete.table.developer" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.employee.complete.table.manager" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.employee.complete.table.status" bundle="${msg}"/></b></td>
                <th><b><fmt:message key="jsp.employee.complete.table.action" bundle="${msg}"/></b></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="item" items="${simpleinfo}">
            <c:if test="${item.getClass().getSimpleName() eq 'Project' }">
            <tr>
                <td><c:out value="${item.name}"></c:out></td>
                <td><c:out value="${item.spetification_id}"></c:out></td>
                <td><c:out value="${item.time}"></c:out></td>
                <td><c:out value="${item.employees}"></c:out></td>
                <td><c:out value="${item.manager}"></c:out></td>
                <td><c:out value="${item.status}"></c:out></td>
                <td>
                    <form class="form-signin" action="Controller" method="get">
                        <input type="hidden" name="executionCommand" value="TRANSFER_CURRENT_PROJECT" />
                        <button class="btn btn-lg btn-success" type="submit" name="id_project" value="${item.id}">Confirm</button>
                    </form>
                </td>
            </tr>
            </c:if>
            </c:forEach>

    </div>
</div>


