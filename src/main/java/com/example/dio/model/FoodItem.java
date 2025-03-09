package com.example.dio.model;

import com.example.dio.enums.DietType;
import com.example.dio.enums.StockStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "food_item")
public class FoodItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name ="name")
    private String name;

    @Column(name ="price")
    private double price;

    @Column(name = "description")
    private String Description;

    @Column(name ="stock")
    private int stock;

    @Column(name = "availability")
    private StockStatus availability;

    @Column(name = "diet_type")
    @Enumerated
    private DietType dietType;

    @Column(name = "created_at")
    private LocalDate createdat;

    @Column(name = "last_modified_at")
    private LocalDateTime lastmodifiedat;

    @ManyToOne
    private CuisineType cuisineType;

    @ManyToOne
    private Restaurant restaurent;
}
