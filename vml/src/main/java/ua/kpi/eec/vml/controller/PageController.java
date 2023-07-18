package ua.kpi.eec.vml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.kpi.eec.vml.model.dao.PageDao;
import ua.kpi.eec.vml.model.entity.Page;

@Controller
public class PageController {

	@Autowired
	private PageDao pageDao;

	@RequestMapping(value = "/page/{urlSuffix}", method = RequestMethod.GET)
	public ModelAndView showPage(@PathVariable String urlSuffix) {
		ModelAndView model = new ModelAndView("page");
		Page page = pageDao.findByUrlSuffix(urlSuffix);
		model.addObject("page", page);
		return model;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

}
