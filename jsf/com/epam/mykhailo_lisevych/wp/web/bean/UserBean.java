package com.epam.mykhailo_lisevych.wp.web.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.dao.UserDao;
import com.epam.mykhailo_lisevych.wp.ejb.ContextBean;
import com.epam.mykhailo_lisevych.wp.entity.User;
import com.epam.mykhailo_lisevych.wp.entity.UserRole;

@Named("userBean")
@SessionScoped
public class UserBean {

	private static class AnonymousUser extends User {
		private static final long serialVersionUID = 1L;

		public AnonymousUser() {
			super.setEmail("anonymous");
			super.setRole(UserRole.GUEST);
		}
		
		@Override
		public void setEmail(String email) {
		}

		@Override
		public void setRole(UserRole role) {
		}
	};

	private static AnonymousUser anonymousUser;

	static {
		anonymousUser = new AnonymousUser();
	}

	@Inject
	private ContextBean context;

	@Inject
	private UserDao udao;

	private User currentUser;

	public String getUsername() {
		return "Username";
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@PostConstruct
	public void init() {
		if (context.getSessionContext().getCallerPrincipal().getName().equals("anonymous")) {
			currentUser = anonymousUser;
		} else {
			currentUser = udao.selectByEmail(context.getSessionContext()
					.getCallerPrincipal().getName());
		}
	}
}
