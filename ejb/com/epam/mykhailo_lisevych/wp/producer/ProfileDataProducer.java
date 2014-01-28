package com.epam.mykhailo_lisevych.wp.producer;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.dao.UserDao;
import com.epam.mykhailo_lisevych.wp.entity.Company;
import com.epam.mykhailo_lisevych.wp.entity.User;
import com.epam.mykhailo_lisevych.wp.entity.UserRole;

@Stateless
public class ProfileDataProducer {

	private static User guestUser;

	@Resource
	private SessionContext context;

	@Inject
	private UserDao udao;

	static {
		guestUser = new User();
		guestUser.setEmail("anonymous");
		guestUser.setRole(UserRole.GUEST);
	}

	@Produces
	public User getCurrent() {
		String email = context.getCallerPrincipal().getName();
		if (email.equals("anonymous")) {
			return guestUser;
		}
		return udao.selectByEmail(email);
	}

}
