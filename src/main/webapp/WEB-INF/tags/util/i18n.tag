<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="value" description="" required="true"
	type="ua.kpi.eec.vml.model.entity.I18n"%>

<c:set var="locale" value="${pageContext.response.locale }" />

<c:forEach var="i18nValue" items="${value.i18nValues }">
	<c:if test="${i18nValue.languageCode.code eq locale}">
		<c:out value="${i18nValue.content}" />
	</c:if>
	<%=request.getRequestURL() %>
	<%=request.getRequestURI() %>
	<%=request.getQueryString() %>
</c:forEach>