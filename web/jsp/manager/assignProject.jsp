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


<!-- Header -->
<%@ include file="../common/header.jspf"%>


<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <a class="btn btn-lg btn-primary" href="Controller?executionCommand=SHOW_PROJECTS">Назад</a>
        <form class="form-signin" action="Controller" method="get">
            <input type="hidden" name="executionCommand" value="ASSIGN_PROJECT" />
            <h3>Assign Project</h3>

            <div class="control-group">
                <label class="control-label" for="project_name">Name of project:</label>

                <div class="controls">
                    <select id="project_name" name="project_name" class="form-control">
                            <c:forEach var="projects" items="${projects}" varStatus="status">
                        <option><c:out value="${projects}"/></option>
                             </c:forEach>
                    </select>
                </div>
            </div>
            <p></p>

            <div class="form-group">
                <label for="disabledInput">Manager id</label>
                <input type="text" id="disabledInput" class="form-control"  placeholder="${sessionScope.user.id}" disabled>
            </div>

            <div class="control-group">
                <label class="control-label" for="dev_name">Developer name:</label>

                <div class="controls">
                    <select id="dev_name" name="dev_name" class="form-control">
                        <c:forEach var="dev_names" items="${dev_names}" varStatus="dev_name">
                            <option><c:out value="${dev_names}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <p></p>
            <input class="btn" type="submit" value="Assign project" /><br />
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
