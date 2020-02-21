package com.taco.challenge.controller;

import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;
import com.taco.challenge.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Validated
@RequestMapping("/order")
public class OrderController {

    private final CalculatorService calculatorService;

    OrderController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping(value="/total", produces={"application/json"})
    public ResponseEntity<OrderTotalResponse> retrieveOrderTotal(@Valid @RequestBody OrderTotalRequest orderTotalRequest) {
        return new ResponseEntity<>(calculatorService.calculateTotalOfOrder(orderTotalRequest), HttpStatus.OK);
    }
}
