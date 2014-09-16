<%@page import="model.entity.Module" %>
<%@page import="java.util.Map" %>
<%@page import="model.entity.Task" %>
<%@page import="java.util.List" %>
<%
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
    Map<Long, String> moodleTasks = (Map<Long, String>) request
            .getAttribute("moodle_tasks");
    List<Module> modules = (List<Module>) request.getAttribute("modules");
%>

<table class="table">
    <thead>
    <th>Name</th>
    <th>Actions</th>
    </thead>
    <%
        for (Task task : tasks) {
    %>
    <tr>
        <td><%=task.getName()%>
        </td>
        <td><a href="/admin/task?action=edit&id=<%=task.getId()%>" class="btn btn-primary">Edit</a></td>
        <!--<td><a href="/admin/task?action=delete&id=<%=task.getId()%>" class="btn btn-danger">delete</a>-->
    </tr>
    <%
        }
    %>
</table>
<%
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
%>
