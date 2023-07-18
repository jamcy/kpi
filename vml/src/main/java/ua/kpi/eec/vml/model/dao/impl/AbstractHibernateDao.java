package ua.kpi.eec.vml.model.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.GenericDao;

import java.io.Serializable;

public abstract class AbstractHibernateDao<T> implements GenericDao<T> {

	private SessionFactory sessionFactory;

	public abstract Class<?> getEntityClass();

	@Override
	@Transactional
	public void create(T entity) throws Exception {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T find(Serializable id) {
		return (T) getSessionFactory().getCurrentSession().get(
				getEntityClass(), id);
	}

	@Override
	@Transactional
	public void update(T entity) {
		getSessionFactory().getCurrentSession().update(entity);
	}

	@Override
	public void delete(T entity) {
		getSessionFactory().getCurrentSession().delete(entity);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
