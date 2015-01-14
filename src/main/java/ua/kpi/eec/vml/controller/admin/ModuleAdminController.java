package ua.kpi.eec.vml.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.model.form.ModuleForm;
import ua.kpi.eec.vml.service.ModuleService;

@Controller
@RequestMapping(value="/admin/module")
public class ModuleAdminController extends DefaultAdminController {
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(value = "/list")
	public String showModuleList(Model model) {
		model.addAttribute("modules", moduleDao.findAll());
		model.addAttribute("view", "module_list");
		return "admin";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showModuleAdd(Model model) {
		ModuleForm moduleForm = new ModuleForm();
		model.addAttribute("module", moduleForm);
		model.addAttribute("rooms", roomDao.findAll());
		model.addAttribute("view", "module_edit");
		return "admin";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doModuleAdd(@Valid ModuleForm module, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			model.addAttribute("module", module);
			model.addAttribute("view", "module_edit");
			model.addAttribute("rooms", roomDao.findAll());
			return "admin";
		}
		moduleService.add(getConversionService().convert(module, Module.class));
		return "redirect:/admin/module/list";
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
	public String doModuleEdit(ModuleForm module, Model model) {
		model.addAttribute("view", "module_edit");
		return "admin";
	}
	
}
