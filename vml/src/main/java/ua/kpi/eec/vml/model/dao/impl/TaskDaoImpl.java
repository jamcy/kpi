package ua.kpi.eec.vml.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.TaskDao;
import ua.kpi.eec.vml.model.entity.Task;

@Repository
public class TaskDaoImpl extends AbstractHibernateDao<Task> implements TaskDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Task> findByCourseId(int courseId) {
		Session session = getSessionFactory().getCurrentSession();
		Query q = session.createQuery("FROM Task WHERE courseId=:courseId");
		q.setParameter("courseId", courseId);
		return q.list();
	}

	@Override
	public Class<?> getEntityClass() {
		return Task.class;
	}

}
