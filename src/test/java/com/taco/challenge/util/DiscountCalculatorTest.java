package com.taco.challenge.util;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DiscountCalculatorTest {

    @Test
    public void discountOfHundred_ReturnsEighty() {
        assertEquals(new BigDecimal("20.00"), DiscountCalculator.calculateDiscount(new BigDecimal("100.00")));
    }
}
