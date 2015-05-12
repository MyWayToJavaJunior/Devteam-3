<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="css/singin.css" rel="stylesheet">
<%@ include file="jsp/common/header.jspf"%>

<div class="container">
    <form class="form-signin" action="Controller" method="post">
		<input type="hidden" name="command" value="register" />
        Имя:
        <br /> <input class="form-control" type="text" name="firstname" value="" /><br />
        Фамилия:
        <br /> <input class="form-control" type="text" name="secondname" value="" /><br />
		Логин:
		<br /> <input class="form-control" type="text" name="email" value="" /><br />
		Пароль:
		<br /> <input class="form-control" type="password" name="password" value="" /><br />

        <div class="control-group">
            <div class="controls">

                <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегестрироваться</button>
            </div>
        </div>
	</form>
</div>

<%@ include file="jsp/common/footer.jspf"%>
