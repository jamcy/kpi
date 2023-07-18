package ua.kpi.eec.vml.model.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.AccountDao;
import ua.kpi.eec.vml.model.entity.Account;

@Repository
public class AccountDaoImpl extends AbstractHibernateDao<Account> implements AccountDao {

	@Override
	public Class<?> getEntityClass() {
		return Account.class;
	}

	@Transactional
	@Override
	public Account findByUsername(String username) {
		Session session = getSessionFactory().getCurrentSession();
		Query q = session.createQuery("FROM Account where username=:username");
		q.setParameter("username", username);
		Account result = (Account)q.uniqueResult();
		Hibernate.initialize(result.getCourses());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Account> findAll() {
		return getSessionFactory().getCurrentSession().createQuery("FROM Account ORDER BY id").list();
	}
}
