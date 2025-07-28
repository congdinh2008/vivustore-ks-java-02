package com.congdinh.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(); // Load hibernate.cfg.xml

            if(sessionFactory == null) {
                sessionFactory = configuration.buildSessionFactory();
            }
        } catch (Exception ex) {
            // Log the exception
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
