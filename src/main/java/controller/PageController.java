package controller;

import java.util.List;

import model.dao.PageDao;
import model.dao.RoomDao;
import model.entity.Page;
import model.entity.Room;
import common.RequestData;

public class PageController implements Controller {

	@Override
	public ControllerResponse processRequest(RequestData rd) {
		String suffix = rd.getFunction();
		ControllerResponse resp = new ControllerResponse();
		resp.setNextView("error");
		if (suffix.equals("main")) {
			RoomDao rdao = new RoomDao();
			List<Room> rooms = rdao.selectAll();
			resp.setPageDataAttribute("rooms", rooms);
			resp.setNextView("index");
		} else {
			PageDao pdao = new PageDao();
			Page content = pdao.selectBySuffix(suffix);
			if (content == null) {
				resp.setPageDataAttribute("message", "Page doesn't exists");
				resp.setNextView("error");
				return resp;
			}
			
			resp.setPageDataAttribute("content", content);
			resp.setNextView("page");
		}
		return resp;
	}

}
