package com.taco.challenge.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderTotalRequest {

    private int orderId;

    private List<FoodItem> foodItems;
}
