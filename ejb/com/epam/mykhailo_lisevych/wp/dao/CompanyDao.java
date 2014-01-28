package com.epam.mykhailo_lisevych.wp.dao;

import javax.persistence.TypedQuery;

import com.epam.mykhailo_lisevych.wp.entity.Company;
import com.epam.mykhailo_lisevych.wp.entity.User;

public class CompanyDao extends AbstractJpaDao<Company> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<?> getEntityClass() {
		return Company.class;
	}

	public Company selectByUser(User u) {
		TypedQuery<Company> query = getEntityManager().createNamedQuery(
				"Company.selectByUser", Company.class);
		query.setParameter("u", u);
		return query.getResultList().get(0);
	}

}
