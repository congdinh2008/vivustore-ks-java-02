package com.congdinh.entities;

import java.time.LocalDateTime;
import java.util.Set;

import com.congdinh.enums.OrderStatus;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders", schema = "dbo", indexes = {
        // Define indexes if needed
        @Index(name = "idx_order_date", columnList = "order_date"),
        @Index(name = "idx_order_delivery_address", columnList = "delivery_address")
})
public class Order extends EntityBase {

    @Column(name = "order_code", nullable = false, length = 50, unique = true)
    private String orderCode;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "delivery_date", nullable = false)   
    private LocalDateTime deliveryDate;
    
    private OrderStatus status;

    @Column(name = "delivery_address", length = 255, columnDefinition = "NVARCHAR(255)")
    private String deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // @ManyToMany
    // @JoinTable(name = "order_details",
    //         joinColumns = @JoinColumn(name = "order_id"),
    //         inverseJoinColumns = @JoinColumn(name = "product_id"))
    // private Set<Product> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
