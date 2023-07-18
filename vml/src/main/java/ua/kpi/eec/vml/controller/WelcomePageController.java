package ua.kpi.eec.vml.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.kpi.eec.vml.model.dao.RoomDao;

@Controller
public class WelcomePageController {

	@Resource
	private RoomDao roomDao;

	@RequestMapping(value = "/welcome")
	public String welcome(Model model) {
		try {
			model.addAttribute("rooms", roomDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "welcome";
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

}
