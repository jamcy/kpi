package model.dao;


import model.datasrc.MysqlDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

public class AbstractHibernateDao<T> implements GenericDao<T> {

    private Session session;

    public AbstractHibernateDao() {
        session = MysqlDataSource.getInstance().getFactory()
                .getCurrentSession();
    }

    @Override
    public void create(T e) throws Exception {
        Transaction tx = null;
        try {
            session = MysqlDataSource.getInstance().getFactory()
                    .getCurrentSession();
            tx = session.beginTransaction();
            session.save(e);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void read(T e, Serializable id) throws Exception {

    }

    @Override
    public void update(T e) throws Exception {

    }

    @Override
    public void delete(T e) throws Exception {

    }
}
