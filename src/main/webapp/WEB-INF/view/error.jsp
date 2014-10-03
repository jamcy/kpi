<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>

<%
	String message = (String) request.getAttribute("message");
%>
<t:master>
	<jsp:body>
<div class="contentHeader">
	<div class="page-title">Error</div>
</div>
<div class="contentCenter">
	<div id="error-message"><%=message%></div>
	<div class="clear"></div>
</div>
<div class="contentFooter"></div>
</jsp:body>
</t:master>