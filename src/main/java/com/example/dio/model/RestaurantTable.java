package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "table_number")
    private String tableNumber;

    @Column(name = "table_capacity")
    private int tableCapacity;

    @Column(name = "status")
    private TableStatus status;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "restaurantTables")
    private List<Staff> staffs;

}
