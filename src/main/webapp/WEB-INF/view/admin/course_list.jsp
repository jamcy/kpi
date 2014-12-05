<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<jsp:directive.page language="java" pageEncoding="UTF-8" />

<table class="table table-bordered table-condensed">
    <thead><tr>
	    <th>Name</th>
	    <th>Actions</th>
    </tr></thead>
    <c:forEach items="${courses }" var="course">
    <tr>
        <td><u:i18n value="${course.name }" /></td>
        <td><a href="<s:url value="/admin/tasks/${course.id }" />" class="btn btn-primary">Tasks</a>
            <a href="<s:url value="/admin/course/edit/${course.id }" />" class="btn btn-primary">Edit</a>
        </td>
        <!-- TODO: add action delete -->
    </tr>
    </c:forEach>
</table>
<!-- TODO: add moodle import for courses -->
<%-- <%
    if (moodleCourses != null & moodleCourses.size() != 0) {
%>
<div class="course-import">
    <form action="" method="post">
        <div class="form-name">Import from moodle</div>
        <select name="id">
            <%
                for (Long id : moodleCourses.keySet()) {
            %>
            <option value="<%=id%>"><%=moodleCourses.get(id)%>
            </option>
            <%
                }
            %>
        </select>
        <button type="submit">Submit</button>
    </form>
</div>
<%
    }
%> --%>