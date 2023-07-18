<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<table class="table">
    <thead><tr>
	    <th>Name</th>
	    <th>Actions</th>
    </tr></thead>
    <c:forEach items="${tasks }" var="task">
    <tr>
        <td><u:i18n value="${task.name }"/></td>
        <td><a href="/admin/task?action=edit&id=${task.id }" class="btn btn-primary">Edit</a></td>
        <!-- TODO: add delete task action -->
    </tr>
    </c:forEach>
</table>
<!-- TODO: import moodle tasks -->
<%-- <%
    if (moodleTasks != null && moodleTasks.size() != 0) {
%>
<form action="/admin/task?action=add" method="post" id="add-task">
    <div class="form-name">Import task</div>
    <input type="hidden" name="course-id"
           value="<%=request.getAttribute("course_id")%>"/> <!-- <input
		type="hidden" name="task-name" value="sometask" /> -->
    <label>Moodle task: <select
            name="task-id">
        <%
            for (Long id : moodleTasks.keySet()) {
        %>
        <option value="<%=id%>"><%=moodleTasks.get(id)%>
        </option>
        <%
            }
        %>
    </select></label>
    <br>
    <label>Task module: <select name=module-id>
        <% for (Module m : modules) { %>
        <option value=<%=m.getId() %>><%=m.getName().toString(lang) %>
        </option>
        <%} %>
    </select>
    </label>
    <br>
    <button type="submit">Submit</button>
</form>
<%
    }
%> --%>
