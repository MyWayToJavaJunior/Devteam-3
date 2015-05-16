<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 28.04.15
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="menuManager.jsp"%>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <%@ include file="../common/language.jsp"%>
    <fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
    <fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>
    <c:set var="page" value="Controller?executionCommand=SHOW_PROJECTS" scope="session" />
    <div class="row">
        <h2 class="sub-header">
            <fmt:message key="jsp.manager.projects.body.header" bundle="${msg}"/>
        </h2>
        <table class="table table-bordered table-striped">

            <thead>
            <tr>
                <td><b><fmt:message key="jsp.customer.specifications.table.name" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.manager.waiting.orders.table.spec.name" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.manager.projects.table.time" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.manager.projects.table.did" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.manager.projects.table.mid" bundle="${msg}"/></b></td>
                <td><b><fmt:message key="jsp.customer.specifications.table.status" bundle="${msg}"/></b></td>
                <th><fmt:message key="jsp.customer.specifications.table.action" bundle="${msg}"/></th>
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
                            <a class="btn btn-mini btn-primary" href="Controller?projectId=${item.id}&executionCommand=VIEW_EDIT_PROJECT" ><i class="icon-edit icon-white"></i><fmt:message key="jsp.manager.projects.button.edit" bundle="${msg}"/></a>
                            <a class="btn btn-mini btn-danger" data-toggle="modal" href="Controller?projectId=${item.id}&executionCommand=DELETE_PROJECT" ><i
                                    class="icon-remove icon-white"></i> <fmt:message key="jsp.manager.projects.button.delete" bundle="${msg}"/></a>
                    </tr>
                </c:if>
            </c:forEach>


            </tbody>

        </table>



    </div>
</div>

</body>
</html>
