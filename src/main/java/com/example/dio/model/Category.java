package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Table(name = "category")
@Entity
public class Category {

    @Id
    @Column(name = "category_name")
    private String category;

    @ManyToMany(mappedBy = "categories")
    private List<FoodItem> foodItems;
}
