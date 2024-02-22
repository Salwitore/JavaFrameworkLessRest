package org.example.Utils.Hibernate;

import org.example.Data.Models.Company.CompanyModel;
import org.example.Data.Models.User.UserModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    public static SessionFactory sessionFactory;

    public static void BuildSessionFactory()
    {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(UserModel.class);
        configuration.addAnnotatedClass(CompanyModel.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
