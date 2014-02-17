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

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ProductController product;

	private List<Product> products;

	private String searchFilter;
	private String sortFilter;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getSortFilter() {
		return sortFilter;
	}

	public void setSortFilter(String sortFilter) {
		this.sortFilter = sortFilter;
	}

	@PostConstruct
	private void init() {
		products = product.selectAll();
	}

	public void search() {
		products = product.select(searchFilter, sortFilter);
	}
}
