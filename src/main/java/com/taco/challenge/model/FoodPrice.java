package com.taco.challenge.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodPrice {

    private int foodId;
    private String foodName;
    private double foodPrice;
}
