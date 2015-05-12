<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 01.05.15
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!-- Header -->
<%@ include file="../common/header.jspf"%>


<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <a class="btn btn-lg btn-primary" href="Controller?executionCommand=SHOW_PROJECTS">Назад</a>

        <form class="form-signin" action="Controller" method="get">
            <input type="hidden" name="executionCommand" value="SHOW_PROJECT_FORM_DETAILS" />
            <h3>Create Project</h3>

            <div class="form-group">
                <label for="disabledInput" >Project name</label>
                <input type="text" id="disabledInput1" class="form-control" name="project_name" >
            </div>

            <div class="control-group">
                <label class="control-label" for="spetification_name">Spetification name:</label>

                <div class="controls">
                    <select id="spetification_name" name="spetification_name" class="form-control">
                        <c:forEach var="spetifications" items="${spetifications}" varStatus="status">
                            <option><c:out value="${spetifications}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <p></p>

            <div class="control-group">
                <label class="control-label" for="developer_id">Developer id:</label>

                <div class="controls">
                    <select id="developer_id" name="developer_id" class="form-control">
                        <c:forEach var="developers" items="${developers}" varStatus="status">
                            <option><c:out value="${developers}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <p></p>

            <div class="form-group">
                <label for="disabledInput">Manager id</label>
                <input type="text" id="disabledInput" class="form-control"  placeholder="${sessionScope.user.id}" disabled>
            </div>

            <p></p>
            <input class="btn" type="submit" value="Далее" /><br />
        </form>
    </div>
</div>

<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<p></p>
<%@ include file="../common/footer.jspf"%>

