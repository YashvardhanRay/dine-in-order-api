package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "staffs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff extends User{

    @ManyToMany
    private List<RestaurantTable> restaurantTables;

    @ManyToOne
    private Restaurant restaurent;

}
