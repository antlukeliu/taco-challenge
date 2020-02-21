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
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
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
        mockRetrievingPrice(1, "Veggie Taco", expectedPrice);

        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(expectedPrice, response.getTotal());
    }

    @Test
    public void requestWithAValidFoodId_ReturnsTotalWithTwoDecimal() {
        BigDecimal expectedPrice = new BigDecimal("2.50");
        mockRetrievingPrice(1, "Veggie Taco", expectedPrice);

        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(expectedPrice.toString(), response.getTotal().toString());
    }

    @Test
    public void requestWithAValidFoodId_ReturnsSameOrderId() {
        mockRetrievingPrice(1, "Veggie Taco", new BigDecimal("2.50"));

        OrderTotalRequest request = createSimpleOrderRequest();

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);
        assertEquals(response.getOrderId(), request.getOrderId());
    }

    @Test
    public void requestWithFourItems_ReturnDiscountedPrice() {
        BigDecimal expectedPrice = new BigDecimal("3.00");
        mockRetrievingPrice(2, "Chicken Taco", expectedPrice);

        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Chicken Taco");
        foodItem.setFoodId(2);
        foodItem.setQuantity(4);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);

        assertEquals(new BigDecimal("9.60"), response.getTotal());
    }

    @Test
    public void requestWithThreeItems_ReturnNoDiscountedPrice() {
        BigDecimal expectedPrice = new BigDecimal("3.00");
        mockRetrievingPrice(2, "Chicken Taco", expectedPrice);

        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Chicken Taco");
        foodItem.setFoodId(2);
        foodItem.setQuantity(3);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        OrderTotalResponse response = calculatorService.calculateTotalOfOrder(request);

        assertEquals(new BigDecimal("9.00"), response.getTotal());
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

    private void mockRetrievingPrice(int id, String foodName, BigDecimal expectedPrice) {
        FoodPrice foodPrice = FoodPrice.builder().foodId(id).foodName(foodName).foodPrice(expectedPrice).build();
        when(foodPriceRepository.getFoodPriceById(id)).thenReturn(foodPrice);
    }
}