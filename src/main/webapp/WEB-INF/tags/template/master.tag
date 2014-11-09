<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<jsp:directive.tag language="java" pageEncoding="UTF-8" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Virtual Multimedia Laboratory</title>

<link rel="shortcut icon" href="<s:url value="/images/favicon.ico" />" />

<link href="<s:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<s:url value="/css/responsive-main.css" />" rel="stylesheet">
<link href="<s:url value="/css/smoothness/jquery-ui-1.10.4.custom.min.css" />" rel="stylesheet">

<script src="<s:url value="/js/jquery-1.9.1.min.js" />"></script>
<script src="<s:url value="/js/bootstrap.min.js" />"></script>
<script src="<s:url value="/js/jquery-ui-1.10.4.custom.min.js" />"></script>

</head>
<body>
	<div class="top">
		<div class="container rel">
			<div class="" style="position: absolute; right: 0px; z-index: 5;">
			 	<security:authentication var="user" property="principal"/>
			 	<c:choose>
			 	<c:when test="${user eq 'anonymous'}">
		 		<a href="<s:url value="/login"/>" class="btn btn-primary"><s:message code="header.login-message" /></a>
			 	</c:when>
			 	<c:otherwise>
		 		<div class="dropdown">
					<a href="#" class="dropdown-toggle btn btn-primary" data-toggle="dropdown">
						<c:out value="${user.fullName}" /><b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<s:url value="/course" />"><s:message code="user.menu.courses" /></a></li>
						<li><a href="<s:url value="/admin" />"><s:message code="user.menu.admin" /> </a></li>
						<li><a href="<s:url value="/j_spring_security_logout" />"><s:message code="user.menu.logout" /></a></li>
					</ul>
				</div>
			 	</c:otherwise>
				</c:choose>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-3">
					<a href="<s:url value="/"/>"> 
						<img src="<s:url value="/images/logo.png" />" />
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
							<li><a href="<s:url value="/" />"><s:message
										code="header.menu.main-page" /> </a></li>
							<li><a href="http://moodle.vml.eec.kpi.ua" target="_top">Moodle</a></li>
							<li><a href="http://eec.kpi.ua">EEC</a></li>
							<!--<li ><a href="/page/mission"><s:message code="header.menu.mission" /></a></li>-->
							<li><a href="<s:url value="/page/about" />"><s:message code="header.menu.about" /> </a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:doBody />

	<div id="footer" class="footer">
		<s:url value="/" var="localeEn" htmlEscape="true">
			<s:param name="locale" value="en" />
		</s:url>
		<s:url value="/" var="localeUk" htmlEscape="true">
			<s:param name="locale" value="uk" />
		</s:url>
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
