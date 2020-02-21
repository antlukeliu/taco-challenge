package com.taco.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.challenge.ChallengeApplication;
import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.exception.FoodIdNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
@ComponentScan({"com.taco.challenge"})
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

        OrderTotalRequest request = createSimpleOrderRequest(1, 0, "Veggie Taco");
        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void FoodIdonFoodItemListEqualZero_ReturnBadRequest() throws Exception {

        OrderTotalRequest request = createSimpleOrderRequest(0, 1, "Veggie Taco");
        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    public void passingInInvalidFoodId_ThrowFoodIdNotFoundException() {
        OrderTotalRequest request = createSimpleOrderRequest(10000, 1, "Burrito");

        assertThatExceptionOfType(FoodIdNotFoundException.class).isThrownBy(() -> orderController.retrieveOrderTotal(request));
    }

    @Test
    public void passingInInvalidFoodId_ReturnStatusCode404AndCustomMessage() throws Exception {
        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription("Veggie Taco");
        foodItem.setFoodId(10000);
        foodItem.setQuantity(1);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        MvcResult result = mvc.perform(post("/order/total").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();

        Optional<FoodIdNotFoundException> exception = Optional.ofNullable((FoodIdNotFoundException) result.getResolvedException());
        exception.ifPresent((err) -> assertEquals(err.getMessage(), "Food item submitted to calculate total has no price stored"));

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

    private OrderTotalRequest createSimpleOrderRequest(int foodId, int quantity, String desc) {
        OrderTotalRequest request = new OrderTotalRequest();
        FoodItem foodItem = new FoodItem();
        foodItem.setFoodDescription(desc);
        foodItem.setFoodId(foodId);
        foodItem.setQuantity(quantity);
        request.getFoodItems().add(foodItem);
        request.setOrderId(1);

        return request;
    }
}

