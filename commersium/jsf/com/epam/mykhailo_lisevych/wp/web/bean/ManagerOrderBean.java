package com.epam.mykhailo_lisevych.wp.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatusValue;

@ManagedBean
@ViewScoped
public class ManagerOrderBean {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private OrderDao odao;

	private OrderStatusValue orderStatusValueFilter;

	private List<Order> orders;

	@PostConstruct
	private void init() {
		orders = odao.selectAll();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public OrderStatusValue getOrderStatusValueFilter() {
		return orderStatusValueFilter;
	}

	public void setOrderStatusValueFilter(
			OrderStatusValue orderStatusValueFilter) {
		this.orderStatusValueFilter = orderStatusValueFilter;
	}

	public OrderStatusValue[] getOrderStatusValues() {
		return OrderStatusValue.values();
	}

}
