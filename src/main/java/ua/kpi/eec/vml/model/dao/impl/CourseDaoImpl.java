package ua.kpi.eec.vml.model.dao.impl;

import ua.kpi.eec.vml.model.entity.Course;

public class CourseDaoImpl extends AbstractHibernateDao<Course> {

	@Override
	public Class<?> getEntityClass() {
		return Course.class;
	}

}
