<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>


<jsp:directive.page language="java" pageEncoding="UTF-8" />

<table class="table table-bordered table-condensed">
    <thead><tr>
    	<th>Name</th>
    	<th>Actions</th>
    </tr></thead>
    <c:forEach items="${modules}" var="module" >
    <tr>
    	<td><u:i18n value="${module.name }" /></td>
    	<td><a href="<s:url value="/admin/module/edit/${module.id }" />" class="btn btn-primary">Edit</a></td>
        <%-- TODO: add action delete --%>
    </tr>
    </c:forEach>
</table>
<a href="<s:url value="/admin/module/add" />" class="btn btn-primary">Create module</a>