package ua.kpi.eec.vml.model.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.AbstractHibernateDao;
import ua.kpi.eec.vml.model.dao.PageDao;
import ua.kpi.eec.vml.model.entity.Page;

public class PageDaoImpl extends AbstractHibernateDao<Page> implements PageDao {

	@Override
	@Transactional
	public Page findByUrlSuffix(String urlSuffix) {
		Session session = getSessionFactory().getCurrentSession();
		Query q = session.createQuery("FROM Page where urlSuffix=:urlSuffix");
		q.setParameter("urlSuffix", urlSuffix);
		return (Page)q.uniqueResult();
	}

	@Override
	public Class<?> getEntityClass() {
		return Page.class;
	}
	
}
