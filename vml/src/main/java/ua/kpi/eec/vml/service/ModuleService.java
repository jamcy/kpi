package ua.kpi.eec.vml.service;

import ua.kpi.eec.vml.model.entity.Module;

public interface ModuleService {
	public void add(Module module) throws Exception;
	public void update(Module module) throws Exception;
}
