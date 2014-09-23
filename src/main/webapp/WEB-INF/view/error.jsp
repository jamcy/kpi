
<%
	String message = (String) request.getAttribute("message");
%>
<div class="contentHeader">
	<div class="page-title">Error</div>
</div>
<div class="contentCenter">
	<div id="error-message"><%=message %></div>
	<div class="clear"></div>
</div>
<div class="contentFooter"></div>