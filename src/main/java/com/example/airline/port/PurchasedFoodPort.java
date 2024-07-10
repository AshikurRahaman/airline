package com.example.airline.port;

import com.example.airline.repository.PurchasedFoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
public class PurchasedFoodPort {
    private PurchasedFoodRepository purchasedFoodRepository;

    public BigDecimal getTotalPriceOfFoodSoldOfCurrentDate(){
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        BigDecimal foodPrice = purchasedFoodRepository.getTotalSalesFromFoods(startOfDay, endOfDay).orElse(new BigDecimal(0));

        return foodPrice;
    }
}
