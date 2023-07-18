<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<t:master>
<jsp:body>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="/">Home</a></li>
		<li class="active">Workspace</li>
	</ol>
	<div class="row">
		<div class="col-xs-6 col-sm-6 col-md-7 col-lg-8 exp-desc">
			<h3 class="p"><s:message code="page.course-list.title"/></h3>
			<ul class="courses">
				<c:forEach items="${courses }" var="course">
				<li>
					<div class="row">
						<a href="<s:url value="/course?id=${course.id }" />" class="wrap-link">
							<div class="col-md-6 ">
								<img class="mini-course" src="<s:url value="/static/${course.imageUrl} "/>" 
									alt="<u:i18n value="${course.name }"/>" />
							</div>
							<div class="col-md-6">
								<h4><u:i18n value="${course.name }" /> </h4>
								<%-- <h4>
									<span class="gray">Labs: </span> <span
										class="label label-default"><%=st1%></span> <span
										class="label label-info"> <%=st2%></span> <span
										class="label label-danger"> <%=st3%></span> <span
										class="label label-success"><%=st4%></span>
								</h4>
								<h4>
									<span class="gray">Committed labs: </span> 
								 	<span class="label label-info"> <%=committed %></span>
								</h4> --%>
							</div>
						</a>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="divider"></div>
</div>
</jsp:body>
</t:master>



