<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="ua.kpi.eec.vml.model.entity.Room"%>
<%@page import="java.util.List"%>
<%
	@SuppressWarnings({ "unchecked" })
	List<Room> rooms = (List<Room>) request.getAttribute("rooms");
%>
<t:master>
	<jsp:body>
	<div id="present" class=" present">
		<div id="vml3d" class="container"></div>
		<img src="/images/present_main2.jpg" />
		<div class="container">
			<div class="over">
				<h1>
					<b><spring:message code="name" /></b>
				</h1>
				<h2>
					<spring:message code="vml-credo" />
				</h2>
				<h4>
					<a class="overlink" href="/page/about">Learn more &gt;&gt;</a>
				</h4>
				<!--<h4><a id="demo" class="btn btn-success" href="#">View Demo</a></h4>-->
			</div>
		</div>
	</div>
	<div id="collapse">
		<div class="container">
			<div class="divider"></div>
			<div class="center">
				<h2>Discover our experiment rooms</h2>
			</div>
			<div class="divider"></div>


			<%-- <div class="rooms"
					style="width: 100%; display: block; text-align: center">
					<c:forEach items="{rooms}"></c:forEach>
				<%
					for (Room room : rooms) {
				%>
				<div class="room-wrap">
					<div class="room" room-id="<%=room.getId()%>">
						<a href="<%="/room?id=" + Long.toString(room.getId())%>"
								class="link">
							<div class="name">
								<b><%=room.getName().toString(lang)%></b>
							</div>
							<div class="body">
								<img src="" />
								<div class="experiments"></div>
							</div>
						</a>
					</div>
				</div>
				<%
					}
				%>
			</div> --%>
			<div class="divider"></div>
			<div class="center">
				<h3 class="p">
						<spring:message code="header.menu.mission" />
					</h3>
			</div>
			<h4>
				<spring:message code="vml-mission" />
			</h4>
			<div class="divider"></div>


		</div>
	</div>

<!-- <script src="js/3ds/graphics_room.js"></script>
<script src="js/3ds/audio_room.js"></script>
<script src="js/3ds/app_room.js"></script> -->
	<script type="text/javascript">
		$($(".room a .body img")[0]).attr("src", "images/room_graphics.jpg");
		$($(".room a .body img")[1]).attr("src", "images/room_audio.jpg");
		$($(".room a .body img")[2]).attr("src", "images/room_app.jpg");
	</script>
	</jsp:body>
</t:master>

