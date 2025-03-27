package com.example.dio.model;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long itemId;

    private int quantity;

    private double totalPrice;

    private boolean ordered;

    @ManyToOne
    private FoodItem foodItem;

    @ManyToOne
    private RestaurantTable restaurantTable;
}
