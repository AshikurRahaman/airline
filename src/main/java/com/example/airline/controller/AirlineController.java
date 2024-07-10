package com.example.airline.controller;

import com.example.airline.model.TopRoutesResponse;
import com.example.airline.model.TopSalesDateResponse;
import com.example.airline.model.TotalSalesResponse;
import com.example.airline.service.ResponseEntityBuilder;
import com.example.airline.service.SalesService;
import com.example.airline.utils.AirlineUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/airline/api/v1")
public class AirlineController {
    private static final Logger logger = LoggerFactory.getLogger(AirlineController.class);

    private ResponseEntityBuilder responseEntityBuilder;

    private SalesService salesService;


    @GetMapping(value = "/total-sale/current-day", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TotalSalesResponse> getTotalSaleOfCurrentDay(){
        String traceId = AirlineUtils.getTraceId();
        logger.info("Comes to total-sales-of-current-day with traceId {}", traceId);
        TotalSalesResponse response = salesService.getTotalSalesOfCurrentDay(traceId);
        logger.info("total-sales-of-current-day response {} with traceId {}",response.toString(), traceId);
        return responseEntityBuilder.buildOkResponse(response);
    }

    @GetMapping("/top-selling-routes")
    public ResponseEntity<TopRoutesResponse> getTopSellingRoutes(){
        String traceId = AirlineUtils.getTraceId();
        logger.info("Comes to top-selling-routes with traceId {}", traceId);
        TopRoutesResponse response = salesService.getTopRoutesOnTicketSold(traceId);
        logger.info("top-selling-routes response {} with traceId {}",response.toString(), traceId);
        return responseEntityBuilder.buildOkResponse(response);
    }

    @GetMapping("/top-sales-date")
    public ResponseEntity<TopSalesDateResponse> getTopSalesDate(@RequestParam("startDate") LocalDate startDate,
                                                                @RequestParam("endDate") LocalDate endDate) {
        String traceId = AirlineUtils.getTraceId();
        logger.info("Comes to top-sales-date with startDate {}, endDate {} and with traceId {}",startDate, endDate, traceId);
        TopSalesDateResponse response = salesService.getTopSalesDate(startDate, endDate, traceId);
        logger.info("top-sales-date response {} with traceId {}", response.toString(), traceId);
        return responseEntityBuilder.buildOkResponse(response);
    }
}
