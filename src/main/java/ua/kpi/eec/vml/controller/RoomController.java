package ua.kpi.eec.vml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.model.entity.Room;

@Controller
public class RoomController {

	@Autowired
	private ModuleDao moduleDao;

	@Autowired
	private RoomDao roomDao;

	@RequestMapping("/room")
	public ModelAndView viewRoomPage(@RequestParam("id") Long roomId) {
		ModelAndView model = new ModelAndView("room");
		Room room = roomDao.find(roomId);
		model.addObject("room", room);
		return model;
	}

	public List<Module> restModules() {
		// TODO: implement modules via rest?
		// Main page navigation module

		// if (rd.getParameter("raw") != null
		// && rd.getParameter("raw").equals("true")) {
		// ModuleJson[] mja = new ModuleJson[modules.size()];
		// for (int i = 0; i < modules.size(); i++) {
		// ModuleJson mj = new ModuleJson();
		// mj.setId(modules.get(i).getId());
		// mj.setName(modules.get(i).getName().getByLanguage(lang));
		// mj.setImageUrl(modules.get(i).getPicture());
		// mj.setDescription(modules.get(i).getDescription()
		// .getByLanguage(lang));
		// mja[i] = mj;
		// }
		// resp.setPageDataAttribute("content", new Gson().toJson(mja));
		// resp.setRaw(true);
		// return resp;
		// }
		return null;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
}
