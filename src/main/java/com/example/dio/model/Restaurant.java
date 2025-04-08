package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Table(name = "restaurant")
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private long restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_no")
    private String contactNumber;

    @Column(name = "email", unique = true)
    private String contactEmail;

    @Column(name = "opens_at")
    private LocalTime opensAt;

    @Column(name = "closes_at")
    private LocalTime closesAt;

    @Column(name = "diet_type")
    private List<DietType> dietTypes;

    @Column(name = "created_at")
    private LocalTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @ManyToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<CuisineType> cuisineTypes;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTable> restaurantTables;

    @OneToMany(mappedBy = "restaurant")
    private List<FoodItem> foodItems;
}
