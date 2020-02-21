package com.taco.challenge.service;


import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;
import com.taco.challenge.model.FoodPrice;
import com.taco.challenge.repository.FoodPriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    @InjectMocks
    CalculatorServiceImpl calculatorService;

    @Mock
    FoodPriceRepository foodPriceRepository;

    @Test
    public void requestWithAValidFoodId_ReturnsTotalGreaterThanZero() {
        double expectedPrice = 2.50;
        FoodPrice foodPrice = FoodPrice.builder().foodId(1).foodName("Veggie Taco").foodPrice(expectedPrice).build();
        when(foodPriceRepository.getFoodPriceById(anyInt())).thenReturn(foodPrice);

        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Veggie Taco");
        foodItem.setFoodId(1);
        foodItem.setQuantity(1);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(response.getTotal(), expectedPrice, .0001);
    }
}