package ua.kpi.eec.vml.model.dao;

import java.util.List;

import ua.kpi.eec.vml.model.entity.Account;

public interface AccountDao extends GenericDao<Account> {
	Account findByUsername(String username);

	List<Account> findAll();
}
