<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="locale" value="${param.locale}"/>
<c:choose>
	<c:when test="${not empty locale }">
		<c:redirect url="/welcome?locale=${locale}"/>
	</c:when>
	<c:otherwise>
		<c:redirect url="/welcome" />
	</c:otherwise>
</c:choose>


