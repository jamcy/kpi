<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<t:master>
<jsp:body>
<div class="row">
	<div class="col-md-1">
		<div class="btn-group-vertical">
			<a href="<s:url value="/admin/courses" />" class="btn btn-default">Courses</a>
			<a href="<s:url value="/admin/users" />" class="btn btn-default">Users</a>
			<a href="<s:url value="/admin/pages" />" class="btn btn-default">Pages</a>
			<a href="<s:url value="/admin/module/list" />" class="btn btn-default">Modules</a>
		</div>
	</div>
	<div class="col-md-10">
	<c:if test="${not empty view }">
	<jsp:include page="admin/${view }.jsp" />
	</c:if>
	</div>
</div>
</jsp:body>
</t:master>


