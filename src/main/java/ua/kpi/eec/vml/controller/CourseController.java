package ua.kpi.eec.vml.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.kpi.eec.vml.model.dao.AccountDao;
import ua.kpi.eec.vml.model.dao.CourseDao;
import ua.kpi.eec.vml.model.dto.AccountData;
import ua.kpi.eec.vml.model.entity.Account;
import ua.kpi.eec.vml.model.entity.Course;
import ua.kpi.eec.vml.model.entity.SystemRole;

@Controller
public class CourseController {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private AccountDao accountDao;

	@RequestMapping("/course")
	public ModelAndView listCourses(@ModelAttribute Account account) {
		ModelAndView model = new ModelAndView("course-list");
		List<Course> courses = new ArrayList<Course>();
		if(SystemRole.TEACHER.equals(account)) {
			courses = courseDao.findAll();
		} else {
			courses = account.getCourses();
		}
		model.addObject("courses", courses);
		
		//TODO tasks
		/*if (user.getRole(course.getId()).equals(CourseRole.CR_STUDENT)) {
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
	}*/
	
		return model;
	}

	@RequestMapping(value = "/course", params = "id")
	public ModelAndView coursePage(@RequestParam int id, @ModelAttribute Account account) {
		// TODO Add course rights to view page
		ModelAndView model = new ModelAndView("course");
		Course course = courseDao.find(id);

		//List<Task> tasks = new TaskDao().selectByCourseId(id);
		//List<TaskLog> tasks_status = null;

//		if (user.getRole(id).equals(CourseRole.CR_TEACHER)) {
//			tasks_status = new TaskLogDao().selectAll();
//		} else {
//			tasks_status = new TaskLogDao().selectByUserId(user.getMoodleId());
//		}
		model.addObject("course", course);
		//model.addObject("tasks", tasks);
		//model.addObject("tasks_status", tasks_status);
		return model;
	}

	@ModelAttribute(value = "account")
	public Account initCurrentAccount() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof AccountData)
			return accountDao.findByUsername(((AccountData)principal).getUsername());
		return Account.getGuestAccount();
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
}
