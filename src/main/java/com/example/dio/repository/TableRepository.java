package com.example.dio.repository;

import com.example.dio.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
}
