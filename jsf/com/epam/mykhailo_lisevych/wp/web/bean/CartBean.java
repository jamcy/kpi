package com.epam.mykhailo_lisevych.wp.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.ejb.OrderBuilder;
import com.epam.mykhailo_lisevych.wp.ejb.OrderPriceProcessor;
import com.epam.mykhailo_lisevych.wp.entity.OrderedProduct;
import com.epam.mykhailo_lisevych.wp.entity.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
@SuppressWarnings("cdi-ambiguous-dependency")
public class CartBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderBuilder orderBuilder;

	@Inject
	private OrderPriceProcessor priceProcessor;

	private int quantity;
	private Product selectedProduct;

	public CartBean() {

	}

	public void clear() {

	}

	public void add() {
		orderBuilder.addProduct(selectedProduct, quantity);
	}

	public int getProductCount() {
		return orderBuilder.getOrder().getOrderedProducts().size();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int q) {
		quantity = q;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public boolean containsProduct(Product p) {
		return orderBuilder.containsProduct(p);
	}

	public List<Product> productList() {
		List<Product> result = new ArrayList<Product>();
		for (OrderedProduct op : orderBuilder.getOrder().getOrderedProducts()) {
			result.add(op.getProduct());
		}
		return result;
	}

	public String submit() {
		// TODO create order here
		orderBuilder.save();
		// orderBuilder.clear();
		return "catalogue";
	}

}
