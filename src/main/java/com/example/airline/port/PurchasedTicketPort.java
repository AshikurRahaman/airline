package com.example.airline.port;

import com.example.airline.repository.PurchasedTickedRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PurchasedTicketPort {
    private PurchasedTickedRepository purchasedTickedRepository;

    public BigDecimal getTotalPriceOfTicketSoldOfCurrentDate(){
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        BigDecimal ticketPrice = purchasedTickedRepository.getTotalSalesFromTickets(startOfDay, endOfDay).orElse(new BigDecimal(0));

        return ticketPrice;
    }

    public LocalDate getTopSellingDate(LocalDate startDate, LocalDate endDate){

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        LocalDate date = purchasedTickedRepository.findMaxSaleDay(startDateTime, endDateTime).orElse(null);
        return date;
    }

    public List<String> getTopThreeSellingRoutes(){
        List<String> routeSaleDTOS = purchasedTickedRepository.topThreeSellingRoutes().orElse(new ArrayList<>());
        return routeSaleDTOS;

    }
}
