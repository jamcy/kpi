package com.epam.mykhailo_lisevych.wp.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.dao.OrderDao;
import com.epam.mykhailo_lisevych.wp.entity.Company;
import com.epam.mykhailo_lisevych.wp.entity.Order;
import com.epam.mykhailo_lisevych.wp.entity.OrderedProduct;
import com.epam.mykhailo_lisevych.wp.entity.Product;

public class OrderController {

	@Inject
	private OrderDao odao;

	public void createOrder(Company owner, Map<Product, Integer> content) {
		Order o = new Order();
		for (Entry<Product, Integer> e : content.entrySet()) {
			OrderedProduct op = new OrderedProduct();
			op.setProduct(e.getKey());
			op.setAmount(e.getValue());
			o.addOrderedProduct(op);
		}
		o.setCompany(owner);
		// TODO order creation stuff
		o.setSummary("TODO: summary report generation");
		o.setTotalCost(new BigDecimal(-100));
		o.setTimeCreated(new Date());
		odao.create(o);
	}

}
