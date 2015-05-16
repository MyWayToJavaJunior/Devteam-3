<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 26.04.15
  Time: 22:00
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

        <c:set var="page" value="Controller?executionCommand=SHOW_SPECIFICATIONS" scope="session" />
        <c:set var="item_job" value="${item.jobs}" scope="session"/>


<%-------%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="span9">
                <div class="well">
                    <div class="page-header">
                        <h1><fmt:message key="jsp.customer.specifications.body.header" bundle="${msg}"/></h1>
                    </div>

                    <table class="table table-bordered table-striped">

                        <thead>
                        <tr>
                            <td><b>ID</b></td>
                            <td><b><fmt:message key="jsp.customer.specifications.table.name" bundle="${msg}"/></b></td>
                            <td><b><fmt:message key="jsp.customer.specifications.table.status" bundle="${msg}"/></b></td>
                            <td><b><fmt:message key="jsp.customer.specifications.table.jobs.number" bundle="${msg}"/></b></td>
                            <th><b><fmt:message key="jsp.customer.specifications.table.action" bundle="${msg}"/></b></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${simpleinfo}">
                        <c:if test="${item.getClass().getSimpleName() eq 'Spetification' }">
                        <tr>
                            <td><c:out value="${item.id}"></c:out></td>
                            <td><c:out value="${item.name}"></c:out></td>

                            <td>
                                <c:if test="${item.status eq '0'}" >
                                <c:out value="Не подтвержден"></c:out></td>
                            </c:if>
                            <c:if test="${item.status eq '1'}" >
                                <c:out value="В разработке"></c:out></td>
                            </c:if>
                            <c:if test="${item.status eq '2'}" >
                                <c:out value="Выполнен"></c:out></td>
                            </c:if>
                            <td><c:out value="${item.jobs}"></c:out></td>
                            <td>
                                <a id = "${item.id}" class="btn btn-mini btn-primary" href="Controller?spId=${item.id}&executionCommand=EDIT_ORDER" >
                                    <i class="icon-edit icon-white"></i>
                                    <fmt:message key="jsp.customer.specifications.button.edit" bundle="${msg}"/></a>

                                <a class="btn btn-mini btn-danger" href="Controller?spId=${item.id}&executionCommand=SHOW_CUSTOMER_JOBS" data-toggle="modal" href=""><i
                                        class="icon-remove icon-white"></i>
                                    <fmt:message key="jsp.customer.specifications.button.jobs.view" bundle="${msg}"/></a>
                            </td>

                        </tr>
                        </c:if>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
        </div>

</body>
</html>