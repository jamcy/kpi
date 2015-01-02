package ua.kpi.eec.vml.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DefaultAdminController {
	
	@Autowired
	private ConversionService conversionService;
	
	@RequestMapping("")
	public String indexPage() {
		return "admin";
	}

	public ConversionService getConversionService() {
		return conversionService;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	
	
}
