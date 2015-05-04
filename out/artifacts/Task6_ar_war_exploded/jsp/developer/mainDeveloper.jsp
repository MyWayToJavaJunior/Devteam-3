<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 29.04.15
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!-- Header -->
<%@ include file="../common/header.jspf"%>

<div align="center">
<div class="container">
    <form class="form-signin" action="Controller" method="post">
        <input type="hidden" name="executionCommand" value="SHOW_NEW_PROJECTS" />
<p> <input type="submit" class="btn btn-lg btn-default" value="<fmt:message key="jsp.employee.current.body.header" bundle="${msg}"/>"></p>
<p></p>
        </form>
    <form class="form-signin" action="Controller" method="post">
        <input type="hidden" name="executionCommand" value="SHOW_CURRENT_PROJECTS" />
        <p> <input type="submit" class="btn btn-lg btn-default" value="<fmt:message key="jsp.employee.current.body.header.current" bundle="${msg}"/>"></p>
        <p></p>
    </form>
    <form class="form-signin" action="Controller" method="post">
        <input type="hidden" name="executionCommand" value="SHOW_READY_PROJECTS" />
        <p> <input type="submit" class="btn btn-lg btn-default" value="<fmt:message key="jsp.employee.current.body.header.complete" bundle="${msg}"></p>
        <p></p>
    </form>
</div>
</div>
<!-- Footer -->
<%@ include file="../common/footer.jspf"%>
