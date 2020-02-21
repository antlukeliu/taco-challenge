package com.taco.challenge.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class OrderTotalResponse {

    @Min(1)
    private int orderId;
    private BigDecimal total;
}
