<%@taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags/util"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<jsp:directive.page language="java" pageEncoding="UTF-8" />

<t:master>
<jsp:body>

<div class="present">
	<img src="<s:url value="/static/${course.imageUrl }" />" />
	<div class="container">
		<div class="over">
			<h1>
				<b><u:i18n value="${course.name }" /></b>
			</h1>
			<h4>
				<a class="overlink" href="#more">Learn more &gt;&gt;</a>
			</h4>
		</div>
	</div>
</div>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="/">Home</a></li>
		<li><a href="/course">Courses</a></li>
		<li class="active"><%=course.getName().toString(lang)%></li>
	</ol>
	<div class="row">
		<div class="col-xs-6 col-sm-6 col-md-7 col-lg-8 exp-desc">
			<div class="row">
				<%
					if (user.getRole(course.getId()).equals(CourseRole.CR_STUDENT)
							&& tasks != null && tasks.size() > 0) {
				%>
				<div class="tabbable2 badges">
					<h3 class="p">Laboratory works</h3>
					<h3 class="bb">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#pane1" class="link"
								href="#page1"
								<%=(tasks.size() - stChecked.size() - stRejected.size()
						- stCommitted.size() == 0) ? "" : "data-toggle=\"tab\""%>>
									<span class="label label-default"><%=tasks.size() - stChecked.size() - stRejected.size()
						- stCommitted.size()%></span>
							</a></li>
							<li><a href="#pane2"
								<%=(stCommitted.size() == 0) ? ""
						: "data-toggle=\"tab\""%>><span
									class="label label-info"><%=stCommitted.size()%></span></a></li>
							<li><a href="#pane3"
								<%=(stRejected.size() == 0) ? "" : "data-toggle=\"tab\""%>><span
									class="label label-danger"><%=stRejected.size()%></span></a></li>
							<li><a href="#pane4"
								<%=(stChecked.size() == 0) ? "" : "data-toggle=\"tab\""%>><span
									class="label label-success"><%=stChecked.size()%></span></a></li>
						</ul>
					</h3>
					<div class='divider'></div>
					<div class="tab-content">
						<div id="pane1" class="tab-pane active">
							<ul class="works">
								<%
									if (tasks.size() - stChecked.size() - stRejected.size()
												- stCommitted.size() == 0) {
								%>
								<li class="empty">
									<h3 class="p gray">No new tasks</h3>
								</li>
								<%
									}
								%>
								<%
									for (Task ts : tasks) {
											if (stProgress.contains(ts.getId())) {
								%>
								<li>
									<div class="row">
										<a class="wrap-link" href="/module/task?id=<%=ts.getId()%>">
											<div class="col-md-9">
												<h4><%=ts.getName()%></h4>
												<h5>
													Status: <span class="label label-default">Progress</span>
												</h5>
											</div>
											<div class="col-md-3 ">
												<img class="mini-exp" src="/images/edit1.png" />
											</div>
										</a>
									</div>
								</li>
								<%
									}
										}
								%>
								<%
									for (Task ts : tasks) {
											if (stCommitted.contains(ts.getId())
													|| stChecked.contains(ts.getId())
													|| stRejected.contains(ts.getId())
													|| stProgress.contains(ts.getId())) {
												continue;
											}
								%>
								<li>
									<div class="row">
										<a class="wrap-link" href="/module/task?id=<%=ts.getId()%>">
											<div class="col-md-9">
												<h4><%=ts.getTask().getByLanguage(lang)%></h4>
												<h5>
													Status: <span class="label label-default">New</span>
												</h5>
											</div>
											<div class="col-md-3 ">
												<img class="mini-exp" src="/images/edit1.png" />
											</div>
										</a>
									</div>
								</li>
								<%
									}
								%>
							</ul>
						</div>
						<div id="pane2" class="tab-pane">
							<ul class="works">
								<%
									for (Task ts : tasks) {
											if (stCommitted.contains(ts.getId())) {
								%>
								<li>
									<div class="row">
										<a class="wrap-link" href="/module/task?id=<%=ts.getId()%>">
											<div class="col-md-9">
												<h4><%=ts.getTask().getByLanguage(lang)%></h4>
												<h5>
													Status: <span class="label label-info">Commited</span>
												</h5>
											</div>
											<div class="col-md-3 ">
												<img class="mini-exp" src="/images/view1.png" />
											</div>
										</a>
									</div>
								</li>
								<%
									}
										}
								%>

							</ul>
						</div>
						<div id="pane3" class="tab-pane">
							<ul class="works">
								<%
									for (Task ts : tasks) {
											if (stRejected.contains(ts.getId())) {
								%>
								<li>
									<div class="row">
										<a class="wrap-link" href="/module/task?id=<%=ts.getId()%>">
											<div class="col-md-9">
												<h4><%=ts.getTask().getByLanguage(lang)%></h4>
												<h5>
													Status: <span class="label label-danger">Rejected</span>
												</h5>
											</div>
											<div class="col-md-3 ">
												<img class="mini-exp" src="/images/edit1.png" />
											</div>
										</a>
									</div>
								</li>
								<%
										}
									}
								%>
							</ul>
						</div>
						<div id="pane4" class="tab-pane">
							<ul class="works">
								<%
									for (Task ts : tasks) {
											if (stChecked.contains(ts.getId())) {
								%>
								<li>
									<div class="row">
										<a class="wrap-link" href="/module/task?id=<%=ts.getId()%>">
											<div class="col-md-9">
												<h4><%=ts.getTask().getByLanguage(lang)%></h4>
												<h5>
													Status: <span class="label label-success">Success</span>
												</h5>
											</div>
											<div class="col-md-3 ">
												<img class="mini-exp" src="/images/view1.png" />
											</div>
										</a>
									</div>
								</li>
								<%
									}
										}
								%>
							</ul>
						</div>
					</div>
					<!-- /.tab-content -->
				</div>
				<!-- /.tabbable -->
				<%
					}
				%>
				<%
					if (user.getRole(course.getId()).equals(CourseRole.CR_TEACHER)) {
				%>
				<ul class="works">
					<h3>Recently committed labs:</h3>
					<%	
							UserDao udao = new UserDao();
							TaskDao tdao = new TaskDao();
							TaskLogDao tlgdao = new TaskLogDao();
							List<Task> courseTasks = tdao.selectByCourseId(course.getId());
							for(Task t : courseTasks) {
								List<TaskLog> logs=tlgdao.selectByTaskId(t.getId());
								for(TaskLog tlg : logs) {
									if (tlg.getStatus().equals(TaskLog.TS_COMMITED)) {
										User u = udao.selectById(tlg.getUser());
										Task tsk = tdao.selectById(tlg.getTask());
							
					%>
					<li>
						<!--if not in time: class="lab-done" -->
						<div class="row">
							<a href="/module/task?action=view&id=<%=tlg.getId()%>"
								class="wrap-link">
								<div class="col-md-9 ib">
									<h4 class='ib'><%=u.getFirstName() + " " + u.getLastName()%></h4>
									<h4 class='ib  pull-right'>
										<span class="label label-success"> <!-- if not in time: class="label label-warning" -->
											<%=DateFormat.getDateTimeInstance(
								DateFormat.MEDIUM, DateFormat.SHORT,
								messages.getLocale()).format(tlg.getUpdated())%>
										</span>
										<%-- <br/>
														<span>Status: <%=tlg.getStatus() %></span>  --%>
									</h4>
									<h5><%=tsk.getName()%></h5>
								</div>
								<div class="col-md-3 ">
									<img class="mini-exp" src="/images/view1.png" />
								</div>
							</a>
						</div>
					</li>

					<%
									}
								}	
						}
					%>
				</ul>
				<%
					} //teacher block
				%>
			</div>

			<a id="more"></a>
			<h3 class="p"><%=messages.getString("page.course.description")%></h3>
			<div class="exp-desc">
				<h5><%=course.getDescription().toString(lang)%></h5>
			</div>
		</div>
		<!-- <div class="col-xs-6 col-sm-6 col-md-5 col-lg-4 aright">								
				<h3 class="p gray">Course teachers</h3>
				for teacher in teachers
					<h4><a href="#">?teacher_name?</a></h4>				
				endfor			
			</div> -->
	</div>
	<div class="divider"></div>
	<!--	
		<div class="center">
			<h4 class="p">COURSE EXPERIMENTS</h4>
		</div>
		<div class="row">
					<div class="exp-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<div class="exp">		
							<div class="body" id="container2">
								<a href="#">
									<img src="images/app1.jpg" />
									<div class="over"></div>
								</a>
						    </div>
							<div class="desc">
								<b>Virtual Reality</b>
								<h6>Demonstrates the virtual reality the virtual reality the virtual reality the virtual reality the virtual reality</h6>
								<a href="#" class="link">More &gt;&gt;</a>
							</div>
						</div>
					</div>
					<div class="exp-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<div class="exp">							
							<div class="body" id="container2">
								<a href="#">
									<img src="images/app2.jpg" />
									<div class="over"></div>
								</a>

						    </div>
							<div class="desc">
								<b>Virtual Reality</b>
								<h6>Demonstrates the virtual reality the virtual reality the virtual reality the virtual reality the virtual reality</h6>
								<a href="#" class="link">More &gt;&gt;</a>
							</div>
						</div>
					</div>
					<div class="exp-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<div class="exp">							
							<div class="body" id="container2">
								<a href="#">
									<img src="images/app3.jpg" />
									<div class="over"></div>
								</a>

						    </div>
							<div class="desc">
								<b>Virtual Reality</b>
								<h6>Demonstrates the virtual reality the virtual reality the virtual reality the virtual reality the virtual reality</h6>
								<a href="#" class="link">More &gt;&gt;</a>
							</div>
						</div>
					</div>
					<div class="exp-wrap col-xs-12 col-sm-6 col-md-4 col-lg-3">
						<div class="exp">							
							<div class="body" id="container2">
								<a href="#">
									<img src="images/app4.jpg" />
									<div class="over"></div>
								</a>

						    </div>
							<div class="desc">
								<b>Virtual Reality</b>
								<h6>Demonstrates the virtual reality the virtual reality the virtual reality the virtual reality the virtual reality</h6>
								<a href="#" class="link">More &gt;&gt;</a>
							</div>
						</div>
					</div>					
		</div>
		-->
</div>
</jsp:body>
</t:master>


