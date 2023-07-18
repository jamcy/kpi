package com.epam.mykhailo_lisevych.wp.dao;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface GenericDao<T> extends Serializable {

	public void create(T e);

	public T read(Object id);

	public void update(T e);

	public void delete(T e);

}
