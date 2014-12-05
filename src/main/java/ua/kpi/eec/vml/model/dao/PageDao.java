package ua.kpi.eec.vml.model.dao;

import java.util.List;

import ua.kpi.eec.vml.model.entity.Page;

public interface PageDao extends GenericDao<Page> {
	Page findByUrlSuffix(String urlSuffix);
	List<Page> findAll();
}
