package com.epam.mykhailo_lisevych.wp.dao;


import com.epam.mykhailo_lisevych.wp.entity.Order;

public class OrderDao extends AbstractJpaDao<Order> {

	private static final long serialVersionUID = 1L;

	@Override
	public Order read(Object id) {
		Order result=super.read(id);
		return result;
	}
	
	@Override
	public Class<?> getEntityClass() {
		return Order.class;
	}

}
