package ua.kpi.eec.vml.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ua.kpi.eec.vml.model.datasrc.MysqlDataSource;
import ua.kpi.eec.vml.model.entity.TaskLog;

public class TaskLogDao {

	@SuppressWarnings("unchecked")
	public List<TaskLog> selectAll() {
		List<TaskLog> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<TaskLog>) session.createCriteria(TaskLog.class)
					.list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public TaskLog select(Long taskid, Long userid) {
		TaskLog result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			List<TaskLog> res = (List<TaskLog>) session
					.createCriteria(TaskLog.class)
					.add(Restrictions.eq("userId", userid))
					.add(Restrictions.eqOrIsNull("taskId", taskid)).list();
			if (res != null && res.size() > 0) {
				result = res.get(0);
			}
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	public TaskLog selectById(Long id) {
		TaskLog result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (TaskLog) session.get(TaskLog.class, id);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<TaskLog> selectByUserId(Long userid) {
		List<TaskLog> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<TaskLog>) session.createCriteria(TaskLog.class)
					.add(Restrictions.eq("userId", userid)).list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}
	
	@SuppressWarnings({"unchecked"})
	public List<TaskLog> selectByTaskId(long taskId) {
		List<TaskLog> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<TaskLog>) session.createCriteria(TaskLog.class)
					.add(Restrictions.eq("taskId", taskId)).list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	public void saveOrUpdate(TaskLog tlg) {
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(tlg);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

}
