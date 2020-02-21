package com.taco.challenge.service;

import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;

public interface CalculatorService {

    OrderTotalResponse calculateTotalOfOrder(OrderTotalRequest request);
}
