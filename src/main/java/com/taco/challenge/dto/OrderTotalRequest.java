package com.taco.challenge.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderTotalRequest {

    @Min(value=1, message="Order Id needed")
    private int orderId;

    @Valid
    @NotEmpty(message="Request total on zero food items")
    private List<FoodItem> foodItems = new ArrayList<>();
}
