package com.epam.mykhailo_lisevych.wp.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.controller.CartController;
import com.epam.mykhailo_lisevych.wp.entity.Product;

@ManagedBean
@ViewScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CartController cart;

	@Inject
	private StateBean stateBean;

	public void add() {
		cart.addLine(stateBean.getCartSelectedProduct(),
				stateBean.getCartSelectedProductQuantity());
	}

	public void clear() {
		cart.clear();
	}

	public boolean isProductInCart(Product p) {
		return cart.containsLine(p);
	}

	public int getCartSize() {
		return cart.getCartSize();
	}

	public void remove(Product p) {
		cart.removeLine(p);
	}

	public List<Product> getOrderedProducts() {
		return cart.getOrderedProducts();
	}

	public int getQuantity(Product p) {
		return cart.getQuantity(p);
	}
	
	public String createOrder() {
		cart.createOrder();
		return "index";
	}
}
