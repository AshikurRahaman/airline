package com.example.airline.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopRoutesResponse {
    private List<String> topSellingRoutes;
}
