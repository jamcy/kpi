package ua.kpi.eec.vml.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DefaultAdminController {
	
	@RequestMapping("")
	public String indexPage() {
		return "admin";
	}
}
