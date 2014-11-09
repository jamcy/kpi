package ua.kpi.eec.vml.model.dao.impl;

import ua.kpi.eec.vml.model.dao.TaskLogDao;
import ua.kpi.eec.vml.model.entity.TaskLog;

public class TaskLogDaoImpl extends AbstractHibernateDao<TaskLog> implements TaskLogDao {

	@Override
	public Class<?> getEntityClass() {
		return TaskLog.class;
	}

}
