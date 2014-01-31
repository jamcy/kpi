package com.epam.mykhailo_lisevych.wp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.ejb.UserStateBean;
import com.epam.mykhailo_lisevych.wp.entity.Product;

@Stateless
@RolesAllowed({ "COMPANY" })
public class CartController {

	@Inject
	private UserStateBean userState;

	@Inject
	private OrderController order;

	public void addLine(Product p, int quantity) {
		userState.getOrderLines().put(p, new Integer(quantity));
	}

	public void removeLine(Product p) {
		userState.getOrderLines().remove(p);
	}

	public boolean containsLine(Product p) {
		return userState.getOrderLines().containsKey(p);
	}

	public int getQuantity(Product p) {
		return userState.getOrderLines().get(p);
	}

	public void setQuantity(Product p, int quantity) {
		userState.getOrderLines().remove(p);
		addLine(p, quantity);
	}

	public void clear() {
		userState.setOrderLines(new HashMap<Product, Integer>());
	}

	public void createOrder() {
		order.createOrder(userState.getCurrentUser().getCompany().get(0),
				userState.getOrderLines());
		clear();
	}

	public List<Product> getOrderedProducts() {
		List<Product> result = new ArrayList<Product>();
		for (Product p : userState.getOrderLines().keySet()) {
			result.add(p);
		}
		return result;
	}

	public int getCartSize() {
		return userState.getOrderLines().size();
	}

}
