package com.example.dio.repository;

import com.example.dio.model.CuisineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<CuisineType, String> {
}
