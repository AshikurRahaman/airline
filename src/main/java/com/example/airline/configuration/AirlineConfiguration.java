package com.example.airline.configuration;

import com.example.airline.port.PurchasedFoodPort;
import com.example.airline.port.PurchasedTicketPort;
import com.example.airline.repository.PurchasedFoodRepository;
import com.example.airline.repository.PurchasedTickedRepository;
import com.example.airline.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirlineConfiguration {
    @Autowired
    private PurchasedFoodRepository purchasedFoodRepository;

    @Autowired
    private PurchasedTickedRepository purchasedTickedRepository;

    @Bean
    public PurchasedFoodPort purchasedFoodPort(){
        return new PurchasedFoodPort(purchasedFoodRepository);
    }

    @Bean
    public PurchasedTicketPort purchasedTicketPort(){
        return new PurchasedTicketPort(purchasedTickedRepository);
    }

    @Bean
    public SalesService salesService(){
        return new SalesService(purchasedFoodPort(), purchasedTicketPort());
    }
}
