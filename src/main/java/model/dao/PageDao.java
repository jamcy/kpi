package model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import model.datasrc.MysqlDataSource;
import model.entity.Page;

public class PageDao {

	// TODO implement
	public List<String> getSuffices() {
		throw new NotImplementedException();
	}

	@SuppressWarnings("unchecked")
	public Page selectBySuffix(String suffix) {
		Page result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			Criteria crit = session.createCriteria(Page.class).add(
					Restrictions.eq("urlSuffix", suffix));
			List<Page> pages = (List<Page>) crit.list();
			tx.commit();
			if (pages != null && pages.size() != 0) {
				result = pages.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	public Page selectById(Long id) {
		Page result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (Page) session.get(Page.class, id);
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
	public List<Page> selectAll() {
		List<Page> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<Page>) session.createQuery("FROM Page order by id")
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

	public void save(Page page) {
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.save(page);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}

	public void update(Page page) {
		Transaction tx = null;
		Session session = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			session.update(page);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
	}
}
