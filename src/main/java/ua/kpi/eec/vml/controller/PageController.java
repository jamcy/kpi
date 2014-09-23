package ua.kpi.eec.vml.controller;

import java.util.List;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.dao.PageDao;
import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.Page;
import ua.kpi.eec.vml.model.entity.Room;

public class PageController implements Controller {

	@Override
	public ControllerResponse processRequest(RequestData rd) {
		String suffix = rd.getFunction();
		ControllerResponse resp = new ControllerResponse();
//		resp.setNextView("error");
//		if (suffix.equals("main")) {
//			RoomDao rdao = new RoomDao();
//			List<Room> rooms = rdao.selectAll();
//			resp.setPageDataAttribute("rooms", rooms);
//			resp.setNextView("index");
//		} else {
//			PageDao pdao = new PageDao();
//			Page content = pdao.selectBySuffix(suffix);
//			if (content == null) {
//				resp.setPageDataAttribute("message", "Page doesn't exists");
//				resp.setNextView("error");
//				return resp;
//			}
//			
//			resp.setPageDataAttribute("content", content);
//			resp.setNextView("page");
//		}
		return resp;
	}

}
