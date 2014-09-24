<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String lang = (String) session.getAttribute("lang");
	if (lang == null) {
		lang = "en";
	}
	//User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" http-equiv="Content-Type"
	content="text/html; charset=utf-8; width=device-width, initial-scale=1.0">
<title>Virtual Multimedia Laboratory</title>
<link rel="shortcut icon" href="/images/favicon.ico" />
<link rel="Stylesheet" href="/css/jquery.ui.css" type="text/css" />
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/css/responsive-main.css" rel="stylesheet" media="screen">
<link href="/css/smoothness/jquery-ui-1.10.4.custom.min.css"
	rel="stylesheet" media="screen">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="../../assets/js/html5shiv.js"></script>
    <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->

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
					<a href="<spring:url value="/"/>"> <img src="/images/logo.png" />

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