package ua.kpi.eec.vml.controller;

import org.springframework.stereotype.Controller;


@Controller
public class TaskController {
	public void task() {
//		String action = rd.getParameter("action");
//		Long id = null;
//		try {
//			id = Long.parseLong(rd.getParameter("id"));
//		} catch (NumberFormatException e) {
//		}
//
//		User user = (User) rd.getClientStateAttribute("user");
//
//		// do task
//		if (user != null && action == null) {
//			Task task = new TaskDao().selectById(id);
//			if (task == null) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "No such task");
//				return;
//			}
//			if(!user.getRole(task.getCourseId()).equals(CourseRole.CR_STUDENT)) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "You must have a student role to do this");
//				return;
//			}
//			
//			resp.setPageDataAttribute("task-mode", "execute");
//			resp.setPageDataAttribute("module-mode", "task");
//			resp.setPageDataAttribute("task", task);
//			resp.setPageDataAttribute("task-status",
//					new TaskLogDao().select(task.getId(), user.getMoodleId()));
//			resp.setPageDataAttribute("module",
//					new ModuleDao().selectById(task.getModuleId()));
//			resp.setNextView("module");
//			return;
//		}
//		
//		// view task
//		if (user != null && action.equals("view")) {
//			TaskLog tlg = new TaskLogDao().selectById(id);
//			if (tlg == null) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "No such task log");
//				return;
//			}
//			Task task = new TaskDao().selectById(tlg.getTask());
//
//			if (!user.getRole(task.getCourseId()).equals(CourseRole.CR_TEACHER)) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message",
//						"You must have teacher rights to check tasks");
//				return;
//			}
//
//			resp.setPageDataAttribute("task-mode", "check");
//			resp.setPageDataAttribute("module-mode", "view");
//			resp.setPageDataAttribute("task", task);
//			resp.setPageDataAttribute("task-status", tlg);
//			resp.setPageDataAttribute("module",
//					new ModuleDao().selectById(task.getModuleId()));
//			resp.setNextView("module");
//			return;
//		}
//
//		// save progress
//		if (user != null && action.equals("save")) {
//			
//			if (id == null) {
//				resp.setRaw(true);
//				resp.setPageDataAttribute("content", new Gson()
//						.toJson(new RequestError("No such task", 105)));
//				return;
//			}
//
//			Task task = new TaskDao().selectById(id);
//
//			if (!user.getRole(task.getCourseId()).equals(CourseRole.CR_STUDENT)) {
//				resp.setRaw(true);
//				resp.setPageDataAttribute("content", new Gson()
//						.toJson(new RequestError(
//								"Operation is not permitted for non-students",
//								110)));
//				return;
//			}
//
//			TaskLogDao tlgd = new TaskLogDao();
//			TaskLog tlg = tlgd.select(id, user.getMoodleId());
//			FileDao fdao = new FileDao();
//
//			if (tlg == null) {
//				tlg = new TaskLog();
//				tlg.setFile(fdao.generateName("/tasks"));
//				tlg.setTask(id);
//			} else {
//				if (tlg.getStatus().equals(TaskLog.TS_CHECKED)) {
//					resp.setRaw(true);
//					resp.setPageDataAttribute("content", new Gson()
//							.toJson(new RequestError("Task is readonly", 153)));
//					return;
//				}
//			}
//			System.out.println(ConfigurationHelper.getRootDir());
//			tlg.setUser(user.getMoodleId());
//			tlg.setStatus(TaskLog.TS_PROGRESS);
//
//			if (rd.getParameter("data") == null) {
//				resp.setRaw(true);
//				resp.setPageDataAttribute("content", new Gson()
//						.toJson(new RequestError("No data to save", 114)));
//				return;
//			}
//			try {
//				fdao.save("/tasks", tlg.getFile(), new ByteArrayInputStream(rd
//						.getParameter("data").getBytes("UTF-8")));
//			}catch(Exception e) {
//				
//			}
//			
//			tlgd.saveOrUpdate(tlg);
//
//			resp.setRaw(true);
//			resp.setPageDataAttribute("content",
//					new Gson().toJson(new Response("OK")));
//			return;
//		}
//
//		
//		// change status
//		if (action.equals("status")) {
//			//Task task = new Task();
//			String status = rd.getParameter("status");
//			/*if (user.getRole(task.getCourseId()).equals(
//					CourseRole.CR_NOTENROLLED)
//					|| user.getRole(task.getCourseId()).equals(
//							CourseRole.CR_MODERATOR)) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "No rights");
//				return;
//			}*/
//			/*if ((status.equals(TaskLog.TS_PROGRESS) || status
//					.equals(TaskLog.TS_COMMITED))
//					&& user.getRole(task.getCourseId()).equals(
//							CourseRole.CR_TEACHER)
//					|| (status.equals(TaskLog.TS_REJECTED) || status
//							.equals(TaskLog.TS_CHECKED))
//					&& user.getRole(task.getCourseId()).equals(
//							CourseRole.CR_STUDENT)) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "NO rights2");
//				return;
//			}*/
//
//			TaskLog tlg = null;
//			
//			if(rd.getParameter("tid")!=null) {
//				Long tid = Long.parseLong(rd.getParameter("tid"));
//				tlg = new TaskLogDao().select(tid, user.getMoodleId());
//			} else {
//				Long lid = Long.parseLong(rd.getParameter("lid"));
//				tlg = new TaskLogDao().selectById(lid);
//			}
//			
//			if (tlg == null) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "No record");
//				return;
//			}
//			
//			tlg.setStatus(status);
//
//			new TaskLogDao().saveOrUpdate(tlg);
//
//			resp.setRedirect(true);
//			resp.setRedirectAddress("/course");
//		}
	}
}
