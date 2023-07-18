<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="value"  required="true" type="ua.kpi.eec.vml.model.entity.I18n" %>
<%@attribute name="escape" type="java.lang.Boolean" %>

<c:set var="locale" value="${pageContext.response.locale }" />
<c:if test="${empty escape}">
	<c:set var="escape" value="true" />
</c:if>

<c:if test="${(not empty value) and (not empty value.i18nValues) }">
	<c:forEach var="i18nValue" items="${value.i18nValues }">
		<c:if test="${i18nValue.languageCode.code eq locale}">
			<c:out escapeXml="${escape }" value="${i18nValue.content}" />	
		</c:if>
	</c:forEach>
</c:if>