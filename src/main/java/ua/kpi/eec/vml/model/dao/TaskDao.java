package ua.kpi.eec.vml.model.dao;

import java.util.List;

import ua.kpi.eec.vml.model.entity.Task;

public interface TaskDao extends GenericDao<Task> {
	List<Task> findByCourseId(int courseId);
}
