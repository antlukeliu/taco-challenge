package com.taco.challenge.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderTotalResponse {

    private BigDecimal total;
}
