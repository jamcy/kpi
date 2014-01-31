package com.epam.mykhailo_lisevych.wp.web.bean;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.ejb.UserStateBean;

@Named("testBean")
@RequestScoped
public class TestBean {

	@Inject
	private UserStateBean userState;

	public UserStateBean getUserState() {
		return userState;
	}

	public void setUserState(UserStateBean userState) {
		this.userState = userState;
	}

}
