package com.congdinh;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.congdinh.repositories.CategoryRepositoryImpl;
import com.congdinh.entities.Category;
import com.congdinh.repositories.CategoryRepository;

public class Main {
    public static void main(String[] args) {
        // checkConfig();
        // Check CategoryRepository
        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
        // Fetch all categories
        categoryRepository.findAll().forEach(category -> {
            System.out.println(category.getName());
        });

        // Fetch category by ID
        int categoryId = 1; // Example ID
        var category = categoryRepository.findById(categoryId);
        if (category != null) {
            System.out.println("Category found: " + category.getName());
        } else {
            System.out.println("Category with ID " + categoryId + " not found.");
        }

        

        // Create a new category
        if(categoryRepository.findByName("New Category") == null) {
            var newCategory = new Category("New Category", "This is a new category");
            categoryRepository.create(newCategory);
            System.out.println("New category created.");
        } else {
            System.out.println("Category 'New Category' already exists.");
        }
    }

    private static void checkConfig() {
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