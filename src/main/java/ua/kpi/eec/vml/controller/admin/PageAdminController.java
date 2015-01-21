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

import ua.kpi.eec.vml.model.dao.PageDao;
import ua.kpi.eec.vml.model.entity.Page;
import ua.kpi.eec.vml.model.form.PageForm;

@Controller
@RequestMapping(value="/admin/page")
public class PageAdminController extends DefaultAdminController {
	@Autowired
	private PageDao pageDao;
	
	@RequestMapping(value = "/list")
	public String showPageList(Model model) {
		model.addAttribute("pages", pageDao.findAll());
		model.addAttribute("view", "page_list");
		return "admin";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showPageAdd(Model model) {
		PageForm page = new PageForm();
		model.addAttribute("page", page);
		model.addAttribute("view", "page_edit");
		model.addAttribute("action", "add");
		return "admin";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doPageAdd(@Valid @ModelAttribute("page") PageForm page, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			model.addAttribute("view", "page_edit");
			model.addAttribute("action", "add");
			return "admin";
		}
		pageDao.create(getConversionService().convert(page, Page.class));
		return "redirect:/admin/page/list";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showPageUpdate(@PathVariable int id, Model model) {
		Page page = pageDao.find(id);
		PageForm pageForm = getConversionService().convert(page, PageForm.class);
		model.addAttribute("page", pageForm);
		model.addAttribute("view", "page_edit");
		model.addAttribute("action", "edit");
		return "admin";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String doPageUpdate(@Valid @ModelAttribute("page") PageForm page, BindingResult result, Model model) throws Exception {
		if(result.hasErrors()) {
			model.addAttribute("view", "page_edit");
			model.addAttribute("action", "edit");
			return "admin";
		}
		pageDao.update(getConversionService().convert(page, Page.class));
		return "redirect:/admin/page/list";
	}
}
