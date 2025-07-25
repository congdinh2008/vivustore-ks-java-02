package com.congdinh.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_details", schema = "dbo")
public class ProductDetails {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private int id;

    @Id
    @Column(name = "product_id")
    private int productDetailsId;

    private String color;

    private Double weight;
    
    private String size;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    // Default constructor
    public ProductDetails() {
    }

    // Parameterized constructor
    public ProductDetails(String color, Double weight, String size) {
        this.color = color;
        this.weight = weight;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
