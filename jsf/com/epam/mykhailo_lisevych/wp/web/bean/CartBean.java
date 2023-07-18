package com.epam.mykhailo_lisevych.wp.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRException;

import com.epam.mykhailo_lisevych.wp.controller.CartController;
import com.epam.mykhailo_lisevych.wp.entity.Product;

@ManagedBean
@ViewScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private CartController cart;

	@Inject
	private StateBean stateBean;

	private Product selectedProduct;
	private int selectedProductCount;

	public void add() {
		cart.addLine(stateBean.getCartSelectedProduct(),
				stateBean.getCartSelectedProductQuantity());
	}

	public String clear() {
		cart.clear();
		return "index?faces-redirect=true";
	}

	public boolean isProductInCart(Product p) {
		return cart.containsLine(p);
	}

	public int getCartSize() {
		return cart.getCartSize();
	}

	public double getLineCost(Product p) {
		return cart.getLineCost(p);
	}

	public double getTotalCost() {
		return cart.getTotalCost();
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

	public void updateLine() {
		cart.setQuantity(selectedProduct, selectedProductCount);
	}

	public String createOrder() throws JRException {
		cart.createOrder();
		return "order-list?faces-redirect=true";
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public int getSelectedProductCount() {
		return selectedProductCount;
	}

	public void setSelectedProductCount(int selectedProductCount) {
		this.selectedProductCount = selectedProductCount;
	}

}
