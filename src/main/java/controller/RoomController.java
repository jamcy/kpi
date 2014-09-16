package controller;

import java.util.List;

import com.google.gson.Gson;

import model.dao.ModuleDao;
import model.dao.RoomDao;
import model.entity.Module;
import model.entity.Room;
import model.json.ModuleJson;
import common.RequestData;

public class RoomController implements Controller {

	@Override
	public ControllerResponse processRequest(RequestData rd) {

		ControllerResponse resp = new ControllerResponse();
		String roomId = rd.getParameter("id");

		if (roomId == null) {
			// Handle empty room id
		} else {
			long id = 0;
			try {
				id = Long.parseLong(roomId);
			} catch (NumberFormatException e) {
				resp.setNextView("error");
				resp.setPageDataAttribute("message", "No such page");
				return resp;
			}

			RoomDao roomd = new RoomDao();
			Room room = roomd.selectById(id);
			if (room == null) {
				resp.setNextView("error");
				resp.setPageDataAttribute("message",
						"There is no such room");
			}
			ModuleDao modd = new ModuleDao();
			List<Module> modules = modd.selectByRoomId(room.getId());
			if(rd.getParameter("raw")!=null&&rd.getParameter("raw").equals("true")) {
				String lang = (String)rd.getClientStateAttribute("lang");
				if(lang==null) {
					lang="en";
				}
				ModuleJson[] mja = new ModuleJson[modules.size()];
				for(int i=0; i<modules.size(); i++) {
					ModuleJson mj = new ModuleJson();
					mj.setId(modules.get(i).getId());
					mj.setName(modules.get(i).getName().getByLanguage(lang));
					mj.setImageUrl(modules.get(i).getPicture());
					mj.setDescription(modules.get(i).getDescription().getByLanguage(lang));
					mja[i]=mj;
				}
				resp.setPageDataAttribute("content", new Gson().toJson(mja));
				resp.setRaw(true);
				return resp;
			}
			resp.setPageDataAttribute("modules", modules);
			resp.setPageDataAttribute("room", room);
			resp.setNextView("room");
		}
		return resp;
	}

    //@Override
    //public String getMapping() {
    //    return null;
    //}
}
