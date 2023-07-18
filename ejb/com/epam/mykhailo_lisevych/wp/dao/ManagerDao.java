package com.epam.mykhailo_lisevych.wp.dao;

import java.util.List;

import javax.ejb.Stateless;

import com.epam.mykhailo_lisevych.wp.entity.Manager;

@Stateless
public class ManagerDao extends AbstractJpaDao<Manager> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Class<?> getEntityClass() {
		return Manager.class;
	}

	@SuppressWarnings("unchecked")
	public List<Manager> selectAll() {
		return (List<Manager>) getEntityManager().createQuery(
				"SELECT m FROM Manager m").getResultList();
	}

}
