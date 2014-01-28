package com.epam.mykhailo_lisevych.wp.web.bean;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.entity.User;

import java.io.Serializable;

@Named("sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
