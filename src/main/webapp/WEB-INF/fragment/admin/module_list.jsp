<%@page import="model.form.ModuleAddForm" %>
<%@page import="model.entity.Room" %>
<%@page import="model.entity.Module" %>
<%@page import="java.util.List" %>
<%
    List<Room> rooms = (List<Room>) request.getAttribute("rooms");
    List<Module> modules = (List<Module>) request.getAttribute("modules");
    String lang = (String) request.getAttribute("lang");
    ModuleAddForm data = (ModuleAddForm) request.getAttribute("form_data");
    if (lang == null) {
        lang = "en";
    }
%>

<table class="table">
    <thead>
    <th>Name</th>
    <th>Actions</th>
    </thead>
    <%for (Module module : modules) { %>
    <tr>
        <td><%=module.getName().toString(lang) %>
        </td>
        <td><a href="/admin/module?action=edit&id=<%=module.getId() %>" class="btn btn-primary">Edit</a></td>
        <!--<td><a href="/admin/module?action=delete&id=<%=module.getId() %>">delete</a>--></td>
    </tr>
    <%} %>
</table>

<form action="/admin/module?action=add" method="post" style="width: 250px;">
    <div class="form-group">
        <input type="text" name="short-name" id="short-name" class="form-control" value="<%=data.getShortName()%>"
               placeholder="Module short name"/>
    </div>
    <div class="form-group">
        <select name="room-id" id="room-id" class="form-control">
            <%for (Room room : rooms) { %>
            <option value="<%=room.getId()%>"><%=room.getName().toString(lang) %>
            </option>
            <%} %>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Create module</button>
</form>