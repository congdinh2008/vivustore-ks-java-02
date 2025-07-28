package com.congdinh.repositories;

import java.util.List;

import com.congdinh.entities.Category;

public interface CategoryRepository {
    List<Category> findAll();

    Category findById(int id);


    Category findByName(String name);

    void create(Category category);

    void delete(int id);

    void update(int id, Category category);
}
