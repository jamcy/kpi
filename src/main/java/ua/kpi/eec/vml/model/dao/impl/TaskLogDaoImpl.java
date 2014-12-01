package ua.kpi.eec.vml.model.dao.impl;

import org.springframework.stereotype.Repository;

import ua.kpi.eec.vml.model.dao.TaskLogDao;
import ua.kpi.eec.vml.model.entity.TaskLog;

@Repository
public class TaskLogDaoImpl extends AbstractHibernateDao<TaskLog> implements TaskLogDao {

	@Override
	public Class<?> getEntityClass() {
		return TaskLog.class;
	}

}
