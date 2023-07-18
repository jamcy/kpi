package com.epam.mykhailo_lisevych.wp.web.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.ejb.UserStateBean;
import com.epam.mykhailo_lisevych.wp.entity.Product;

@Named("stateBean")
@SessionScoped
public class StateBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private UserStateBean userState;

	private Product cartSelectedProduct;
	private int cartSelectedProductQuantity;

	public UserStateBean getUserState() {
		return userState;
	}

	public void setUserState(UserStateBean userState) {
		this.userState = userState;
	}

	public Product getCartSelectedProduct() {
		return cartSelectedProduct;
	}

	public void setCartSelectedProduct(Product cartSelectedProduct) {
		this.cartSelectedProduct = cartSelectedProduct;
	}

	public int getCartSelectedProductQuantity() {
		return cartSelectedProductQuantity;
	}

	public void setCartSelectedProductQuantity(int cartSelectedProductQuantity) {
		this.cartSelectedProductQuantity = cartSelectedProductQuantity;
	}

}
