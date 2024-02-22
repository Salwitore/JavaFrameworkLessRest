package org.example.DataAccess.UnitOfWork;

import org.example.DataAccess.Repository.IRepository;
import org.example.DataAccess.Repository.Repository;
import org.example.Utils.Hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UnitOfWork implements IUnitOfWork {

    private SessionFactory sessionFactory;


    public UnitOfWork()
    {
        sessionFactory = HibernateUtils.sessionFactory;
    }

    private SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            sessionFactory = HibernateUtils.getSessionFactory();
        }
        return sessionFactory;
    }

    private void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public <T> Repository<T> GetRepository(Class<T> type)
    {
        return new Repository<T>(sessionFactory,type);
    }

}
