package com.epam.mykhailo_lisevych.wp.ejb;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.dao.CompanyDao;
import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderedProduct;
import com.epam.mykhailo_lisevych.wp.entity.Product;
import com.epam.mykhailo_lisevych.wp.entity.User;

@Stateful
public class OrderBuilder {

	@Inject
	private User currentUser;

	@Inject
	private CompanyDao cdao;

	@Inject
	private OrderDao odao;

	@Inject
	private OrderPriceProcessor priceProcessor;

	private Order order;

	@PostConstruct
	public void init() {
		order = new Order();
	}

	public void addProduct(Product p, int quantity) {
		if (containsProduct(p)) {
			return;
		}
		OrderedProduct op = new OrderedProduct();
		op.setAmount(quantity);
		op.setProduct(p);
		order.getOrderedProducts().add(op);
	}

	public void removeProduct(Product p) {
		// TODO implement
	}

	public void createOrder() {
		odao.create(order);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean containsProduct(Product p) {
		for (OrderedProduct op : order.getOrderedProducts()) {
			if (op.getProduct().equals(p)) {
				return true;
			}
		}
		return false;
	}

	public int getProductQuantity(Product p) {
		// TODO implement
		return 0;
	}

	public void setProductQuantity(Product p, int quantity) {
		// TODO implement
	}

	public void save() {
		System.out.println(currentUser.getEmail());
		order.setTimeCreated(new Date());
		order.setCompany(cdao.selectByUser(currentUser));
		order.setTotalCost(new BigDecimal(priceProcessor.getTotalPrice(order)));
		odao.create(order);
		order = new Order();
	}

}
