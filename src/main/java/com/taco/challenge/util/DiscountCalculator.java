package com.taco.challenge.util;

import java.math.BigDecimal;

public class DiscountCalculator {

    private static final BigDecimal DISCOUNT_RATE = new BigDecimal(".20");

    public static BigDecimal calculateDiscount(BigDecimal total) {
        return total.multiply(DISCOUNT_RATE).setScale(2, BigDecimal.ROUND_FLOOR);
    }
}
