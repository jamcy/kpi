package ua.kpi.eec.vml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.entity.Module;

@Controller
public class ModuleController {
	
	@Autowired
	private ModuleDao moduleDao;
	
	@RequestMapping(value="/module", method=RequestMethod.GET)
	public ModelAndView modulePage(@RequestParam int id) {
		ModelAndView model = new ModelAndView("module");
		Module module = moduleDao.find(id);
		model.addObject("module", module);
		model.addObject("room", module.getRoom());
		return model;
	}
	
	//TODO: task
	//TODO: resource
	//TODO: app
	
	@RequestMapping(value="/module/app", method=RequestMethod.GET, produces="text/html; charset=utf-8")
	@ResponseBody
	public String moduleApplicationPage(@RequestParam int id) {
		Module module = moduleDao.find(id);
		return module.getEmbed();
//		User user = (User) rd.getClientStateAttribute("user");
//		String mode = rd.getParameter("mode");
//
//		if (user != null && mode != null && mode.equals("task")) {
//			Long taskid = null;
//			try {
//				taskid = Long.parseLong(rd.getParameter("taskid"));
//			} catch (Exception e) {
//
//			}
//			if (taskid != null) {
//				Task task = new TaskDao().selectById(taskid);
//				if (user.getRole(task.getCourseId()).equals(
//						CourseRole.CR_STUDENT)) {
//					data = new ModuleInitData();
//					TaskLog tlg = new TaskLogDao().select(task.getId(),
//							user.getMoodleId());
//					data.setTaskid(task.getId());
//					if (tlg == null) {
//						data.setProject(task.getTemplate());
//						System.out.println(task.getTemplate());
//						data.setReadonly(false);
//					} else {
//						data.setProject("/static/tasks/"+tlg.getFile());
//						if (tlg.getStatus().equals(TaskLog.TS_CHECKED)) {
//							data.setReadonly(true);
//						} else {
//							data.setReadonly(false);
//						}
//					}
//				}
//			}
//		}
//
//		if (user != null && mode != null && mode.equals("view")) {
//			Long tlgid = null;
//			try {
//				tlgid = Long.parseLong(rd.getParameter("tlgid"));
//			} catch (Exception e) {
//
//			}
//			if (tlgid != null) {
//				TaskLog tlg = new TaskLogDao().selectById(tlgid);
//				if (tlg != null) {
//					Task task = new TaskDao().selectById(tlg.getTask());
//					if (user.getRole(task.getCourseId()).equals(
//							CourseRole.CR_TEACHER)) {
//						data = new ModuleInitData();
//						data.setReadonly(true);
//						data.setTaskid(task.getId());
//						data.setProject("/static/tasks/"+tlg.getFile());
//					}
//				}
//			}
//		}
//
//		resp.setRaw(true);
//		String content = m.getEmbedCode();
//		if (data != null) {
//			String[] parts = content.split("<body>");
//			content = parts[0]
//					+ "<div style=\"display : none\" id=\"lab-data\">"
//					+ new Gson().toJson(data) + "</div>" + parts[1];
//		}
//		resp.setPageDataAttribute("content", content);
//		resp.setRawContentType("text/html");
	}

	
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}
}
