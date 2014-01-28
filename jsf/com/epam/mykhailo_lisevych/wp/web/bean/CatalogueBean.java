package com.epam.mykhailo_lisevych.wp.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.mykhailo_lisevych.wp.dao.ProductDao;
import com.epam.mykhailo_lisevych.wp.entity.Product;
import com.epam.mykhailo_lisevych.wp.entity.User;

@Named("catalogueBean")
@ViewScoped
public class CatalogueBean {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private User currentUser;

	@EJB
	private ProductDao pdao;

	private List<Product> products;
	private Product selectedProduct;

	public CatalogueBean() {

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public void search() {
		System.out.println(selectedProduct);
	}

	public void addToCartActionListener() {
		System.out.println("Add to cart");
	}

	@PostConstruct
	private void init() {
		products = pdao.selectAll();
	}

	public void attrListener(ActionEvent e) {
		selectedProduct = (Product) e.getComponent().getAttributes()
				.get("product");
		System.out.println(selectedProduct);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
