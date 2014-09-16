package model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.datasrc.MysqlDataSource;
import model.entity.Module;

/**
 * 
 * @author spawn
 * 
 */
public class ModuleDao {

	@SuppressWarnings("unchecked")
	public List<Module> selectAll() {
		Session session = null;
		Transaction tx = null;
		List<Module> result = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<Module>) session.createQuery(
					"from Module order by id").list();
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
	public List<Module> selectByRoomId(long id) {
		Session session = null;
		Transaction tx = null;
		List<Module> result = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Module.class)
					.add(Restrictions.eq("roomId", id))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			result = crit.list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	public Module selectById(long id) {
		Module result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (Module) session.get(Module.class, id);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	public void update(Module module) {
		Transaction tx = null;
		Session session = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.update(module);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public void save(Module module) {
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.save(module);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

}
