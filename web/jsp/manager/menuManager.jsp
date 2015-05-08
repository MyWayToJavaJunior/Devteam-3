<%--
  Created by IntelliJ IDEA.
  User: olga
  Date: 28.04.15
  Time: 0:38
  To change this template use File | Settings | File Templates.
--%>
<fmt:setLocale value="${localeValue}"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href=""><fmt:message key="jsp.customer.menu.specifications.add" bundle="${msg}"/></a></li>
                <li><a href="Controller?executionCommand=SHOW_PROJECTS"><fmt:message key="jsp.manager.projects.page.title" bundle="${msg}"/></a></li>
                <li><a href="Controller?executionCommand=VIEW_WAITING_ORDER"><fmt:message key="jsp.manager.waiting.orders.body.header" bundle="${msg}"/></a></li>
                <li><a href="#"><fmt:message key="jsp.manager.bills.body.header" bundle="${msg}"/></a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controller?executionCommand=SHOW_ASSIGN_PROJECT_FORM"><fmt:message key="jsp.manager.assign.orders.body.header" bundle="${msg}"/></a></li>
                <li><a href="Controller?executionCommand=SHOW_PROJECT_FORM"><fmt:message key="jsp.manager.add.orders.body.header" bundle="${msg}"/></a></li>

            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="Controller?executionCommand=VIEW_USER"><fmt:message key="jsp.manager.user.delete.body.header" bundle="${msg}"/></a></li>
                <li><a href="Controller?executionCommand=VIEW_USER"><fmt:message key="jsp.manager.user.edit.profile.body.header" bundle="${msg}"/></a></li>
            </ul>
        </div>
    </div>
</div>