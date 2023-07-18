package ua.kpi.eec.vml.model.dao;

import java.io.Serializable;

public interface GenericDao<T> {
    void create(T e) throws Exception;

    T find(Serializable id);

    void update(T e);

    void delete(T e);
}
