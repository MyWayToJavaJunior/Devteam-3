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
        <form class="form-signin" action="Controller" method="get">
            <input type="hidden" name="executionCommand" value="SHOW_PROJECT_FORM_DETAILS" />
            <h3>Create Project</h3>

            <div class="control-group">
                <label class="control-label" for="sp_name">Name of project:</label>

                <div class="controls">
                    <select id="sp_name" name="sp_name" class="form-control">
                        <c:forEach var="spetifications" items="${spetifications}" varStatus="status">
                            <option><c:out value="${spetifications}"/></option>
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

