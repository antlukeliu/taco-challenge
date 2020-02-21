package com.taco.challenge.repository;

import com.taco.challenge.model.FoodPrice;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodPriceRepository {

    private static final Map<Integer, FoodPrice> foodPriceMap = Stream.of(new Object[][] {
            { 1, FoodPrice.builder().foodId(1).foodName("Veggie Tacos").foodPrice(2.50).build() },
            { 2, FoodPrice.builder().foodId(2).foodName("Chicken Tacos").foodPrice(3.00).build() },
            { 3, FoodPrice.builder().foodId(3).foodName("Beef Tacos").foodPrice(3.00).build() },
            { 4, FoodPrice.builder().foodId(4).foodName("Chorizo Tacos").foodPrice(3.50).build() },
    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (FoodPrice) data[1]));


    public FoodPrice getFoodPriceById(int id) {
        return foodPriceMap.get(id);
    }
}
