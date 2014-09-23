package ua.kpi.eec.vml.model.dao.impl;

import model.entity.Room;
import ua.kpi.eec.vml.model.dao.AbstractHibernateDao;
import ua.kpi.eec.vml.model.dao.RoomDao;

public class RoomDaoImpl extends AbstractHibernateDao<Room> implements RoomDao {

	@Override
	public Class<?> getEntityClass() {
		return Room.class;
	}
}
