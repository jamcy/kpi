package ua.kpi.eec.vml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {
	
	@RequestMapping("/course")
	public ModelAndView listCourses() {
		ModelAndView model = new ModelAndView("course-list");
		return model;
	}
	
	public void processRequest() {
		/*if(rd.getClientStateAttribute("user")==null) {
			resp.setNextView("error");
			resp.setPageDataAttribute("message", "Login  before viewing this page");
		}*/
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
	}

}
