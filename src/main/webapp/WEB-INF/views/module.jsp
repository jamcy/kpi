<%@page import="model.entity.Room"%>
<%@page import="model.dao.RoomDao"%>
<%@page import="model.entity.Task"%>
<%@page import="model.entity.TaskLog"%>
<%@page import="resource.VmlResources"%>
<%@page import="model.entity.Module"%>

<%
	Module m = (Module) request.getAttribute("module");
	String init = (String) request.getAttribute("module-init");
	Task task = (Task) request.getAttribute("task");
	TaskLog tsk_status = (TaskLog) request.getAttribute("task-status");
	String mode = (String) request.getAttribute("module-mode");
	String task_mode = (String) request.getAttribute("task-mode");
	Room rm = new RoomDao().selectById(m.getRoomId());
%>

<!-- <script>
	$(document).ready(function() {		
		$('#module-launch').click(function() {
			$('#module-popover').show();
		});
		$('#module-popover-close').click(function() {
			$('#module-popover').hide();
		});
		/*$("#launch").click(function(ev){
			$("#launch_window").css("display","block");
		});*/
	});
</script> -->

<div class="container">
	<ol class="breadcrumb">
		<li><a href="/">Home</a></li>
		<li><a href="/room?id=<%=rm.getId()%>"><%=rm.getName().getByLanguage(lang)%></a></li>
		<li class="active"><%=m.getName().toString(lang)%></li>
	</ol>
	<div class="row">
		<div class="col-xs-6 col-sm-6 col-md-7 col-lg-8 exp-desc">
			<h1><%=m.getName().toString(lang)%></h1>

			<div class="tabbable2">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#pane1" data-toggle="tab"><%=messages.getString("page.module.description")%></a></li>
					<%
						if (task != null) {
					%>
					<li><a href="#pane2" data-toggle="tab"><%=messages.getString("page.module.task")%></a></li>
					<li><a href="#pane3" data-toggle="tab"><%=messages.getString("page.module.tdesc")%></a></li>
					<%
						}
					%>

				</ul>

				<div class='divider'></div>

				<div class="tab-content">
					<div id="pane1" class="tab-pane active">
						<div class="tab-container">
							<div class="exp-desc">
								<%=m.getDescription().toString(lang)%>
								<div class="divider"></div>
								<%=m.getContent().toString(lang)%>
							</div>
						</div>
					</div>
					<%
						if (task != null) {
					%>
					<div id="pane2" class="tab-pane">
						<div class="tab-container">
							<div class="exp-desc"><%=task.getTask().toString(lang)%></div>
						</div>
					</div>
					<div id="pane3" class="tab-pane">
						<div class="tab-container">
							<div class="exp-desc"><%=task.getTheory().toString(lang)%></div>
						</div>
					</div>
					<%
						}
					%>
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
				Student: <%=user.getFirstName() + " " + user.getLastName()%> <br/>
				Task: <%=task.getName() %>
			</h4>
			<div class='center'>
				<a
					href="/module/task?action=status&status=checked&lid=<%=tsk_status.getId()%>"
					class="btn btn-primary btn-choice"><%=messages.getString("task-accept")%></a>
				<a
					href="/module/task?action=status&status=rejected&lid=<%=tsk_status.getId()%>"
					class="btn btn-danger btn-choice"><%=messages.getString("task-decline")%></a>
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
		src="/module/app?id=<%=m.getId()%><%=(mode == null) ? "" : ("&mode=" + mode + (mode
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

	<%-- <div class="launch_window">
			<div id="module-embed">
				<%=m.getEmbedCode() %>
				<iframe
					src="/module/app?id=<%=m.getId()%><%=(mode == null) ? "" : ("&mode=" + mode + (mode
						.equals("task") ? ("&taskid=" + task.getId())
						: ("&tlgid=" + tsk_status.getId())))%>"
					width="1230" height="640" align="center" frameborder="no">
					Ваш браузер не поддерживает плавающие фреймы! </iframe>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div style="display: none" id="lab-data"></div>

			<div id="lab-buttons"></div>
		</div> --%>
</div>

<!-- Modal -->
<%--
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 100%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"
					style="font-size: 56px; position: absolute; right: 5px; margin-top: -20px;">&times;</button>
			</div>
			<div class="modal-body" style="text-align: center">
				<div class="launch_window">
					<div id="module-embed">
						<%=m.getEmbedCode()%> 
						<iframe
							src="/module/app?id=<%=m.getId()%><%=(mode == null) ? "" : ("&mode=" + mode + (mode
					.equals("task") ? ("&taskid=" + task.getId())
					: ("&tlgid=" + tsk_status.getId())))%>"
							width="1230" height="640" align="center" frameborder="no">
						</iframe>
						<div class="clear"></div>
					</div>
					<div class="clear"></div>
					<div style="display: none" id="lab-data"></div>

					<div id="lab-buttons"></div>
				</div>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
 --%>
