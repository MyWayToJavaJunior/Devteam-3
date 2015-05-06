

<!--[if lt IE 7]><html class="lt-ie9 lt-ie8 lt-ie7" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><![endif]-->
<!--[if IE 7]><html class="lt-ie9 lt-ie8" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><![endif]-->
<!--[if IE 8]><html class="lt-ie9" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><![endif]-->
<!--[if gt IE 8]><!--><html xmlns="http://www.w3.org/1999/xhtml"><!--<![endif]-->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<c:set var="page" value="index.jsp" scope="session" />
<%@ include file="jsp/common/header.jspf"%>
<fmt:setBundle basename="com.epam.task6.resource.Resource" var="msg"/>


<link rel="stylesheet" href="css/normalize.css" />
<link rel="stylesheet" href="css/foundation.min.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/ie.css" />


<script src="js/vendor/custom.modernizr.js"></script>

<div class="jumbotron">

        <div class="center-block">
        <div class="row well">
            <div class="col-md-6">

                <h2><fmt:message key="jsp.common.text.header" bundle="${msg}"/></h2>
            <p>
            <h4><fmt:message key="jsp.common.text" bundle="${msg}"/></h4>
            <ul>
                <li><fmt:message key="jsp.common.text.partone" bundle="${msg}"/></li>
                <li><fmt:message key="jsp.common.text.parttwo" bundle="${msg}"/></li>
                <li><fmt:message key="jsp.common.text.partthree" bundle="${msg}"/></li>
            </ul>
            <h4><fmt:message key="jsp.common.text.partfour" bundle="${msg}"/></h4>
            </p>
                <p>
                <c:if test="${sessionScope.user == null}">
                    <a href="login.jsp"> <button type="btn" class="btn btn-lg btn-primary"><fmt:message key="jsp.common.header.login" bundle="${msg}"/></button></a>
                </c:if>
                    or
                <c:if test="${sessionScope.user == null}">
                    <a href="register.jsp"><button type="btn" class="btn btn-lg btn-primary"><fmt:message key="jsp.common.header.reg" bundle="${msg}"/></button></a>
                </c:if>
               </p>
                </div>
            <div class="col-md-6">
                <div align="center">
                <img src="img/order.png">
                </div>
                </div>
            <div class="col-md-6">

                </div>
       </div>
    </div>
</div>




<div id="features" class="section features" data-magellan-destination="features">
    <div class="row hi-icon-wrap hi-icon-effect-3 hi-icon-effect-3b">
        <div class="large-4 columns feature">
            <span class="icon icon-browser hi-icon"></span>
            <h3><fmt:message key="jsp.common.landing.one.header" bundle="${msg}"/></h3>
            <p><fmt:message key="jsp.common.landing.one.text" bundle="${msg}"/>

            </p>
        </div>
        <div class="large-4 columns feature">
            <span class="icon icon-tools hi-icon"></span>
            <h3><fmt:message key="jsp.common.landing.two.header" bundle="${msg}"/></h3>
            <p><fmt:message key="jsp.common.landing.two.text" bundle="${msg}"/>
            </p>
        </div>
        <div class="large-4 columns feature">
            <span class="icon icon-trophy hi-icon"></span>
            <h3><fmt:message key="jsp.common.landing.three.header" bundle="${msg}"/></h3>
            <p><fmt:message key="jsp.common.landing.three.text" bundle="${msg}"/>
            </p>
        </div>
    </div>
</div>



<%@ include file="jsp/common/footer.jspf"%>
