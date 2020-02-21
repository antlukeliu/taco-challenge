package com.taco.challenge.dto;


import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class FoodItem {

    @Min(value=1, message="foodId is less than one. Something went wrong")
    private int foodId;
    private String foodDescription;
    @Min(value=1, message="Quantity should be greater than zero")
    private int quantity;

}
