<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 04.05.15
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../common/header.jspf"%>
<%@ include file="menuСustomer.jspf"%>


<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="row">

        <%@ include file="../common/language.jsp"%>
        <fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
        <fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>
        <c:set var="page" value="Controller?executionCommand=SHOW_CUSTOMER_JOBS" scope="session" />

        <%-------%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="span9">
                <div class="well">
                    <div class="page-header">
                        <h1><fmt:message key="jsp.customer.specifications.body.header" bundle="${msg}"/></h1>
                    </div>
                    <a class="btn btn-lg btn-primary" href="Controller?executionCommand=SHOW_SPECIFICATIONS">Назад</a>

                    <table class="table table-bordered table-striped">

                        <thead>
                        <tr>
                            <td><b>ID</b></td>
                            <td><b><fmt:message key="jsp.customer.specifications.table.name" bundle="${msg}"/></b></td>
                            <td><b><fmt:message key="jsp.customer.specifications.table.uid" bundle="${msg}"/></b></td>
                            <td><b><fmt:message key="jsp.customer.specifications.table.status" bundle="${msg}"/></b></td>
                            <td><b><fmt:message key="jsp.customer.specifications.table.jobs.number" bundle="${msg}"/></b></td>
                            <th><b><fmt:message key="jsp.customer.specifications.table.action" bundle="${msg}"/></b></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${jobs}">
                        <c:if test="${item.getClass().getSimpleName() eq 'Job' }">
                        <tr>
                            <td><c:out value="${item.id}"></c:out></td>
                            <td><c:out value="${item.name}"></c:out></td>
                            <td><c:out value="${item.qualification}"></c:out></td>
                            <td><c:out value="${item.cost}"></c:out></td>
                            <td><c:out value="${item.time}"></c:out></td>
                            <td><c:out value="${item.specification}"></c:out></td>
                            <td><c:out value="${item.specialist}"></c:out></td>

                        </tr>
                        </c:if>
                        </c:forEach>

                    </table>
                        <a class="btn btn-lg btn-primary" href="Controller?executionCommand=GET_JOB">Add Job</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
