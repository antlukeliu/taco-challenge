package com.taco.challenge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderTotalResponse {

    private int orderId;
    private BigDecimal total;
}
