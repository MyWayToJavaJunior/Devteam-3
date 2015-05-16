<%@ page import="java.sql.Time" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 27.04.15
  Time: 3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
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
        <h3 class="form">Step 2</h3>

        <form class="form" role="form" >

            <h4>Job</h4>
            <div class="form-group">
                <label for="name_job">Name</label>
                <input type="text" id="name_job" name="name_job"  class="form-control" placeholder="Enter name of job" required="" autofocus="">
            </div>

            <div class="form-group">
                <label for = "qualification_job">Qualification</label>
                <input type="text" id="qualification_job" name="qualification_job" class="form-control" placeholder="Enter qualification" required="">
            </div>

            <div class="form-group">
                <label for="qualification_job">Time</label>
                <input type="text" id="job_time" name="job_time" class="form-control" id="exampleInputPassword1" placeholder="Time">
            </div>
        </form>
        <a class="btn btn-lg btn-primary" href="Controller?executionCommand=CREATE_JOB">Create job</a>
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
