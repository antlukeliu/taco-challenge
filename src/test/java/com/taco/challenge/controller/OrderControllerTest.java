package com.taco.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.exception.FoodIdNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderController orderController;
    
    @Test
    public void ValidRequestBody_ReturnHttpOk() throws Exception {

        OrderTotalRequest request = createSimpleOrderRequest();

        String jsonRequest = new ObjectMapper().writeValueAsString(request);


        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void OrderIdNotSet_ReturnBadRequest() throws Exception {

        OrderTotalRequest request = new OrderTotalRequest();

        String jsonRequest = new ObjectMapper().writeValueAsString(request);


        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void EmptyFoodItemList_ReturnBadRequest() throws Exception {

        OrderTotalRequest request = new OrderTotalRequest();
        request.setOrderId(1);
        String jsonRequest = new ObjectMapper().writeValueAsString(request);


        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void QuantityonFoodItemListEqualZero_ReturnBadRequest() throws Exception {

        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Veggie Taco");
        foodItem.setFoodId(1);
        foodItem.setQuantity(0);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        String jsonRequest = new ObjectMapper().writeValueAsString(request);


        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void FoodIdonFoodItemListEqualZero_ReturnBadRequest() throws Exception {

        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Veggie Taco");
        foodItem.setFoodId(0);
        foodItem.setQuantity(1);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        String jsonRequest = new ObjectMapper().writeValueAsString(request);


        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void passingInInvalidFoodId_ThrowFoodIdNotFoundException() {
        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Veggie Taco");
        foodItem.setFoodId(10000);
        foodItem.setQuantity(1);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        assertThatExceptionOfType(FoodIdNotFoundException.class).isThrownBy(() -> orderController.retrieveOrderTotal(request));
    }

    @Test
    public void passingInInvalidFoodId_ReturnStatusCode404() throws Exception {
        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Veggie Taco");
        foodItem.setFoodId(10000);
        foodItem.setQuantity(1);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
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
