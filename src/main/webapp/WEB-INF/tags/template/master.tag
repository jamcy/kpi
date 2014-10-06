<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@tag language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Virtual Multimedia Laboratory</title>

<link rel="shortcut icon" href="/img/favicon.ico" />

<link href="<spring:url value="/static/css/jquery.ui.css" />" rel="stylesheet" />
<link href="<spring:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<spring:url value="/static/css/responsive-main.css" />" rel="stylesheet">
<link href="<spring:url value="/static/css/smoothness/jquery-ui-1.10.4.custom.min.css" />" rel="stylesheet">

<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
<script src="/js/jquery-ui-1.10.4.custom.min.js"></script>

<script src="/js/three.min.js"></script>
<script src="/js/libs/stats.min.js"></script>
</head>
<body>
	<div class="top">
		<div class="container rel">
			<div class="" style="position: absolute; right: 0px; z-index: 5;">
				<%-- <%
                if (user == null) {
            %> --%>
				<a href="/login" class="btn btn-primary"><spring:message
						code="header.login-message" /> </a>
				<%-- <%
            } else {
            %> --%>
				<div class="dropdown">
					<%--  <a href="#" class="dropdown-toggle btn btn-primary"
                   data-toggle="dropdown"><%=user.getFirstName() + " " + user.getLastName()%> --%>
					<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="/course"><spring:message
									code="user.menu.courses" /> </a></li>
						<%-- <%
                        boolean admin = false;
                        if (!user.getRole().equals(User.SR_USER)) {
                            admin = true;
                        }
                        for (CourseRole cr : user.getCourseRoles()) {
                            if (cr.getRole().equals(CourseRole.CR_TEACHER)
                                    || cr.getRole().equals(CourseRole.CR_MODERATOR)) {
                                admin = true;
                            }
                        }

                        if (admin == true) {
                    %> --%>
						<li><a href="/admin"><spring:message
									code="user.menu.admin" /> </a></li>
						<%-- <%
                        }
                    %> --%>
						<li><a href="/login?action=logout"><spring:message
									code="user.menu.logout" /> </a></li>
					</ul>
				</div>
				<%--  <%
                }
            %> --%>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-3">
					<a href="<spring:url value="/"/>"> 
						<img src="<spring:url value="/img/logo.png" />" />
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
							<li><a href="/page/about"><spring:message
										code="header.menu.about" /> </a></li>
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
		<div class="btn-group ib">
			<%-- <a <%if (!lang.equals("en")) {%> href="/language?val=en" <%}%>
				class="btn btn-default <%if (lang.equals("en")) {%>active<%}%>">English</a>
			<a <%if (!lang.equals("uk")) {%> href="/language?val=uk" <%}%>
				class="btn btn-default <%if (lang.equals("uk")) {%>active<%}%>"><%="\u0423\u043A\u0440\u0430\u0457\u043D\u0441\u044C\u043A\u0430"%></a> --%>
			<a href="${localeEn}">English</a> <a href="${localeUk}">Українська</a>
		</div>
	</div>
</body>
</html>
