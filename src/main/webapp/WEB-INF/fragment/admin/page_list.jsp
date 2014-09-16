<%@page import="java.util.List" %>
<%@page import="model.entity.Page" %>
<%
    List<Page> pages = (List<Page>) request.getAttribute("pages");
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "en";
    }
%>
<%
    if (pages != null && pages.size() != 0) {
%>
<table id="page-list" class="table">
    <thead>
    <th>Name</th>
    <th>Actions</th>
    </thead>
    <%
        for (Page p : pages) {
    %>
    <tr>
        <td><%=(p.getName() == null) ? "" : p.getName().getByLanguage(lang)%>
        </td>
        <td><a href="/admin/page?action=edit&id=<%=p.getId()%>" class="btn  btn-primary">edit</a>
            <!--<a href="/admin/page?action=delete&id=<%=p.getId() %>">delete</a>--></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
<a href="/admin/page?action=add" class="btn btn-primary">Create page</a>