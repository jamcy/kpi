package ua.kpi.eec.vml.service;

import ua.kpi.eec.vml.model.entity.Account;
import ua.kpi.eec.vml.model.entity.Course;
import ua.kpi.eec.vml.model.entity.Module;

public interface AdminService {
	void updateAccount(Account account);

	void updateCourse(Course course);

	void addModule(Module module);

	void updateModule(Module module);
}
