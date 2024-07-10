package com.example.airline.repository;

import com.example.airline.entity.PurchasedTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface PurchasedTickedRepository extends JpaRepository<PurchasedTicket, Long> {

 /*   @Query("SELECT new com.example.airline.dto.SalesData(TO_DATE(pt.purchaseDate, 'YYYY-MM-DD'), SUM(pt.totalPrice)) " +
            "FROM PurchasedTicket pt " +
//     "WHERE pt.purchaseDate BETWEEN :startDate AND :endDate " +  // Uncomment this line if needed
            "GROUP BY TO_DATE(pt.purchaseDate, 'YYYY-MM-DD') " +
            "ORDER BY SUM(pt.totalPrice) DESC")
    Optional<List<SalesData>> findTopSalesDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);*/


    @Query("SELECT SUM(pt.totalPrice) FROM PurchasedTicket pt WHERE pt.purchaseDate >= :startOfDay AND pt.purchaseDate <= :endOfDay")
    Optional<BigDecimal> getTotalSalesFromTickets(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT DATE(pt.purchaseDate) AS saleDay " +
            "FROM PurchasedTicket pt " +
            "WHERE pt.purchaseDate >= :startDate AND pt.purchaseDate <= :endDate " +
            "GROUP BY saleDay " +
            "ORDER BY SUM(pt.totalPrice) DESC "+
            " LIMIT 1")
    Optional<LocalDate> findMaxSaleDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t.route " +
            "FROM PurchasedTicket pt " +
            "JOIN pt.ticket t " +
            "GROUP BY t.route " +
            "ORDER BY SUM(pt.totalPrice) DESC LIMIT 3")
    Optional<List<String>> topThreeSellingRoutes();

}
