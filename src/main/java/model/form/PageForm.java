package model.form;

import model.entity.Page;
import model.entity.Translation;
import common.RequestData;

public class PageForm implements FormData {

	private Translation name;
	private Translation content;
	private String suffix="";

	public PageForm() {
		this.name = new Translation();
		this.content = new Translation();
	}

	@Override
	public boolean validate() {
		boolean result = true;
		if(this.content==null || this.content.equals("")) {
			//TODO message
			result = false;
		}
		if(this.name.toString("en").equals("")) {
			//TODO message
			result = false;
		}
		return result;
	}

	@Override
	public void init(RequestData rd) {
		this.suffix = rd.getParameter("url-suffix");
		this.name = new Translation();
		name.setByLanguage("en", rd.getParameter("name_en").trim());
		name.setByLanguage("uk", rd.getParameter("name_uk").trim());
		this.content = new Translation();
		content.setByLanguage("en", rd.getParameter("content_en").trim());
		content.setByLanguage("uk", rd.getParameter("content_uk").trim());
	}

	public void update(Page page) {
		page.setContent(content);
		page.setName(name);
		page.setUrlSuffix(suffix);
	}

	public void load(Page page) {
		this.content = page.getContent();
		this.name = page.getName();
		this.suffix = page.getUrlSuffix();
	}

	public Translation getName() {
		return name;
	}

	public void setName(Translation name) {
		this.name = name;
	}

	public Translation getContent() {
		return content;
	}

	public void setContent(Translation content) {
		this.content = content;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
