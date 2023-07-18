package ua.kpi.eec.vml.model.dao;

import java.util.List;

import ua.kpi.eec.vml.model.entity.Course;

public interface CourseDao extends GenericDao<Course> {
	List<Course> findAll();
}
