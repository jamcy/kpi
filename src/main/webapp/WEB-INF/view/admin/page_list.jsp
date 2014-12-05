<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<table id="page-list" class="table table-bordered table-condensed">
    <thead>
    <tr>
    	<th>Name</th>
    	<th>Actions</th>
    </tr>
    </thead>
    <c:forEach items="${pages }" var="page" >
    <tr>
        <td><u:i18n value="${page.name }" /></td>
        <td><a href="<s:url value="/admin/page/edit/${page.id }" />" class="btn  btn-primary">edit</a></td>
        <!-- TODO: action delete page -->
    </tr>
    </c:forEach>
</table>
<a href="<s:url value="/admin/page/add" />" class="btn btn-primary">Create page</a>