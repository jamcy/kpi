package com.epam.mykhailo_lisevych.wp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractJpaDao<T> implements GenericDao<T> {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(name = "wp_jpa")
	private EntityManager em;

	@Override
	public void create(T e) {
		this.getEntityManager().persist(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T read(Object id) {
		return (T) getEntityManager().find(getEntityClass(), id);
	}

	@Override
	public void update(T e) {
		getEntityManager().refresh(e);
	}

	@Override
	public void delete(T e) {
		getEntityManager().remove(e);
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public abstract Class<?> getEntityClass();

}
