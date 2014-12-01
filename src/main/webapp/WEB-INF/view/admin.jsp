<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<t:master>
<jsp:body>
<div class="contentCenter">
    <table class="table">
        <tr>
            <td><a href="<s:url value="/admin/courses" />">Courses</a></td>
            <td><a href="<s:url value="/admin/users" />">Users</a></td>
            <td><a href="<s:url value="/admin/pages" />">Pages</a></td>
            <td><a href="<s:url value="/admin/modules" />">Modules</a></td>
        </tr>
    </table>
    <c:if test="${not empty view }">
    <jsp:include page="admin/${view}.jsp" />
    </c:if>
    <div style="clear: both;"></div>
</div>
<div class="contentFooter"></div>
</jsp:body>
</t:master>


