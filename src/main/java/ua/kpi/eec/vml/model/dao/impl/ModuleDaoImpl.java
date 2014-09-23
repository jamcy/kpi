package ua.kpi.eec.vml.model.dao.impl;

import model.entity.Module;
import ua.kpi.eec.vml.model.dao.AbstractHibernateDao;
import ua.kpi.eec.vml.model.dao.ModuleDao;

public class ModuleDaoImpl extends AbstractHibernateDao<Module> implements
		ModuleDao {

	@Override
	public Class<?> getEntityClass() {
		return Module.class;
	}
}
