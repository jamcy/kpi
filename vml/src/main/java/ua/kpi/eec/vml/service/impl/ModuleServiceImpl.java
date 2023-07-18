package ua.kpi.eec.vml.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kpi.eec.vml.model.dao.ModuleDao;
import ua.kpi.eec.vml.model.entity.Module;
import ua.kpi.eec.vml.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleDao moduleDao;
	
	@Override
	public void add(Module module) throws Exception {
		moduleDao.create(module);
	}

	@Override
	public void update(Module module) throws Exception {
		moduleDao.update(module);
	}
	
}
