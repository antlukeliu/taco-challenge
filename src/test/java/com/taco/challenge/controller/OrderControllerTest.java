package com.taco.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void ValidRequestBody_ReturnHttpOk() throws Exception {

        OrderTotalRequest request = new OrderTotalRequest();
        request.setOrderId(1);

        FoodItem foodItem = new FoodItem();



        String jsonRequest = new ObjectMapper().writeValueAsString(request);


        mvc.perform(post("/order/total").content(jsonRequest).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}
