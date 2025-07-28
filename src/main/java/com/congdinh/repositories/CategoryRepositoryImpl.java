package com.congdinh.repositories;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.congdinh.entities.Category;
import com.congdinh.utils.HibernateUtils;

public class CategoryRepositoryImpl implements CategoryRepository {
    private SessionFactory sessionFactory;

    public CategoryRepositoryImpl() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public List<Category> findAll() {
        // 1. Create session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Category> categories = null;

        try {
            // 2. Begin transaction
            transaction = session.beginTransaction();
            // 3. Query to get all categories
            // 4. Return the list of categories ~ SELECT * FROM categories
            categories = session.createQuery("FROM Category", Category.class).list();
            transaction.commit();
        } catch (Exception e) {
            // 5. Handle exceptions and rollback if necessary
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // 6. Close session
            session.close();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        // 1. Create Session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Category category = session.find(Category.class, id);
            // Query query = session.createQuery("FROM Category WHERE id = :id", Category.class);
            // query.setParameter("id", id);
            // Category category = query.uniqueResult();
            transaction.commit();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception, log it, or rethrow it as needed
            System.err.println("Error finding category by ID: " + e.getMessage());
        } finally {
            session.close();
        }
        return null; // Return null if not found or on error
    }

    @Override
    public Category findByName(String name) {
        // 1. Create Session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            // 2. Query to find category by name
            Category category = session.createQuery("FROM Category WHERE name = :name", Category.class)
                    .setParameter("name", name)
                    .uniqueResult();
            transaction.commit();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception, log it, or rethrow it as needed
            System.err.println("Error finding category by name: " + e.getMessage());
        } finally {
            session.close();
        }
        return null; // Return null if not found or on error
    }

    @Override
    public void create(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception, log it, or rethrow it as needed
            System.err.println("Error creating category: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            // Check if the category exists
            Category category = session.find(Category.class, id);
            if (category == null) {
                System.out.println("Category with ID " + id + " does not exist.");
                return;
            }
            session.remove(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception, log it, or rethrow it as needed
            System.err.println("Error deleting category: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void update(int id, Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            // Check if the category exists
            Category existingCategory = session.find(Category.class, id);
            if (existingCategory == null) {
                System.out.println("Category with ID " + id + " does not exist.");
                return;
            }

            // Check if category name is already taken
            Category existingByName = findByName(category.getName());
            if (existingByName != null && existingByName.getId() != id) {
                System.out.println("Category name '" + category.getName() + "' is already taken.");
                return;
            }
            category.setId(id);
            session.merge(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            // Handle the exception, log it, or rethrow it as needed
            System.err.println("Error creating category: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
