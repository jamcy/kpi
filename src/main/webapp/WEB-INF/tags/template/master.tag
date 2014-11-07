<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:directive.tag language="java" pageEncoding="UTF-8" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Virtual Multimedia Laboratory</title>

<link rel="shortcut icon" href="/images/favicon.ico" />

<link href="<spring:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<spring:url value="/css/responsive-main.css" />" rel="stylesheet">
<link href="<spring:url value="/css/smoothness/jquery-ui-1.10.4.custom.min.css" />" rel="stylesheet">

<script src="<spring:url value="/js/jquery-1.9.1.min.js" />"></script>
<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
<script src="<spring:url value="/js/jquery-ui-1.10.4.custom.min.js" />"></script>

</head>
<body>
	<div class="top">
		<div class="container rel">
			<div class="" style="position: absolute; right: 0px; z-index: 5;">
			 	<sec:authentication var="username" property="principal"/>
			 	<c:choose>
			 	<c:when test="${username eq 'anonymous'}">
		 		<a href="<spring:url value="/login"/>" class="btn btn-primary"><spring:message code="header.login-message" /></a>
			 	</c:when>
			 	<c:otherwise>
		 		<div class="dropdown">
					<a href="#" class="dropdown-toggle btn btn-primary" data-toggle="dropdown">
						<c:out value="${username}" /><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<spring:url value="/course" />"><spring:message code="user.menu.courses" /></a></li>
						<li><a href="<spring:url value="/admin" />"><spring:message code="user.menu.admin" /> </a></li>
						<li><a href="<spring:url value="/j_spring_security_logout" />"><spring:message code="user.menu.logout" /></a></li>
					</ul>
				</div>
			 	</c:otherwise>
				</c:choose>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-3">
					<a href="<spring:url value="/"/>"> 
						<img src="<spring:url value="/images/logo.png" />" />
						<div class="vml">
							<div class="title">
								<b class="pt">Virtual</b> <b class="pt">Multimedia</b> <b
									class="pt">Laboratory</b>
							</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-8  col-md-9 ">
					<div class="navi">
						<ul class="nav nav-tabs links">
							<li><a href="<spring:url value="/" />"><spring:message
										code="header.menu.main-page" /> </a></li>
							<li><a href="http://moodle.vml.eec.kpi.ua" target="_top">Moodle</a></li>
							<li><a href="http://eec.kpi.ua">EEC</a></li>
							<!--<li ><a href="/page/mission"><spring:message code="header.menu.mission" /></a></li>-->
							<li><a href="<spring:url value="/page/about" />"><spring:message code="header.menu.about" /> </a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:doBody />

	<div id="footer" class="footer">
		<spring:url value="/" var="localeEn" htmlEscape="true">
			<spring:param name="locale" value="en" />
		</spring:url>
		<spring:url value="/" var="localeUk" htmlEscape="true">
			<spring:param name="locale" value="uk" />
		</spring:url>
		<c:choose>
			<c:when test="${pageContext.response.locale eq 'en'}">
				<c:set var="enActive" value="active" />
				<c:set var="ukActive" value="" />
			</c:when>
			<c:otherwise>
				<c:set var="enActive" value="" />
				<c:set var="ukActive" value="active" />
			</c:otherwise>
		</c:choose>
		
		<div class="btn-group ib">
			<a href="${localeEn}" class="btn btn-default ${enActive}">English</a> 
			<a href="${localeUk}" class="btn btn-default ${ukActive}">Українська</a>
		</div>
	</div>
</body>
</html>
