package ua.kpi.eec.vml.model.form;

import ua.kpi.eec.vml.common.RequestData;
import ua.kpi.eec.vml.model.entity.I18n;
import ua.kpi.eec.vml.model.entity.Page;

public class PageForm implements FormData {

	private I18n name;
	private I18n content;
	private String suffix="";

	public PageForm() {
		this.name = new I18n();
		this.content = new I18n();
	}

	@Override
	public boolean validate() {
		boolean result = true;
		if(this.content==null || this.content.equals("")) {
			//TODO message
			result = false;
		}
//		if(this.name.toString("en").equals("")) {
//			//TODO message
//			result = false;
//		}
		return result;
	}

	@Override
	public void init(RequestData rd) {
		this.suffix = rd.getParameter("url-suffix");
		this.name = new I18n();
//		name.setByLanguage("en", rd.getParameter("name_en").trim());
//		name.setByLanguage("uk", rd.getParameter("name_uk").trim());
		this.content = new I18n();
//		content.setByLanguage("en", rd.getParameter("content_en").trim());
//		content.setByLanguage("uk", rd.getParameter("content_uk").trim());
	}

	public void update(Page page) {
//		page.setContent(content);
//		page.setName(name);
		page.setUrlSuffix(suffix);
	}

	public void load(Page page) {
//		this.content = page.getContent();
//		this.name = page.getName();
		this.suffix = page.getUrlSuffix();
	}

	public I18n getName() {
		return name;
	}

	public void setName(I18n name) {
		this.name = name;
	}

	public I18n getContent() {
		return content;
	}

	public void setContent(I18n content) {
		this.content = content;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
