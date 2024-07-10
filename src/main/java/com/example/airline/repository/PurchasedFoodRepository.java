package com.example.airline.repository;

import com.example.airline.entity.PurchasedFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchasedFoodRepository extends JpaRepository<PurchasedFood, Long> {
    Optional<List<PurchasedFood>> findAllByPurchaseDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(pt.totalPrice) FROM PurchasedFood pt WHERE pt.purchaseDate BETWEEN :startOfDay AND :endOfDay")
    Optional<BigDecimal> getTotalSalesFromFoods(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
