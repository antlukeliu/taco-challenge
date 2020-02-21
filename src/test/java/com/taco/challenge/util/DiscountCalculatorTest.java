package com.taco.challenge.util;

import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = DiscountCalculator.class)
public class DiscountCalculatorTest {

    @Test
    public void discountOfHundred_ReturnsEighty() {
        assertEquals(new BigDecimal("20.00"), DiscountCalculator.calculateDiscount(new BigDecimal("100.00")));
    }
}
