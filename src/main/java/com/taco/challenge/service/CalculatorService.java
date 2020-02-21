package com.taco.challenge.service;

import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public OrderTotalResponse calculateTotalOfOrder(OrderTotalRequest request) {
        return new OrderTotalResponse();
    }
}
