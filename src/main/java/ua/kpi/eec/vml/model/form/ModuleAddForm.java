package ua.kpi.eec.vml.model.form;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.dao.FileDao;
import ua.kpi.eec.vml.model.entity.Module;

public class ModuleAddForm implements FormData {

	private long roomId;
	private String shortName="";

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public boolean validate() {
		boolean result = true;
		if(this.shortName==null || !this.shortName.matches("([a-zA-Z]|[_]|[0-9])+")) {
			//TODO proper message
			result = false;
		}
		if(this.roomId<=0) {
			result = false;
		}
		if(new FileDao().getDirectories("/modules").contains(this.shortName)) {
			result = false;
		}
		return result;
	}

	@Override
	public void init(RequestData rd) {
		this.shortName = rd.getParameter("short-name").trim();
		try {
			this.roomId = Long.parseLong(rd.getParameter("room-id"));
		} catch (NumberFormatException e) {
			this.roomId = 0;
		}
	}
	
	public void update(Module module) {
//		module.setShortName(shortName);
//		module.setRoomId(roomId);
	}

}
