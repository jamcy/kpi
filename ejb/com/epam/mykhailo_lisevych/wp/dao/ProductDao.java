package com.epam.mykhailo_lisevych.wp.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.epam.mykhailo_lisevych.wp.entity.Product;
import com.epam.mykhailo_lisevych.wp.entity.Product_;

@Stateless
public class ProductDao extends AbstractJpaDao<Product> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<?> getEntityClass() {
		return Product.class;
	}

	@SuppressWarnings("unchecked")
	public List<Product> selectAll() {
		return (List<Product>) getEntityManager().createQuery(
				"SELECT p FROM Product p").getResultList();
	}

	public List<Product> selectProducts(String name, String orderColumn,
			boolean orderAsc) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> product = cq.from(Product.class);
		cq.select(product);
		if (name != null && name.trim().length() != 0) {
			cq.where(cb.like(cb.lower(product.get(Product_.name)), "%"
					+ name.trim().toLowerCase() + "%"));
		}
		if (orderColumn.equals("name")) {
			if (orderAsc) {
				cq.orderBy(cb.asc(product.get(Product_.name)));
			} else {
				cq.orderBy(cb.desc(product.get(Product_.name)));
			}
		} else {
			if (orderColumn.equals("price")) {
				if (orderAsc) {
					cq.orderBy(cb.asc(product.get(Product_.priceRetail)));
				} else {
					cq.orderBy(cb.desc(product.get(Product_.priceRetail)));
				}
			} else {
				cq.orderBy(cb.asc(product.get(Product_.name)));
			}
		}
		TypedQuery<Product> q = getEntityManager().createQuery(cq);
		return q.getResultList();
	}

}
