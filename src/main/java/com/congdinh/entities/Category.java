package com.congdinh.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "categories", schema = "dbo", uniqueConstraints = {
        // Define unique constraints if needed
        @UniqueConstraint(name = "uc_category_name", columnNames = "name")
}, indexes = {
        // Define indexes if needed
        @Index(name = "idx_category_name", columnList = "name")
})
public class Category extends EntityBase {
    @Column(name = "name", nullable = false, length = 100, columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(name = "description", length = 255, columnDefinition = "NVARCHAR(255)")
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    // Default constructor
    public Category() {
    }

    // Parameterized constructor
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
