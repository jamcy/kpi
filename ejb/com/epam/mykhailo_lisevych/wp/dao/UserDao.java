package com.epam.mykhailo_lisevych.wp.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.epam.mykhailo_lisevych.wp.entity.User;

@Stateless
public class UserDao extends AbstractJpaDao<User> {

	private static final long serialVersionUID = 1L;

	@Override
	public Class<?> getEntityClass() {
		return User.class;
	}

	public User selectByEmail(String email) {
		TypedQuery<User> query = getEntityManager().createNamedQuery(
				"User.selectByEmail", User.class);
		query.setParameter("email", email);
		return query.getResultList().get(0);
	}

}
