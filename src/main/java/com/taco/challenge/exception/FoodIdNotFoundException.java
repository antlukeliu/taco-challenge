package com.taco.challenge.exception;

public class FoodIdNotFoundException extends RuntimeException{
    public FoodIdNotFoundException() {
        super("Food item submited to retrieve total has no price stored");
    }
}
