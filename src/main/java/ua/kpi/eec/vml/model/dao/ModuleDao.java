package ua.kpi.eec.vml.model.dao;

import java.util.List;

import ua.kpi.eec.vml.model.entity.Module;

public interface ModuleDao extends GenericDao<Module> {
	List<Module> findAll();
	Module findByFolder(String folder);
}
