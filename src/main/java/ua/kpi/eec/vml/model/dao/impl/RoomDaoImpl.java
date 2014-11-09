package ua.kpi.eec.vml.model.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.Room;

public class RoomDaoImpl extends AbstractHibernateDao<Room> implements RoomDao {

	@Override
	public Class<?> getEntityClass() {
		return Room.class;
	}
	
	@Override
	@Transactional
	public Room find(Serializable id) {
		Room room = super.find(id);
		if(room!=null)
			Hibernate.initialize(room.getModules());
		return room;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Room> findAll() throws Exception {
		return getSessionFactory().getCurrentSession().createQuery("from Room order by id").list();
	}
}
