package model.form;

import java.util.HashMap;

import common.RequestData;

public class LoginForm implements FormData, HttpFormData {
	
	public static final String FM_LOGIN_FAILED = "form.login.message.fail";
	public static final String FM_CONNECT_FAILED = "form.login.message.connect";
	
	private HashMap<String, String> messages = new HashMap<String, String>();
	private String formMessage=null;
	
	private String username="";
	private String password="";
	private String returnUrl=null;
	
	public LoginForm() {
		this.username="";
		this.password = "";
	}
	
	public String getUsername() {
		if(username!=null)
			return username;
		return "";
	}

	public void setUsername(String username) {
		if(username==null) {
			this.username=null;
			return;
		}
		this.username = username.trim();
	}

	public String getPassword() {
		if(password!=null)
			return password;
		return "";
	}

	public void setPassword(String password) {
		if(password==null) {
			this.password=null;
			return;
		}
		this.password = password.trim();
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	@Override
	public boolean validate() {
		if(username==null||password==null) {
			return false;
		}
		
		if(username.equals("") || password.equals("")) {
			return false;
		}
		return true;
	}
	
	@Override
	public void init(RequestData rd) {
		this.setUsername(rd.getParameter("username"));
		this.setPassword(rd.getParameter("password"));
		this.setReturnUrl(rd.getParameter("returnurl"));
	}

	@Override
	public String getMessage() {
		return this.formMessage;
	}

	public void setMessage(String message) {
		this.formMessage = message;
	}
	
	@Override
	public HttpFormElement getElement(String name) {
		Object data = null;
		if(name.equals("username")) {
			data = this.username;
		}
		if(name.equals("password")) {
			data = this.password;
		}
		if(name.equals("returnurl")) {
			data = this.returnUrl;
		}
		HttpFormElement result = new HttpFormElement();
		result.setValue(data);
		result.setMessage(messages.get(name));
		return null;
	}
}
