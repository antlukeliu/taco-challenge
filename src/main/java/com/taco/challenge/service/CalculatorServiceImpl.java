package com.taco.challenge.service;

import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;
import com.taco.challenge.model.FoodPrice;
import com.taco.challenge.repository.FoodPriceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

@Service
public class CalculatorServiceImpl implements CalculatorService {



    public OrderTotalResponse calculateTotalOfOrder(OrderTotalRequest request) {
        BigDecimal total = BigDecimal.ZERO;
        FoodPriceRepository foodPriceRepository = new FoodPriceRepository();
        OrderTotalResponse response = new OrderTotalResponse();


        for(FoodItem foodItem : request.getFoodItems()) {
            FoodPrice foodPrice = foodPriceRepository.getFoodPriceById(foodItem.getFoodId());
            BigDecimal itemTotal = foodPrice.getFoodPrice().multiply(new BigDecimal(foodItem.getQuantity()));
            total = total.add(itemTotal);
        }
        response.setTotal(total.setScale(2, RoundingMode.FLOOR));

        return response;
    }
}
