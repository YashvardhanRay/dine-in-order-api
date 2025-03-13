package com.example.dio.model;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;

public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long itemId;

    private int quantity;

    private double totalPrice;

    private OrderStatus isOrdered;

    @ManyToOne
    private FoodItem foodItem;

    @ManyToOne
    private RestaurantTable restaurantTable;
}
