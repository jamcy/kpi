package ua.kpi.eec.vml.model.dao;

import ua.kpi.eec.vml.model.entity.Page;

public interface PageDao extends GenericDao<Page> {
	public Page findByUrlSuffix(String urlSuffix);
}
