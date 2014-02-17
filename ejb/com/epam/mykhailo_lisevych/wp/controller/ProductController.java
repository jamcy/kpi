package com.epam.mykhailo_lisevych.wp.controller;

import java.util.List;

import javax.inject.Inject;

import com.epam.mykhailo_lisevych.wp.dao.ProductDao;
import com.epam.mykhailo_lisevych.wp.entity.Product;

public class ProductController {

	@Inject
	private ProductDao pdao;

	public List<Product> selectAll() {
		return pdao.selectAll();
	}

	public List<Product> select(String name, String sort) {
		if (sort.equals("na")) {
			return pdao.selectProducts(name, "name", true);
		}
		if (sort.equals("nd")) {
			return pdao.selectProducts(name, "name", false);
		}
		if (sort.equals("pa")) {
			return pdao.selectProducts(name, "price", true);
		}
		if (sort.equals("pd")) {
			return pdao.selectProducts(name, "price", false);
		}
		return pdao.selectProducts(name, "name", true);
	}
}
