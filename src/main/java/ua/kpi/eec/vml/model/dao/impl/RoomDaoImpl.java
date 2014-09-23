package ua.kpi.eec.vml.model.dao.impl;

import ua.kpi.eec.vml.model.dao.AbstractHibernateDao;
import ua.kpi.eec.vml.model.dao.RoomDao;
import ua.kpi.eec.vml.model.entity.Room;

public class RoomDaoImpl extends AbstractHibernateDao<Room> implements RoomDao {

	@Override
	public Class<?> getEntityClass() {
		return Room.class;
	}
}
