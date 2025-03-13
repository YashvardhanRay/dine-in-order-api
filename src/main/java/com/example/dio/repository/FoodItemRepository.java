package com.example.dio.repository;

import com.example.dio.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {

    @Query("SELECT fi FROM FoodItem fi INNER JOIN fi.categories c " +
            "WHERE c.category IN :categories " +
            "GROUP BY fi.id " +
            "HAVING COUNT(DISTINCT c.category) = :categoryCount")
    List<FoodItem> findByTwoCategories(
            @Param("categories") List<String> categories,
            @Param("categoryCount") int categoryCount);
}
