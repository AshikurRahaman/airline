package com.example.airline.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TotalSalesResponse {
    private BigDecimal totalAmount;
    private String date;
}
