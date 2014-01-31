package com.epam.mykhailo_lisevych.wp.ejb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.dao.UserDao;
import com.epam.mykhailo_lisevych.wp.entity.Product;
import com.epam.mykhailo_lisevych.wp.entity.User;
import com.epam.mykhailo_lisevych.wp.entity.UserRole;

@Stateful
@SessionScoped
public class UserStateBean implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@Resource
	private SessionContext context;

	@Inject
	private UserDao udao;

	private User currentUser;
	private Map<Product, Integer> orderLines;

	static {
		anonymousUser = new AnonymousUser();
	}

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
		orderLines = new HashMap<Product, Integer>();
		if (context.getCallerPrincipal().getName().equals("anonymous")) {
			currentUser = anonymousUser;
		} else {
			currentUser = udao.selectByEmail(context.getCallerPrincipal()
					.getName());
		}
	}

	public Map<Product, Integer> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Map<Product, Integer> orderLines) {
		this.orderLines = orderLines;
	}

	public void refresh() {
		init();
	}

}
