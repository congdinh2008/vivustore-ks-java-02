package com.congdinh;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        System.out.println("=============Check Hibernate Configuration=============");
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("Hibernate session opened successfully!");
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}