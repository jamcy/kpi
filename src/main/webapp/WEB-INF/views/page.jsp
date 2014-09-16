<%@page import="model.entity.Page"%>
<%
	Page content = (Page)request.getAttribute("content");
%>

<div class="container">
	<h3 class="p"><%=content.getName().getByLanguage(lang) %></h3>
	<%=content.getContent().getByLanguage(lang) %>
</div>