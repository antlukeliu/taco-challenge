package com.taco.challenge.repository;

import com.taco.challenge.model.FoodPrice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FoodPriceRepositoryTest {

    private FoodPriceRepository foodPriceRepository;

    @Before
    public void setup() {
        foodPriceRepository = new FoodPriceRepository();
    }

    @Test
    public void passingValidId_ReturnsNonNullFoodPrice() {

        FoodPrice foodPrice = foodPriceRepository.getFoodPriceById(1);

        assertNotNull(foodPrice);
    }
}
