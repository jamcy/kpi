package model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.datasrc.MysqlDataSource;
import model.entity.Room;

public class RoomDao {

	@SuppressWarnings("unchecked")
	public List<Room> selectAll() {
		List<Room> result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory()
					.getCurrentSession();
			tx = session.beginTransaction();
			result = (List<Room>) session.createQuery("FROM Room ORDER BY id")
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

	public Room selectById(long id) {
		Room result = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = MysqlDataSource.getInstance().getFactory().openSession();
			tx = session.beginTransaction();
			result = (Room) session.get(Room.class, new Long(id));
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}
}
