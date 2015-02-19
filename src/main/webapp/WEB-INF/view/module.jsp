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
		<li><a href="<s:url value="/room?id=${module.room.id}"/>"><u:i18n value="${module.room.name }"></u:i18n></a></li>
		<li class="active"><u:i18n value="${module.name }"/></li>
	</ol>
	<div class="row">
		<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 exp-desc">
			<h3 class="p"><u:i18n value="${module.name }"/></h3>
			<div class="divider"></div>
			<%--<li class="active"><a href="#pane1" data-toggle="tab"><s:message code="page.module.description" /></a></li>
			<c:if test="${not empty task }">
			<li><a href="#pane2" data-toggle="tab"><s:message code="page.module.task"/></a></li>
			<li><a href="#pane3" data-toggle="tab"><s:message code="page.module.tdesc"/></a></li>
			</c:if> --%>
			<h4><u:i18n value="${module.description }" /></h4>
			
			<c:if test="${not empty task }">
			<div id="pane2" class="tab-pane">
				<div class="tab-container">
					<div class="exp-desc"><u:i18n value="${task.task }"/></div>
				</div>
			</div>
			<div id="pane3" class="tab-pane">
				<div class="tab-container">
					<div class="exp-desc"><u:i18n value="${task.theory }" escape="false" /></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
			<a id="launch-module" href="#module-app" class='exp-img-box-box'>
				<div class='exp-img-box'>
					<div class='rel' style="text-align: center;">
						<div class='over center'>
							<img src="<s:url value="/images/launch.jpg"/>" alt="Launch"
								class="btn-launch img-rounded">
						</div>
						<img class="exp-img" src="<s:url value="/static/${module.imageUrl }"/>" />
					</div>
					<div id="module-launch" class="btn launch"><s:message code="page.module.show-module" /></div>
				</div>
			</a>
			<!-- TODO: do task display -->
			<%-- <div class="divider"></div>
			<%
				if (task_mode != null
						&& (tsk_status == null || tsk_status != null
								&& !tsk_status.getStatus().equals(
										TaskLog.TS_CHECKED))) {
			%>
			<%
				if (task_mode.equals("check")) {
			%>
			<h4 class="p">
				Student: <u:i18n value="${user.name }"/> <br/>
				Task: <u:i18n value="${task.name }" />
			</h4>
			<div class='center'>
				<a
					href="/module/task?action=status&status=checked&lid=<%=tsk_status.getId()%>"
					class="btn btn-primary btn-choice"><s:message code="task-accept"/></a>
				<a
					href="/module/task?action=status&status=rejected&lid=<%=tsk_status.getId()%>"
					class="btn btn-danger btn-choice"><s:message code="task-decline" /></a>
			</div>
			<%
				} else {
			%>
			<div class='center'>
				<a
					href="/module/task?action=status&status=commited&tid=<%=task.getId()%>"
					class="btn btn-primary btn-choice"><%=messages.getString("task-commit")%></a>
			</div>
			<%
				}
			%>

			<%
				}
			%>

		</div>
		<iframe id="module" style="display:none; margin-top: 10px; "
		src="/module/app?id=${module.id}<%=(mode == null) ? "" : ("&mode=" + mode + (mode
					.equals("task") ? ("&taskid=" + task.getId())
					: ("&tlgid=" + tsk_status.getId())))%>"
		width="${module.embedWidth }" height="${module.embedHeight }" align="center" frameborder="no"></iframe> --%>
		</div>
	</div>
	<div class="clearfix"></div>
	<%-- <h3><s:message code="page.module.description" /></h3> --%>
	<div class="divider"></div>
	<div id="module-description">
		<u:i18n value="${module.pageContent }" escape="false"/>
	</div>
	<div id="module-app">
		<div class="divider"></div>
		<iframe src="<s:url value="/module/app/${module.folder}" />"
			width="${module.embedWidth }" height="${module.embedHeight }" frameborder="no" >
		</iframe>
	</div>
	<script>
	$(document).ready(function() {
		$("#launch-module").click(function() {
			$("#module-app").show();
		});
	});
	</script>
</div>
</jsp:body>
</t:master>
