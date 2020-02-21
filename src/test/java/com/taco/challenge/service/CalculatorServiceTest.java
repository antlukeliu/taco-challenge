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

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CalculatorServiceTest {

    @InjectMocks
    CalculatorServiceImpl calculatorService;

    @Mock
    FoodPriceRepository foodPriceRepository;

    @Test
    public void requestWithAValidFoodId_ReturnsTotalGreaterThanZero() {
        BigDecimal expectedPrice = new BigDecimal("2.50");
        FoodPrice foodPrice = FoodPrice.builder().foodId(1).foodName("Veggie Taco").foodPrice(expectedPrice).build();
        when(foodPriceRepository.getFoodPriceById(1)).thenReturn(foodPrice);

        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(expectedPrice, response.getTotal());
    }

    @Test
    public void requestWithAValidFoodId_ReturnsTotalWithTwoDecimal() {
        BigDecimal expectedPrice = new BigDecimal("2.50");
        FoodPrice foodPrice = FoodPrice.builder().foodId(1).foodName("Veggie Taco").foodPrice(expectedPrice).build();
        when(foodPriceRepository.getFoodPriceById(1)).thenReturn(foodPrice);

        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(expectedPrice.toString(), response.getTotal().toString());
    }

    private OrderTotalRequest createSimpleOrderRequest() {
        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Veggie Taco");
        foodItem.setFoodId(1);
        foodItem.setQuantity(1);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        return request;
    }
}