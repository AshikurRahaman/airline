package com.example.airline.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "route", nullable = false)
    private String route;

    @Column(name = "airline")
    private String airline;

    @Column(name = "price", nullable = false)
    private Double price;


}
