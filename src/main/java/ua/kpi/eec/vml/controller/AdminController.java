package ua.kpi.eec.vml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.kpi.eec.vml.model.form.ModuleForm;
import ua.kpi.eec.vml.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/modules")
	public String listModules(Model model) {
		model.addAttribute("modules");
		return "";
	}

	@RequestMapping(value = "/module/add", method = RequestMethod.GET)
	public String module(Model model) {
		return "";
	}

	@RequestMapping(value = "/module/add", method = RequestMethod.GET)
	public String addModule(@ModelAttribute ModuleForm moduleData, Model model) {
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
