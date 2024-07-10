package com.example.airline.service;

import com.example.airline.model.TopRoutesResponse;
import com.example.airline.model.TopSalesDateResponse;
import com.example.airline.model.TotalSalesResponse;
import com.example.airline.port.PurchasedFoodPort;
import com.example.airline.port.PurchasedTicketPort;
import com.example.airline.utils.AirlineUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
public class SalesService {
    private PurchasedFoodPort purchasedFoodPort;
    private PurchasedTicketPort purchasedTicketPort;

    private static final Logger logger = LoggerFactory.getLogger(SalesService.class);

    public TotalSalesResponse getTotalSalesOfCurrentDay(String traceId){
        BigDecimal totalTicketPrice = purchasedTicketPort.getTotalPriceOfTicketSoldOfCurrentDate();
        logger.info("Total Ticket Price {} with traceId {}", totalTicketPrice, traceId);

        BigDecimal totalFoodPrice = purchasedFoodPort.getTotalPriceOfFoodSoldOfCurrentDate();
        logger.info("Total Food Price {} with traceId {}", totalFoodPrice, traceId);

        TotalSalesResponse response = new TotalSalesResponse();
        response.setTotalAmount(totalFoodPrice.add(totalTicketPrice));
        response.setDate(AirlineUtils.convertDate(new Date()));

        return response;
    }

    public TopRoutesResponse getTopRoutesOnTicketSold(String traceId){
        List<String> routes = purchasedTicketPort.getTopThreeSellingRoutes();
        logger.info("Top three selling routes {} with traceId {}", routes, traceId);

        TopRoutesResponse response = new TopRoutesResponse();
        response.setTopSellingRoutes(routes);

        return response;
    }

    public TopSalesDateResponse getTopSalesDate(LocalDate startDate, LocalDate endDate, String traceId){
        LocalDate topSalesDate = purchasedTicketPort.getTopSellingDate(startDate, endDate);
        logger.info("Top Sale date on Tickets {} with traceId {}", topSalesDate, traceId);

        String convertedTopSalesDate = AirlineUtils.convertLocalDate(topSalesDate);
        logger.info("Converted Top Sale date {} with traceId {}", convertedTopSalesDate, traceId);

        TopSalesDateResponse response = new TopSalesDateResponse();
        response.setTopSalesDate(convertedTopSalesDate);
        response.setStartDateRange(AirlineUtils.convertLocalDate(startDate));
        response.setEndDateRange(AirlineUtils.convertLocalDate(endDate));

        return response;
    }
}
