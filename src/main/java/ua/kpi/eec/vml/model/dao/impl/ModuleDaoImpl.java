package ua.kpi.eec.vml.model.dao.impl;

import java.io.Serializable;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.AbstractHibernateDao;
import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.entity.Module;

public class ModuleDaoImpl extends AbstractHibernateDao<Module> implements
		ModuleDao {

	@Override
	@Transactional
	public Module find(Serializable id) {
		Module module = super.find(id);
		if(module!=null) {
			Hibernate.initialize(module.getRoom());
		}
		return module;
	}
	
	@Override
	public Class<?> getEntityClass() {
		return Module.class;
	}
}
