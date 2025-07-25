package com.congdinh.enums;

public enum OrderStatus {
    PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    RETURNED,
    CANCELLED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
