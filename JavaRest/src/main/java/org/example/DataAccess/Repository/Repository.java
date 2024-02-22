package org.example.DataAccess.Repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class Repository<T> implements IRepository<T> {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public Repository(SessionFactory sessionFactory, Class<T> type) {
        this.sessionFactory = sessionFactory;
        this.type = type;
    }


    private Session GetSession() {
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
        }
        return session;
    }

    protected void CloseSession() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Override
    public int Add(T model) {
        int id = (int) GetSession().save(model);
        return id;
    }

    @Override
    public T Get(int id) {

        T model = GetSession().get(type, id);
        CloseSession();
        return model;
    }

    @Override
    public List<T> GetAll() {
        List<T> models = GetSession().createQuery("from " + type.getName(), type).list();
        CloseSession();
        return models;
    }

    @Override
    public void Update(T entity) {
        GetSession().update(entity);
    }

    @Override
    public void Delete(T entity) {
        GetSession().delete(entity);
    }


    @Override
    public boolean SaveChanges() {

        try {
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            CloseSession();
        }

    }


}
