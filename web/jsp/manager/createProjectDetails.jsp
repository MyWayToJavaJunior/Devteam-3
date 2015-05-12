<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 01.05.15
  Time: 13:08
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

            <h3>Create Project Details</h3>

            <div class="form-group">
                <label for="disabledInput">Spetification name</label>
                <input type="text" id="disabledInput2" class="form-control"  value="${sessionScope.spetification.name}" disabled>
            </div>

            <div class="form-group">
                <label for="disabledInput">User Id</label>
                <input type="text" id="disabledInput" class="form-control"  value="${sessionScope.spetification.user_id}" disabled>
            </div>

            <div class="form-group">
                <label for="disabledInput">Jobs count</label>
                <input type="text" id="disabledInput1" class="form-control"  value="${sessionScope.spetification.jobs}" disabled>
            </div>

            <div class="form-group">
                <label for="disabledInput">Status id</label>
                <input type="text" id="disabledInput3" class="form-control"  value="${sessionScope.spetification.status}" disabled>
            </div>

            <form class="form-signin" action="Controller" method="get">
                <input type="hidden" name="command" value="ASSIGN_PROJECT" />
                <h3>JOBS</h3>

                <c:forEach var="item" items="${sessionScope.jobList}">
                <div class="form-group">
                    <label for="disabledInput">Job name</label>
                    <input type="text" id="disabledInput5" class="form-control"  value="${item.name}" disabled>
                </div>

                <div class="form-group">
                    <label for="disabledInput">Qualification</label>
                    <input type="text" id="disabledInput6" class="form-control"  value="${item.qualification}" disabled>
                </div>

                <div class="form-group">
                    <label for="disabledInput">Cost</label>
                    <input type="text" id="disabledInput17" class="form-control"  value="${item.cost}" disabled>
                </div>

                <div class="form-group">
                    <label for="disabledInput">Time</label>
                    <input type="text" id="disabledInput38" class="form-control"  value="${item.time}" disabled>
                </div>

                    <div class="form-group">
                        <label for="disabledInput">Specialist count</label>
                        <input type="text" id="disabledInput28" class="form-control"  value="${item.specialist}" disabled>
                    </div>

                    </c:forEach>
                </form>

            <p></p>
            <a class="btn btn-lg btn-primary" href="Controller?executionCommand=CREATE_PROJECT">Create Order</a>
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

