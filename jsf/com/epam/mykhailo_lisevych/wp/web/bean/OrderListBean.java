package com.epam.mykhailo_lisevych.wp.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.ejb.UserStateBean;
import com.epam.mykhailo_lisevych.wp.entity.Order;

@SuppressWarnings("cdi-ambiguous-dependency")
@ManagedBean
@ViewScoped
@RolesAllowed({ "COMPANY" })
public class OrderListBean {

	private List<Order> orders;

	@Inject
	private OrderDao odao;

	@Inject
	private UserStateBean userState;

	@PostConstruct
	public void init() {
		orders = odao.selectByCompany(userState.getCurrentUser().getCompany());
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
