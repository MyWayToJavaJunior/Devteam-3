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
    <div class="row">

        <h2 class="sub-header">
            Header
        </h2>
        <table class="table table-bordered table-striped">

            <thead>
            <tr>
                <td><b>name</b></td>
                <td><b>spetification_id</b></td>
                <td><b>time</b></td>
                <td><b>employees</b></td>
                <td><b>manager</b></td>
                <td><b>status</b></td>
                <th>Action</th>
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
                            <a class="btn btn-mini btn-primary" href="updateTodo.html"><i class="icon-edit icon-white"></i> Edit</a>
                            <a class="btn btn-mini btn-danger" data-toggle="modal" href="#confirm_delete_2"><i
                                    class="icon-remove icon-white"></i> Delete</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>


            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div align="center">Total = <span class="badge badge-inverse">10</span></div>
                </td>
                <td colspan="2">
                    <div align="center">Todo = <span class="badge">5</span></div>
                </td>
                <td colspan="2">
                    <div align="center">Done = <span class="badge badge-success">5</span></div>
                </td>
            </tr>
            </tfoot>
        </table>



    </div>
</div>

</body>
</html>
