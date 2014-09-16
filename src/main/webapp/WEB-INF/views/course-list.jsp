
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.TaskDao"%>
<%@page import="model.entity.Task"%>
<%@page import="model.entity.TaskLog"%>
<%@page import="model.dao.TaskLogDao"%>
<%@page import="model.entity.Course"%>
<%@page import="java.util.List"%>
<%
	try {
		List<Course> courses = (List<Course>) request
				.getAttribute("courses");
		List<TaskLog> labs = new TaskLogDao().selectByUserId(user
				.getMoodleId());
%>

<div class="container">
	<ol class="breadcrumb">
		<li><a href="/">Home</a></li>
		<li class="active">Workspace</li>
	</ol>
	<div class="row">
		<div class="col-xs-6 col-sm-6 col-md-7 col-lg-8 exp-desc">
			<h3 class="p"><%=messages.getString("page.course-list.title")%></h3>
			<ul class="courses">
				<%
					for (Course course : courses) {
				%>
				<li>
					<div class="row">
						<a href="/course?id=<%=course.getId()%>" class="wrap-link">
							<div class="col-md-6 ">
								<%
									//TODO remove hardcoded
									System.out.println("TODO: remove hardcoded course images");
									String courseImg="/images/";
									int crsid = (int)course.getId();
									switch(crsid) {
									case 2:
										courseImg+="course_1.jpg";
										break;
									case 3:
										courseImg+="course_2.jpg";
										break;
									case 4:
										courseImg+="course_3.jpg";
										break;
									default:
										courseImg+="course_placeholder.jpg";
									}
								%>
								<img class="mini-course" src="<%=courseImg %>" />
							</div>
							<div class="col-md-6">
								<h4><%=course.getName().toString(lang)%></h4>
								<%
											
											if (user.getRole(course.getId()).equals(
													CourseRole.CR_STUDENT)) {
												int st1 = 0;
												int st2 = 0;
												int st3 = 0;
												int st4 = 0;
												List<Task> tasks = new TaskDao().selectByCourseId(course
														.getId());
												ArrayList<Long> taskIds = new ArrayList<Long>();
												for (Task t : tasks) {
													taskIds.add(t.getId());
												}
												for (TaskLog tl : labs) {
													if (taskIds.contains(tl.getTask())) {
														taskIds.remove(tl.getTask());
														if (tl.getStatus().equals(TaskLog.TS_PROGRESS)) {
															st1++;
															continue;
														}
														if (tl.getStatus().equals(TaskLog.TS_COMMITED)) {
															st2++;
															continue;
														}
														if (tl.getStatus().equals(TaskLog.TS_CHECKED)) {
															st4++;
															continue;
														}
														if (tl.getStatus().equals(TaskLog.TS_REJECTED)) {
															st3++;
															continue;
														}
													}
												}
												st1 += taskIds.size();
								%>

								<h4>
									<span class="gray">Labs: </span> <span
										class="label label-default"><%=st1%></span> <span
										class="label label-info"> <%=st2%></span> <span
										class="label label-danger"> <%=st3%></span> <span
										class="label label-success"><%=st4%></span>
								</h4>
								<%
									}
									if(user.getRole(course.getId()).equals(CourseRole.CR_TEACHER)) {
										int committed = 0;
										TaskLogDao tlg = new TaskLogDao();
										List<Task> tasks = new TaskDao().selectByCourseId(course.getId());
										List<TaskLog> logs = new LinkedList<TaskLog>();
										for(Task t : tasks) {
											logs.addAll(tlg.selectByTaskId(t.getId()));
										}
										for(TaskLog tl : logs) {
											if(tl.getStatus().equals(TaskLog.TS_COMMITED)) {
												committed++;
											}
										}
										%>
										<h4>
										<span class="gray">Committed labs: </span> 
										 <span
										class="label label-info"> <%=committed %></span>
										</h4>
										<%
									}
								%>
							</div>
						</a>
					</div>
				</li>
				<%
					}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				%>
			</ul>

		</div>
		<!--
			<div class="col-xs-6 col-sm-6 col-md-5 col-lg-4">
				<h3 class="p gray">Ruslan Hadyniak</h3>					
				<div class="user-info">	
					<div class="user-img-wrap ">
						<img class="user-img" src="images/dev2.jpg" />				
					</div>
					<div class="user-about">
						<h4 class="p"> About<h4>
						<h5>Student of NTUU 'KPI'. Bachelor. Google bla-bla participant. Bla-bla active bla</h5>
					</div>
				</div>				
				<div class="divider"></div>
				<div class="user-info">	
				<h4 class="p"> Some statistics<h4>
				<h5>Bla-bla bla. Bla-bla bla. Bla-bla bla.</h5>					
				</div>				
			</div>
			-->
	</div>
	<div class="divider"></div>
	<!--
		<div class="center">
			<h4 class="p">DEVELOPER OF</h4>
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



