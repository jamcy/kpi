package ua.kpi.eec.vml.model.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.AbstractHibernateDao;
import ua.kpi.eec.vml.model.dao.AccountDao;
import ua.kpi.eec.vml.model.entity.Account;

public class AccountDaoImpl extends AbstractHibernateDao<Account> implements AccountDao {

	@Override
	public Class<?> getEntityClass() {
		return Account.class;
	}

	@Transactional
	@Override
	public Account findByEmail(String email) {
		//TODO implement
		return null;
	}

}
