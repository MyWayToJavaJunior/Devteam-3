<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ include file="jsp/common/header.jspf"%>

        <c:set var="pageID" value="register.jsp" scope="request" />
        <jsp:include page="locale.jsp"/>

	<form class="form-signin" action="Controller" method="post">
		<input type="hidden" name="command" value="register" />
        <c:out value="${firstname}" />
        <br /> <input class="form-control" type="text" name="firstname" value="" /><br />
        <c:out value="${secondname}" />
        <br /> <input class="form-control" type="text" name="secondname" value="" /><br />
		<c:out value="${login}" />
		<br /> <input class="form-control" type="text" name="email" value="" /><br />
		<c:out value="${password}" />
		<br /> <input class="form-control" type="password" name="password" value="" /><br /> <input
			type="submit" value="${register}" /><br />
	</form>

<%@ include file="jsp/common/footer.jspf"%>
