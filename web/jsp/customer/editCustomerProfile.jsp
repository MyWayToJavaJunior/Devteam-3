<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 16.05.15
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../common/header.jspf"%>
<%@ include file="../common/language.jsp"%>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en' : localeValue}"/>
<fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>
<body>
<a class="btn btn-lg btn-primary" href="Controller?executionCommand=SHOW_SPECIFICATIONS">Назад</a>
<div class="container" style="padding-top: 60px;">
    <h1 class="page-header">Edit Profile</h1>
    <div class="row">
        <!-- left column -->
        <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="text-center">
                <img src="img/9.jpeg" class="avatar img-circle img-thumbnail" alt="avatar">
                <h6>Sorry, now you can't upload avatar...</h6>
            </div>
        </div>
        <!-- edit form column -->
        <div class="col-md-8 col-sm-6 col-xs-12 personal-info">

            <h3>Personal info</h3>
            <form class="form-horizontal" role="form" action="Controller" method="get">
                <input type="hidden" name="executionCommand" value="EDIT_PROFILE" />
                <div class="form-group">
                    <label class="col-lg-3 control-label">First name:</label>
                    <div class="col-lg-8">
                        <input id="firstName" name="firstName" class="form-control" value="${user.firstName}" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Last name:</label>
                    <div class="col-lg-8">
                        <input id="lastName" name="lastName" class="form-control" value="${user.lastName}" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Company:</label>
                    <div class="col-lg-8">
                        <input id="company" name="company" class="form-control" value="" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Email:</label>
                    <div class="col-lg-8">
                        <input id="email" name="email" class="form-control" value="${user.email}" type="text">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Password:</label>
                    <div class="col-md-8">
                        <input id="pass1" name="pass1" class="form-control" value="${user.password}" type="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Confirm password:</label>
                    <div class="col-md-8">
                        <input id="pass2" name="pass2" class="form-control" value="${user.password}" type="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input class="btn btn-primary" type="submit" value="Save Changes" type="button">
                        <span></span>
                        <input class="btn btn-default" value="Cancel" type="reset">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<%@ include file="../common/footer.jspf"%>