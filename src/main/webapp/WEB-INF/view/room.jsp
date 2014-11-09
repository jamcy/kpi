<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<t:master>
<jsp:body>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="<s:url value="/" />">Home</a></li>
		<li class="active"><u:i18n value="${room.name }"/></li>
	</ol>
	<div class="row">
		<div class="col-xs-6 col-sm-6 col-md-7 col-lg-8 exp-desc">
			<h1><u:i18n value="${room.name }"/></h1>
			<!-- TODO: move description to properties -->
			<h3 class="p">Description</h3>
			<div class="exp-desc">
			<!-- TODO: remove bad workaround -->
				<h5><%-- <%=messages.getString("room-desc-" + room.getId()) %> --%></h5>
			</div>
		</div>
		<div class="col-xs-6 col-sm-6 col-md-5 col-lg-4">
			<div class='aright'>
				<div class="room-static" style='background-color: inherit; box-shadow: none;'>
					<div class="body" id="container" style='background-color: inherit;'>
						<img src="<s:url value="/static/${room.imageUrl}"/>" style="height: 220px;" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${not empty room.modules}">
	<div class="divider"></div>
	<div class="center">
		<h4 class="p">ROOM EXPERIMENTS</h4>
	</div>
	<div class="row">
	<c:forEach items="${room.modules}" var="module">
		<s:url value="/module?id=${module.id}" var="moduleUrl"/>
		<div class="exp-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
			<div class="exp">
				<div class="body" id="container2">
					<a href="${moduleUrl }">
                    	<img src="<s:url value="/static/${module.imageUrl}" />" />
                		<div class="over"></div>
                	</a>
				</div>
				<div class="desc">
					<b><u:i18n value="${module.name}"/></b>
                   	<h6><u:i18n value="${module.description}"/></h6>
                	<a href="${moduleUrl }" class="link">More &gt;&gt;</a>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
	<div class="divider"></div>
	</c:if>
</div>
</jsp:body>
</t:master>