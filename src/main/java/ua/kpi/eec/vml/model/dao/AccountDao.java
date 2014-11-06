package ua.kpi.eec.vml.model.dao;

import ua.kpi.eec.vml.model.entity.Account;

public interface AccountDao extends GenericDao<Account> {
	Account findByEmail(String email);
}
