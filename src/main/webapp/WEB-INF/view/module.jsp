<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<t:master>
<jsp:body>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="/">Home</a></li>
		<!-- TODO: Fix breadcrumbs -->
		<%-- <li><a href="/room?id=<%=rm.getId()%>"><%=rm.getName().getByLanguage(lang)%></a></li> --%>
		<li class="active"><u:i18n value="${module.name }"/></li>
	</ol>
	<div class="row">
		<div class="col-xs-6 col-sm-6 col-md-7 col-lg-8 exp-desc">
			<h1><u:i18n value="${module.name }"/></h1>
			<div class="tabbable2">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#pane1" data-toggle="tab"><s:message code="page.module.description" /> </a></li>
					<c:if test="${not empty task }">
					<li><a href="#pane2" data-toggle="tab"><s:message code="page.module.task"/></a></li>
					<li><a href="#pane3" data-toggle="tab"><s:message code="page.module.tdesc"/></a></li>
					</c:if>
				</ul>

				<div class='divider'></div>

				<div class="tab-content">
					<div id="pane1" class="tab-pane active">
						<div class="tab-container">
							<div class="exp-desc">
								<u:i18n value="${module.description }" />
								<div class="divider"></div>
								<u:i18n value="${module.content }"/>
							</div>
						</div>
					</div>
					<c:if test="${not empty task }">
					<div id="pane2" class="tab-pane">
						<div class="tab-container">
							<div class="exp-desc"><u:i18n value="${task.task }"/></div>
						</div>
					</div>
					<div id="pane3" class="tab-pane">
						<div class="tab-container">
							<div class="exp-desc"><u:i18n value="${task.theory }"/></div>
						</div>
					</div>
					</c:if>
				</div>
				<!-- /.tab-content -->
			</div>
			<!-- /.tabbable -->
			<div class="divider"></div>
		</div>
		<div class="col-xs-6 col-sm-6 col-md-5 col-lg-4 ">
			<a id="launch_module" href="#module" class='exp-img-box-box'> <!--  data-toggle="modal" data-target="#myModal"> -->
				<div class='exp-img-box'>
					<div class='rel' style="text-align: center;">
						<div class='over center'>
							<img src="/images/launch.jpg" alt="Launch"
								class="btn-launch img-rounded">
						</div>
						<img class="exp-img" src="<%=(m.getPicture()==null || m.getPicture().trim().equals(""))? "/img/logo.png" : m.getPicture() %>" />
					</div>
					<div id="module-launch" class="btn launch"><%=messages.getString("page.module.show-module")%></div>
				</div>
			</a>
			<div class="divider"></div>

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
	</div>

	<iframe id="module" style="display:none; margin-top: 10px; "
		src="/module/app?id=${module.id}<%=(mode == null) ? "" : ("&mode=" + mode + (mode
					.equals("task") ? ("&taskid=" + task.getId())
					: ("&tlgid=" + tsk_status.getId())))%>"
		width="1230" height="640" align="center" frameborder="no"> </iframe>

	<script>
	$(function(){
		function autoResize(id){
		    var newheight;
		    var newwidth;
		    var frame = document.getElementById(id);
		    if(document.getElementById){
		    	if(frame.contentDocument){
		    		newheight = frame.contentDocument.documentElement.scrollHeight+30;
		    	}else{
		        	newheight = frame.contentWindow.document.body.scrollHeight;	
		    	}
		        if(newheight==0){
		        	 newheight= frame.contentWindow.document.body.offsetHeight;
		        }
		        newwidth=document.getElementById(id).contentWindow.document.body.scrollWidth;
		    }

		    document.getElementById(id).height= (newheight) + "px";
		    document.getElementById(id).width= (newwidth+15) + "px";
		}
		
		
		$("#launch_module").click(function(e){
			var id = "module";
			$("#"+id).show();
			autoResize(id);
			});
	})
	</script>
</div>
</jsp:body>
</t:master>
