<%@ page import="java.sql.Time" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 27.04.15
  Time: 3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var="page" value="Controller?executionCommand=SHOW_ORDER_FORM" scope="session" />
<!-- Header -->
<%@ include file="../common/header.jspf"%>
<%@ include file="../common/language.jsp"%>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
<fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>

<jsp:useBean id="job" type="java.lang.Integer" scope="session" />

<%-----
<jsp:useBean id="name_job" type="java.util.ArrayList" scope="session" />
<jsp:useBean id="qualification_job" type="java.util.ArrayList" scope="session" />
<jsp:useBean id="job_time" type="java.util.ArrayList" scope="session" />
----------%>


<div class="container">

    <form class="form-signin" action="Controller" method="get">
        <input type="hidden" name="executionCommand" value="CREATE_ORDER_PART_TWO" />
        <h3 class="form">Step 2</h3>
    <c:if test="${sessionScope.job != null}">
        <c:forEach var="i" begin="1" end="${sessionScope.job}">
        <form class="form" role="form" id="${i}">


            <h4>Job</h4>
            <div class="form-group">
                <label >Name</label>
                <input type="text" name="name_job" class="form-control" placeholder="Enter name of job" required="" autofocus="">
            </div>

            <div class="form-group">
                <label>Qualification</label>
                <input type="text" name="qualification_job" class="form-control" placeholder="Enter qualification" required="" autofocus="">
            </div>

            <div class="form-group">
                <label for="exampleInputPassword1">Time</label>
                <input type="text" name="job_time" class="form-control" id="exampleInputPassword1" placeholder="Time">
            </div>


        </form>
        </c:forEach>
    </c:if>
        <input class="btn btn-danger" type="submit" value="Create order" /><br />
</div>

<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<!-- end form-->
</div>
</div>
</div>

<%@ include file="../common/footer.jspf"%>
