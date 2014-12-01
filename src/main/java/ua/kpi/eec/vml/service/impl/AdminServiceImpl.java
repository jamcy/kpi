package ua.kpi.eec.vml.service.impl;

import org.springframework.stereotype.Service;

import ua.kpi.eec.vml.model.dao.AccountDao;
import ua.kpi.eec.vml.model.dao.CourseDao;
import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.dao.PageDao;
import ua.kpi.eec.vml.model.entity.Account;
import ua.kpi.eec.vml.model.entity.Course;
import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	private AccountDao accountDao;
	private ModuleDao moduleDao;
	private CourseDao courseDao;
	private PageDao pageDao;
	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
	}
	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
	}
	@Override
	public void addModule(Module module) {
		// TODO Auto-generated method stub
	}
	@Override
	public void updateModule(Module module) {
		// TODO Auto-generated method stub
	}

}
