package com.taco.challenge.service;

import com.taco.challenge.dto.FoodItem;
import com.taco.challenge.dto.OrderTotalRequest;
import com.taco.challenge.dto.OrderTotalResponse;
import com.taco.challenge.exception.FoodIdNotFoundException;
import com.taco.challenge.model.FoodPrice;
import com.taco.challenge.repository.FoodPriceRepository;
import com.taco.challenge.util.DiscountCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    public OrderTotalResponse calculateTotalOfOrder(OrderTotalRequest request) {
        BigDecimal total = BigDecimal.ZERO;
        int quantityCount = 0;
        FoodPriceRepository foodPriceRepository = new FoodPriceRepository();
        OrderTotalResponse response = new OrderTotalResponse();


        for(FoodItem foodItem : request.getFoodItems()) {
            FoodPrice foodPrice = foodPriceRepository.getFoodPriceById(foodItem.getFoodId());

            if (foodPrice == null) {
               throw new FoodIdNotFoundException();
            }

            int quantity = foodItem.getQuantity();
            quantityCount += quantity;
            BigDecimal itemTotal = foodPrice.getFoodPrice().multiply(new BigDecimal(quantity));
            total = total.add(itemTotal);
        }

        if (quantityCount >= 4) {
            BigDecimal discount = DiscountCalculator.calculateDiscount(total);
            total = total.subtract(discount);
        }

        response.setTotal(total.setScale(2, RoundingMode.FLOOR));
        response.setOrderId(request.getOrderId());

        return response;
    }
}
