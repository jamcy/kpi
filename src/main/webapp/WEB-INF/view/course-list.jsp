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
	</div>
	<div class="divider"></div>
</div>
</jsp:body>
</t:master>



