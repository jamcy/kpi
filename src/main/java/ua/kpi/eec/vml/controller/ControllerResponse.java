package ua.kpi.eec.vml.controller;

import java.io.InputStream;
import java.util.HashMap;

public class ControllerResponse {

	private String nextView = "";
	private boolean redirect = false;
	private boolean raw = false;
	private String redirectAddress = "";
	private String rawContentType = "text";
	private boolean noresp = false;
	private InputStream content = null;

	public ControllerResponse() {
		this.pageData = new HashMap<String, Object>();
	}

	private HashMap<String, Object> pageData = null;

	public boolean isRaw() {
		return raw;
	}

	public void setRaw(boolean raw) {
		this.raw = raw;
	}

	public String getNextView() {
		return nextView;
	}

	public void setNextView(String nextView) {
		this.nextView = nextView;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getRedirectAddress() {
		return redirectAddress;
	}

	public void setRedirectAddress(String redirectAddress) {
		this.redirectAddress = redirectAddress;
	}

	public void setRedirectAddress(String controllerPath,
			HashMap<String, String> params) {
		String paramString = "";
		for (String key : params.keySet()) {
			paramString += "&" + key + "=" + params.get(key);
		}
		paramString = paramString.replaceFirst("&", "?");
		this.redirectAddress = controllerPath + paramString;
	}

	public HashMap<String, Object> getPageData() {
		return pageData;
	}

	public void setPageDataAttribute(String key, Object value) {
		this.pageData.put(key, value);
	}

	public String getRawContentType() {
		return rawContentType;
	}

	public void setRawContentType(String rawContentType) {
		this.rawContentType = rawContentType;
	}

	public boolean isNoresp() {
		return noresp;
	}

	public void setNoresp(boolean noresp) {
		this.noresp = noresp;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}
	
	
	
}
