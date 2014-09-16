<%
    String contentViewName = (String) request.getAttribute("content_view");
    String contentViewPath = contentViewName == null ? null : "/WEB-INF/fragment/admin/" + contentViewName + ".jsp";
%>
<style></style>
<div class="contentCenter">
    <table class="table">
        <tr>
            <td><a href="/admin/course">Courses</a></td>
            <td><a href="/admin/user">Users</a></td>
            <td><a href="/admin/page">Pages</a></td>
            <td><a href="/admin/module">Modules</a></td>
        </tr>
    </table>
    <%
        if (contentViewPath != null) {
    %>
    <jsp:include page='<%=contentViewPath%>'/>
    <%
        }
    %>
    <div style="clear: both;"></div>
</div>
<div class="contentFooter"></div>
