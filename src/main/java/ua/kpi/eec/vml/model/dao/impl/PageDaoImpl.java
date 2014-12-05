package ua.kpi.eec.vml.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.PageDao;
import ua.kpi.eec.vml.model.entity.Page;

@Repository
public class PageDaoImpl extends AbstractHibernateDao<Page> implements PageDao {

	@Override
	@Transactional
	public Page findByUrlSuffix(String urlSuffix) {
		Session session = getSessionFactory().getCurrentSession();
		Query q = session.createQuery("FROM Page where urlSuffix=:urlSuffix");
		q.setParameter("urlSuffix", urlSuffix);
		return (Page)q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Page> findAll() {
		return getSessionFactory().getCurrentSession().createQuery("FROM Page ORDER BY id").list();
	}
	
	@Override
	public Class<?> getEntityClass() {
		return Page.class;
	}
	
}
