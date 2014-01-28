package com.epam.mykhailo_lisevych.wp.web.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@ViewScoped
public class AuthBean {

	private String username;
	private String password;
	private String returnUrl;

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		returnUrl = (String) externalContext.getRequestMap().get(
				RequestDispatcher.FORWARD_REQUEST_URI);
		if (returnUrl == null) {
			returnUrl = externalContext.getRequestContextPath()
					+ "/index.xhtml";
		} else {
			String originalQuery = (String) externalContext.getRequestMap()
					.get(RequestDispatcher.FORWARD_QUERY_STRING);
			if (originalQuery != null) {
				returnUrl += "?" + originalQuery;
			}
		}
	}

	public void login() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();
		try {
			request.login(username, password);
			// TODO replace injected users
			externalContext.redirect(returnUrl);
		} catch (ServletException e) {
			e.printStackTrace();
			context.addMessage(null, new FacesMessage("Failed to login"));
		}
	}

	public void logout() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.invalidateSession();
		externalContext.redirect(returnUrl);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
