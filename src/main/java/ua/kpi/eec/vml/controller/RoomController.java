package ua.kpi.eec.vml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.Room;

@Controller
public class RoomController {

	@Autowired
	private ModuleDao moduleDao;

	@Autowired
	private RoomDao roomDao;

	@RequestMapping("/room")
	public String viewRoom(@RequestParam("id") Long roomId, Model model) {
		Room room = roomDao.read(roomId);
		model.addAttribute("room", room);
		if (room == null) {
			model.addAttribute("message", "There is no such room");
		}
		// List<Module> modules = modd.selectByRoomId(room.getId());
		// if (rd.getParameter("raw") != null
		// && rd.getParameter("raw").equals("true")) {
		// String lang = (String) rd.getClientStateAttribute("lang");
		// if (lang == null) {
		// lang = "en";
		// }
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
		// resp.setPageDataAttribute("modules", modules);
		return "room";
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}
}
