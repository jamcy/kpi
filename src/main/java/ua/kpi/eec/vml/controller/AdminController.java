package ua.kpi.eec.vml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.kpi.eec.vml.model.dao.AccountDao;
import ua.kpi.eec.vml.model.dao.CourseDao;
import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.dao.PageDao;
import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.dao.TaskDao;
import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.model.form.ModuleForm;
import ua.kpi.eec.vml.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ConversionService conversionService;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private PageDao pageDao;
	@Autowired
	private TaskDao taskDao;
	
	@RequestMapping("")
	public String index() {
		return "admin";
	}

	@RequestMapping(value = "/modules")
	public String listModules(Model model) {
		model.addAttribute("modules", moduleDao.findAll());
		model.addAttribute("view", "module_list");
		return "admin";
	}
	
	@RequestMapping(value = "/courses")
	public String listCourses(Model model) {
		model.addAttribute("courses", courseDao.findAll());
		model.addAttribute("view", "course_list");
		return "admin";
	}

	@RequestMapping(value = "/pages")
	public String listPages(Model model) {
		model.addAttribute("pages", pageDao.findAll());
		model.addAttribute("view", "page_list");
		return "admin";
	}
	
	@RequestMapping(value = "/users")
	public String listUsers(Model model) {
		model.addAttribute("users", accountDao.findAll());
		model.addAttribute("view", "user_list");
		return "admin";
	}
	
	@RequestMapping(value="/tasks/{courseId}")
	public String listTasks(@PathVariable int courseId, Model model) {
		model.addAttribute("tasks", taskDao.findByCourseId(courseId));
		model.addAttribute("view", "task_list");
		return "admin";
	}
	
	@RequestMapping(value="/module/add", method=RequestMethod.GET)
	public String addModule() {
		return "admin";
	}
	
	@RequestMapping(value = "/module/edit/{id}", method = RequestMethod.GET)
	public String showEditModule(@PathVariable int id, Model model) {
		Module module = moduleDao.find(id);
		ModuleForm moduleForm = conversionService.convert(module, ModuleForm.class);
		model.addAttribute("module", moduleForm);
		model.addAttribute("rooms", roomDao.findAll());
		model.addAttribute("view", "module_edit");
		return "admin";
	}
	
	@RequestMapping(value = "/module/edit/{id}", method = RequestMethod.POST)
	public String editModule(Model model) {
		return "";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String updateUser(@PathVariable int id, Model model) {
		// admin service select by id
		return "/user/edit";
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
