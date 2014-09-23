package ua.kpi.eec.vml.model.dao.impl;

import ua.kpi.eec.vml.model.dao.AbstractHibernateDao;
import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.entity.Module;

public class ModuleDaoImpl extends AbstractHibernateDao<Module> implements
		ModuleDao {

	@Override
	public Class<?> getEntityClass() {
		return Module.class;
	}
}
