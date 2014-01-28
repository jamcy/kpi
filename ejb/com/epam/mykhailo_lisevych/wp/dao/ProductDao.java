package com.epam.mykhailo_lisevych.wp.dao;

import java.util.List;

import javax.ejb.Stateless;

import com.epam.mykhailo_lisevych.wp.entity.Product;

@Stateless
public class ProductDao extends AbstractJpaDao<Product> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<?> getEntityClass() {
		return Product.class;
	}

	@SuppressWarnings("unchecked")
	public List<Product> selectAll() {
		return (List<Product>) getEntityManager().createQuery("SELECT p FROM Product p")
				.getResultList();
	}

}
