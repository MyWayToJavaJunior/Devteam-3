<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 08.05.15
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="page" value="Controller?executionCommand=SHOW_BILLS" scope="session" />
<!-- Header -->
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/language.jsp"%>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
<fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="row">

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row">
                <a class="btn btn-lg btn-primary" href="Controller?executionCommand=SHOW_PROJECTS">Назад</a>
                <h2 class="sub-header">

                    <fmt:message key="jsp.customer.bills.page.title" bundle="${msg}"/>
                </h2>
                <table class="table table-bordered table-striped">

                    <thead>
                    <tr>
                        <td><b><fmt:message key="jsp.customer.bills.table.id" bundle="${msg}"/></b></td>
                        <td><b><fmt:message key="jsp.customer.bills.table.cost" bundle="${msg}"/></b></td>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="item" items="${billsList}">

                        <tr>
                            <td><c:out value="${item.id}"></c:out></td>
                            <td><c:out value="${item.cost}"></c:out></td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



</body>
</html>
