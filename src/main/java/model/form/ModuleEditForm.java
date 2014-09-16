package model.form;

import model.entity.Module;
import model.entity.Translation;
import common.RequestData;

public class ModuleEditForm implements FormData {

	public ModuleEditForm() {
		this.description = new Translation();
		this.name = new Translation();
		this.content = new Translation();
	}

	private String shortName = "";
	private Translation name = null;
	private Translation description = null;
	private Translation content = null;
	private long roomId;
	private String embedCode = "";
	private String imageUrl = "";

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Translation getContent() {
		return content;
	}

	public void setContent(Translation content) {
		this.content = content;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Translation getName() {
		return name;
	}

	public void setName(Translation name) {
		this.name = name;
	}

	public Translation getDescription() {
		return description;
	}

	public void setDescription(Translation description) {
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

		this.name = new Translation();
		name.setByLanguage("en", rd.getParameter("name_en"));
		name.setByLanguage("uk", rd.getParameter("name_uk"));

		this.shortName = rd.getParameter("short-name");

		this.description = new Translation();
		description.setByLanguage("en", rd.getParameter("description_en"));
		description.setByLanguage("uk", rd.getParameter("description_uk"));

		this.content = new Translation();
		content.setByLanguage("en", rd.getParameter("content_en"));
		content.setByLanguage("uk", rd.getParameter("content_uk"));

		this.embedCode = rd.getParameter("embed");
		
		this.imageUrl = rd.getParameter("image");

		try {
			this.roomId = Long.parseLong(rd.getParameter("room-id"));
		} catch (NumberFormatException e) {
			this.roomId = 0;
		}
	}

	public void update(Module module) {
		module.setContent(content);
		module.setDescription(description);
		module.setEmbedCode(embedCode);
		module.setName(name);
		module.setPicture(imageUrl);
		module.setRoomId(roomId);
		module.setShortName(shortName);
	}

	public void load(Module module) {
		this.setContent(module.getContent());
		this.setDescription(module.getDescription());
		this.setName(module.getName());
		this.setRoomId(module.getRoomId());
		this.setShortName(module.getShortName());
		this.setImageUrl(module.getPicture());
		this.setEmbedCode(module.getEmbedCode());
	}

}
