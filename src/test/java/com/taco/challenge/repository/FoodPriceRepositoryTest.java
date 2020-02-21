package com.taco.challenge.repository;

import com.taco.challenge.model.FoodPrice;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = FoodPriceRepository.class)
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

    @Test
    public void validateStaticPricesHaveNotChanged() {

        FoodPrice veggieTaco = foodPriceRepository.getFoodPriceById(1);
        FoodPrice chickenTaco = foodPriceRepository.getFoodPriceById(2);
        FoodPrice beefTaco = foodPriceRepository.getFoodPriceById(3);
        FoodPrice chorizoTaco = foodPriceRepository.getFoodPriceById(4);

        assertEquals(new BigDecimal("2.50"),veggieTaco.getFoodPrice());
        assertEquals(new BigDecimal("3.00"),chickenTaco.getFoodPrice());
        assertEquals(new BigDecimal("3.00"),beefTaco.getFoodPrice());
        assertEquals(new BigDecimal("3.50"),chorizoTaco.getFoodPrice());
    }
}
