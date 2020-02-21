package com.taco.challenge.model;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class FoodPrice {

    private int foodId;
    private String foodName;
    private BigDecimal foodPrice;
}
