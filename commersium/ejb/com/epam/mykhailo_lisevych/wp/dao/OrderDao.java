package com.epam.mykhailo_lisevych.wp.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.epam.mykhailo_lisevych.wp.entity.Company;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatus;
import com.epam.mykhailo_lisevych.wp.entity.OrderStatusValue;

@Stateless
public class OrderDao extends AbstractJpaDao<Order> {

	private static final long serialVersionUID = 1L;

	@Override
	public Order read(Object id) {
		Order result = super.read(id);
		return result;
	}

	public void merge(Order o) {
		getEntityManager().merge(o);
	}

	@Override
	public void create(Order e) {
		e.setTimeCreated(new Date());
		OrderStatus statusSubmitted = new OrderStatus();
		statusSubmitted.setStatus(OrderStatusValue.SUBMITTED);
		statusSubmitted.setTimeFrom(e.getTimeCreated());
		e.addStatus(statusSubmitted);
		super.create(e);
	}

	@Override
	public Class<?> getEntityClass() {
		return Order.class;
	}

	public List<Order> selectAll() {
		TypedQuery<Order> query = getEntityManager().createQuery(
				"SELECT DISTINCT o FROM Order o", Order.class);
		return query.getResultList();
	}

	public List<Order> selectByCompany(Company c) {
		TypedQuery<Order> query = getEntityManager().createNamedQuery(
				"Order.selectByCompany", Order.class);
		query.setParameter("company", c);
		return query.getResultList();
	}

}
