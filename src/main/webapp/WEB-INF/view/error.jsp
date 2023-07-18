<%@taglib prefix="t" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<t:master>
<jsp:body>
<div class="contentHeader">
	<div class="page-title">Error</div>
</div>
<div class="contentCenter">
	<div id="error-message"><s:message code="${message }" /> </div>
	<div class="clear"></div>
</div>
<div class="contentFooter"></div>
</jsp:body>
</t:master>