package com.example.airline.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopSalesDateResponse {
    private String topSalesDate;
    private String startDateRange;
    private String endDateRange;
}
