package com.taco.challenge.service;


import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;
import com.taco.challenge.exception.FoodIdNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class CalculatorServiceTest {

    @Autowired
    private CalculatorServiceImpl calculatorService;

    @Before
    public void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @Test
    public void requestWithAValidFoodId_ReturnsTotalGreaterThanZero() {
        BigDecimal expectedPrice = new BigDecimal("2.50");

        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(expectedPrice, response.getTotal());
    }

    @Test
    public void requestWithThreeItems_ReturnNoDiscountedPrice() {
        OrderTotalRequest request = createSimpleOrderRequest(2, "Chicken Taco", 3);

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);

        assertEquals(new BigDecimal("9.00"), response.getTotal());
    }

    @Test
    public void requestWithFourItems_ReturnDiscountedPrice() {
        OrderTotalRequest request = createSimpleOrderRequest(2, "Chicken Taco", 4);

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);

        assertEquals(new BigDecimal("9.60"), response.getTotal());
    }

    @Test
    public void requestWithAValidFoodId_ReturnsTotalWithTwoDecimal() {
        BigDecimal expectedPrice = new BigDecimal("2.50");

        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(expectedPrice.toString(), response.getTotal().toString());
    }

    @Test
    public void requestWithAValidFoodId_ReturnsSameOrderId() {
        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(response.getOrderId(), request.getOrderId());
    }

    @Test(expected = FoodIdNotFoundException.class)
    public void foodIdNotFound_ThrowFoodIdNotFoundException() {
        OrderTotalRequest request = createSimpleOrderRequest(5, "Chicken Taco", 3);

        calculatorService.calculateTotalOfOrder(request);
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

    private OrderTotalRequest createSimpleOrderRequest(int foodId, String description, int quantity) {
        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription(description);
        foodItem.setFoodId(foodId);
        foodItem.setQuantity(quantity);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        return request;
    }
}