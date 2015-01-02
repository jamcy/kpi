package ua.kpi.eec.vml.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.model.form.ModuleForm;

@Controller
@RequestMapping(value="/admin/module")
public class ModuleAdminController extends DefaultAdminController {
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private RoomDao roomDao;
	
	@RequestMapping(value = "/list")
	public String showModuleList(Model model) {
		model.addAttribute("modules", moduleDao.findAll());
		model.addAttribute("view", "module_list");
		return "admin";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showModuleAdd() {
		return "admin";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doModuleAdd() {
		return "admin";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showModuleEdit(@PathVariable int id, Model model) {
		Module module = moduleDao.find(id);
		ModuleForm moduleForm = getConversionService().convert(module, ModuleForm.class);
		model.addAttribute("module", moduleForm);
		model.addAttribute("rooms", roomDao.findAll());
		model.addAttribute("view", "module_edit");
		return "admin";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String doModuleEdit(@Validated ModuleForm module, Model model) {
		model.addAttribute("view", "module_edit");
		return "admin";
	}
	
}
