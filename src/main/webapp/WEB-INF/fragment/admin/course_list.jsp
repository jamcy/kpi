<%@page import="java.util.HashMap" %>
<%@page import="model.entity.Course" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ResourceBundle" %>
<%
    String lang = (String) session.getAttribute("lang");
    ResourceBundle messages = (ResourceBundle) request
            .getAttribute("messages");
    String prefix = request.getContextPath();
    List<Course> courses = (List<Course>) request
            .getAttribute("courses");
    HashMap<Long, String> moodleCourses = (HashMap<Long, String>) request
            .getAttribute("moodle_courses");
%>
<table class="table">
    <thead>
    <th>Name</th>
    <th>Actions</th>
    </thead>
    <%
        for (Course c : courses) {
    %>
    <tr>
        <td><%=c.getName().toString(lang)%>
        </td>
        <td><a href="/admin/task?courseid=<%=c.getId() %>" class="btn btn-primary">Edit tasks</a>
            <a href="/admin/course?action=edit&id=<%=c.getId()%>" class="btn btn-primary">Edit</a>
        </td>
        <!--<td><a href="/admin/course?action=delete&id=<%=c.getId()%>" class="btn btn-danger">Delete</a>-->
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
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
%>