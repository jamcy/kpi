<%@page import="java.util.List" %>
<%@page import="model.entity.User" %>
<%@page import="java.util.Locale" %>
<%@page import="java.util.ResourceBundle" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
    ResourceBundle messages = ResourceBundle.getBundle(
            "resource.VmlResources", new Locale(lang));
%>

<table class="table">
    <thead>
    <th>Name</th>
    <th>System role</th>
    <th>Actions</th>
    </thead>

    <%
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getFirstName() + " " + user.getLastName()%>
        </td>
        <td><%=user.getRole()%>
        </td>
        <td><a href="/admin/user?action=edit&id=<%=user.getMoodleId()%>" class="btn btn-primary">Edit roles</a></td>
    </tr>
    <%
        }
    %>
</table>