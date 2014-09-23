package ua.kpi.eec.vml.controller;

import java.util.LinkedList;
import java.util.List;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.dao.CourseDao;
import ua.kpi.eec.vml.model.dao.TaskDao;
import ua.kpi.eec.vml.model.dao.TaskLogDao;
import ua.kpi.eec.vml.model.entity.Course;
import ua.kpi.eec.vml.model.entity.CourseRole;
import ua.kpi.eec.vml.model.entity.Task;
import ua.kpi.eec.vml.model.entity.TaskLog;

public class CourseController implements Controller {

	@Override
	public ControllerResponse processRequest(RequestData rd) {

		ControllerResponse resp = new ControllerResponse();
		
		if(rd.getClientStateAttribute("user")==null) {
			resp.setNextView("error");
			resp.setPageDataAttribute("message", "Login  before viewing this page");
			return resp;
		}
//		User user = (User)rd.getClientStateAttribute("user");
//		
//		if(rd.getParameter("id")!=null) {
//			Long id=Long.parseLong(rd.getParameter("id"));
//			
//			//Check rights
//			if(user.getRole(id).equals(CourseRole.CR_NOTENROLLED)) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "You are not enrolled for this course");
//				return resp;
//			}
//			
//			Course course = new CourseDao().selectById(id);
//			
//			if(course==null) {
//				resp.setNextView("error");
//				resp.setPageDataAttribute("message", "no such course");
//				return resp;
//			}
//			
//			List<Task> tasks = new TaskDao().selectByCourseId(id);
//			List<TaskLog> tasks_status = null;
//			
//			if(user.getRole(id).equals(CourseRole.CR_TEACHER)) {
//				tasks_status = new TaskLogDao().selectAll();
//			} else {
//				tasks_status = new TaskLogDao().selectByUserId(user.getMoodleId());
//			}
//			
//			resp.setPageDataAttribute("course", course);
//			resp.setPageDataAttribute("tasks",tasks);
//			resp.setPageDataAttribute("tasks_status", tasks_status);
//			resp.setNextView("course");
//			return resp;
//		}
//		
//		List<Course> all_courses = new CourseDao().selectAll();
//		List<Course> courses = new LinkedList<Course>();
//		
//		for(Course c : all_courses) {
//			if(!user.getRole(c.getId()).equals(CourseRole.CR_NOTENROLLED)) {
//				courses.add(c);
//			}
//		}
		
//		resp.setPageDataAttribute("courses", courses);
		resp.setNextView("course-list");
		return resp;
	}

}
