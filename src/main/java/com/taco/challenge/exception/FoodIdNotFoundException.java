package com.taco.challenge.exception;

public class FoodIdNotFoundException extends RuntimeException{
    public FoodIdNotFoundException() {
        super("Food item submitted to calculate total has no price stored");
    }
}
