package ua.kpi.eec.vml.model.dao;

import java.util.List;

import ua.kpi.eec.vml.model.entity.Room;

public interface RoomDao extends GenericDao<Room> {
	public List<Room> findAll();
}
