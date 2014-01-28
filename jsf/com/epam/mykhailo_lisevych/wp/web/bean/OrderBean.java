package com.epam.mykhailo_lisevych.wp.web.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatusValue;
import com.epam.mykhailo_lisevych.wp.web.converter.OrderFromId;

@Named("orderBean")
@RequestScoped
public class OrderBean {

	@Inject
	private OrderFromId converter;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private OrderDao odao;
	
	private String dealContents;
	private String changeComment;
	private OrderStatusValue statusValueLabel;
	
	private Order order;

	public String[] getAvailableStatusLabels() {
		String[] result = new String[OrderStatusValue.values().length];
		for(int i=0; i< OrderStatusValue.values().length; i++) {
			result[i] = OrderStatusValue.values()[i].name();
		}
		return result;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderFromId getConverter() {
		return converter;
	}

	public void setConverter(OrderFromId converter) {
		this.converter = converter;
	}

	public String getDealContents() {
		return dealContents;
	}

	public void setDealContents(String dealContents) {
		this.dealContents = dealContents;
	}

	public OrderStatusValue getStatusValueLabel() {
		return statusValueLabel;
	}

	public void setStatusValueLabel(OrderStatusValue statusValueLabel) {
		this.statusValueLabel = statusValueLabel;
	}
	

}
