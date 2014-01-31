package com.epam.mykhailo_lisevych.wp.web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.controller.ProductController;
import com.epam.mykhailo_lisevych.wp.entity.Product;

@ManagedBean
@ViewScoped
public class CatalogueBean {

	@Inject
	private ProductController product;

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@PostConstruct
	private void init() {
		products = product.selectAll();
	}

}
