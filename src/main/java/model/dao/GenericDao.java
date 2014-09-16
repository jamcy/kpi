package model.dao;

import java.io.Serializable;

public interface GenericDao<T> {
    void create(T e) throws Exception;

    void read(T e, Serializable id) throws Exception;

    void update(T e) throws Exception;

    void delete(T e) throws Exception;
}
