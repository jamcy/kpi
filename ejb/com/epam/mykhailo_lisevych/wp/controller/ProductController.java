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
}
