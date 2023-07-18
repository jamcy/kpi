package ua.kpi.eec.vml.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class ResourceController {
	
	@RequestMapping(value="/module/resource")
	@ResponseBody
	public void resource(@RequestParam String url) {
//		try {
//			resp.setContent(new URL(rd.getParameter("url")).openConnection()
//					.getInputStream());
//		} catch (Exception e) {
//			resp.setPageDataAttribute("content", "Error:bad url");
//		}
	}
}
