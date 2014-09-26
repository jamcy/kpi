<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%-- <%
    //Room room = (Room) request.getAttribute("room");
   /*  List<Module> modules = (List<Module>) request
            .getAttribute("modules"); */
    //TODO remove hardcoded room pictures
    /* String rmPic = "/images/";
    int rmId = (int) room.getId();
    switch (rmId) {
        case 1:
            rmPic += "room_graphics.jpg";
            break;
        case 2:
            rmPic += "room_audio.jpg";
            break;
        case 3:
            rmPic += "room_app.jpg";
            break;
        default:
            rmPic += "logo.png";
    } */
%> --%>
<t:master>
	<jsp:body>
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="/">Home</a></li>
			<%-- <li class="active"><a href="#"><%=room.getName().toString(lang)%> --%>
			<!-- </a></li> -->
		</ol>
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-7 col-lg-8 exp-desc">
				<h1>
					<%-- <%=room.getName().toString(lang)%> --%>
				</h1>

				<h3 class="p">Description</h3>

				<div class="exp-desc">
					<h5>
						<%-- <%=messages.getString("room-desc-" + room.getId()) %> --%>
					</h5>
				</div>


			</div>
			<div class="col-xs-6 col-sm-6 col-md-5 col-lg-4">
				<div class='aright'>
					<div class="room-static "
							style='background-color: inherit; box-shadow: none;'>
						<div class="body" id="container"
								style='background-color: inherit;'>
							<img src="<%-- <%=rmPic %> --%>" style="height: 220px;" />
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="divider"></div>
		<div class="center">
			<h4 class="p">ROOM EXPERIMENTS</h4>
		</div>

		<div class="row">
			<%-- <%
            if (modules != null) {
                for (Module module : modules) {
        %> --%>
			<div class="exp-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
				<div class="exp">
					<div class="body" id="container2">
						<%-- <a href="/module?id=<%=module.getId()%>">
                        <img src="<%=module.getPicture() %>" alt="<%=module.getName().toString(lang) %>"/>

                        <div class="over"></div>
                    </a> --%>

					</div>
					<div class="desc">
						<%--  <b><%=(module.getName() == null) ? "" : module.getName().toString(lang) %>
                    </b>
                    <h6><%=module.getDescription().getByLanguage(lang) %>
                    </h6>
                    <a href="/module?id=<%=module.getId()%>" class="link">More &gt;&gt;</a> --%>
					</div>
				</div>
			</div>
			<%-- <%
                }
            }
        %> --%>
		</div>
		<div class="divider"></div>
	</div>
	<!--
<div class="center">
<h4 class="p">EXPERIMENT DEVELOPERS</h4>
</div>
<div class="row">
<div class="dev-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
<div class="dev">
<a href="#" ><img src="images/dev1.jpg"/></a>
<div class="desc">
<h4>Petro Ivanovi4</h4>
<h6>Lead developer. CAthedral assistant</h6>
<h5><b>6</b> Experiments</h5>
</div>
</div>
</div>
<div class="dev-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
<div class="dev">
<a href="#" ><img src="images/dev2.jpg"/></a>
<div class="desc">
<h4>Ivan Petrovich</h4>
<h6>Lead developer. CAthedral assistant</h6>
<h5><b>0</b> Experiments</h5>
</div>
</div>
</div>
<div class="dev-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
<div class="dev">
<a href="#" ><img src="images/dev3.jpg"/></a>
<div class="desc">
<h4>Olooevich</h4>
<h6>Lead developer. CAthedral assistant</h6>
<h5><b>2</b> Experiments</h5>
</div>
</div>
</div>
<div class="dev-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
<div class="dev">
<a href="#" ><img src="images/dev4.jpg"/></a>
<div class="desc">
<h4>Nostal'ji</h4>
<h6>Lead developer. CAthedral assistant</h6>
<h5><b>666</b> Experiments</h5>
</div>
</div>
</div>
</div>
-->

	<!-- <script src="js/3ds/globe.js"></script> -->
	</jsp:body>
</t:master>