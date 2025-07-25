package com.congdinh.entities;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "customers", schema = "dbo", indexes = {
        @Index(name = "idx_customer_first_name", columnList = "first_name"),
        @Index(name = "idx_customer_last_name", columnList = "last_name"),
        @Index(name = "idx_customer_phone_number", columnList = "phone_number")
})
public class Customer extends EntityBase {
    @Column(name = "first_name", nullable = false, length = 255, columnDefinition = "NVARCHAR(255)")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255, columnDefinition = "NVARCHAR(255)")
    private String lastName;

    @Column(name = "phone_number", length = 15, unique = true)
    private String phoneNumber;

    @Column(name = "address", length = 255, columnDefinition = "NVARCHAR(255)")
    private String address;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(String firstName, String lastName, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
