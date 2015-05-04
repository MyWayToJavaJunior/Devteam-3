<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="css/singin.css" rel="stylesheet">
<%@ include file="jsp/common/header.jspf"%>
<fmt:setLocale value="ru"/>

<div class="container">
<form class="form-signin" action="Controller" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="hidden" name="executionCommand" value="LOGIN" />
    <c:out value="${login}" />
    <br /> <input class="form-control" type="text" name="email" value="" placeholder="Email address"/><br />
    <c:out value="${password}" />
    <br /> <input class="form-control" type="password" name="password" value="" placeholder="Password" /><br />

    <div class="control-group">
        <div class="controls">

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </div>
    </div>
</form>
    </div>



<%@ include file="jsp/common/footer.jspf"%>

