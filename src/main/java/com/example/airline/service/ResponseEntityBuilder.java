package com.example.airline.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseEntityBuilder {
    public <T> ResponseEntity<T> buildOkResponse(T model){
        return ResponseEntity.ok(model);
    }
}
