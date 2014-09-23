package ua.kpi.eec.vml.model.form;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.entity.I18n;
import ua.kpi.eec.vml.model.entity.Module;

public class ModuleEditForm implements FormData {

	public ModuleEditForm() {
		this.description = new I18n();
		this.name = new I18n();
		this.content = new I18n();
	}

	private String shortName = "";
	private I18n name = null;
	private I18n description = null;
	private I18n content = null;
	private long roomId;
	private String embedCode = "";
	private String imageUrl = "";

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public I18n getContent() {
		return content;
	}

	public void setContent(I18n content) {
		this.content = content;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public I18n getName() {
		return name;
	}

	public void setName(I18n name) {
		this.name = name;
	}

	public I18n getDescription() {
		return description;
	}

	public void setDescription(I18n description) {
		this.description = description;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	@Override
	public boolean validate() {
		// TODO Validate form
		return true;
	}

	@Override
	public void init(RequestData rd) {

		this.name = new I18n();
//		name.setByLanguage("en", rd.getParameter("name_en"));
//		name.setByLanguage("uk", rd.getParameter("name_uk"));

		this.shortName = rd.getParameter("short-name");

		this.description = new I18n();
//		description.setByLanguage("en", rd.getParameter("description_en"));
//		description.setByLanguage("uk", rd.getParameter("description_uk"));

		this.content = new I18n();
//		content.setByLanguage("en", rd.getParameter("content_en"));
//		content.setByLanguage("uk", rd.getParameter("content_uk"));

		this.embedCode = rd.getParameter("embed");
		
		this.imageUrl = rd.getParameter("image");

		try {
			this.roomId = Long.parseLong(rd.getParameter("room-id"));
		} catch (NumberFormatException e) {
			this.roomId = 0;
		}
	}

	public void update(Module module) {
//		module.setContent(content);
//		module.setDescription(description);
//		module.setEmbedCode(embedCode);
//		module.setName(name);
//		module.setPicture(imageUrl);
//		module.setRoomId(roomId);
//		module.setShortName(shortName);
	}

	public void load(Module module) {
//		this.setContent(module.getContent());
//		this.setDescription(module.getDescription());
//		this.setName(module.getName());
//		this.setRoomId(module.getRoomId());
//		this.setShortName(module.getShortName());
//		this.setImageUrl(module.getPicture());
//		this.setEmbedCode(module.getEmbedCode());
	}

}
