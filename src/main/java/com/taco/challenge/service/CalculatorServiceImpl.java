package com.taco.challenge.service;

import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;
import com.taco.challenge.model.FoodPrice;
import com.taco.challenge.repository.FoodPriceRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class CalculatorServiceImpl implements CalculatorService {



    public OrderTotalResponse calculateTotalOfOrder(OrderTotalRequest request) {
        Double total = 0.0;
        FoodPriceRepository foodPriceRepository = new FoodPriceRepository();
        OrderTotalResponse response = new OrderTotalResponse();


        for(FoodItem foodItem : request.getFoodItems()) {
            FoodPrice foodPrice = foodPriceRepository.getFoodPriceById(foodItem.getFoodId());
            total += foodPrice.getFoodPrice() * (double) foodItem.getQuantity();
        }
        response.setTotal(total);

        return response;
    }
}
