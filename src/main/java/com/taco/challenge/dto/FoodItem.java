package com.taco.challenge.dto;


import lombok.Data;

@Data
public class FoodItem {

    private long foodId;

    private String foodDescription;

    private int quantity;

}
