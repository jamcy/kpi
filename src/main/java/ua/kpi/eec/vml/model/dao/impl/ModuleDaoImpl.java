package ua.kpi.eec.vml.model.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.entity.Module;

@Repository
public class ModuleDaoImpl extends AbstractHibernateDao<Module> implements
		ModuleDao {

	@Override
	@Transactional
	public Module find(Serializable id) {
		Module module = super.find(id);
		if(module!=null) {
			Hibernate.initialize(module.getRoom());
			Hibernate.initialize(module.getDescription());
			Hibernate.initialize(module.getPageContent());
		}
		return module;
	}
	
	@Override
	public Class<?> getEntityClass() {
		return Module.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Module> findAll() {
		return getSessionFactory().getCurrentSession().createQuery("from Module order by id").list();
	}
}
