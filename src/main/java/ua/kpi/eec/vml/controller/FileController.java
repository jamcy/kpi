package ua.kpi.eec.vml.controller;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.dao.FileDao;
import ua.kpi.eec.vml.model.json.FileManagerContents;

import com.google.gson.Gson;

public class FileController implements Controller {

	@Override
	public ControllerResponse processRequest(RequestData rd) {
		String action = rd.getParameter("action");
		String root = rd.getParameter("root");
		
		String path ="/resource";
		FileDao fdao = new FileDao();
		FileManagerContents data = new FileManagerContents();
		data.setFiles(fdao.listFiles("/resource"));
		ControllerResponse resp = new ControllerResponse();
		resp.setRaw(true);
		resp.setPageDataAttribute("content", new Gson().toJson(data));
		return resp;
	}
	
	public String list(RequestData rd) {
		String path = rd.getParameter("path");
		FileDao fdao = new FileDao();
		
		
		
		FileManagerContents data = new FileManagerContents();
		return new Gson().toJson(data);
	}
	
	public String upload(RequestData rd) {
		return "";
	}
	
	public String delete(RequestData rd) {
		return "";
	}
	
	public String dir(RequestData rd) {
		return "";
	}

}
