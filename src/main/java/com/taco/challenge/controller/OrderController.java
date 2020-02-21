package com.taco.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping(value="/total", produces={"application/json"})
    public ResponseEntity<Object> retrieveOrderTotal() {
        return new ResponseEntity<Object>("", HttpStatus.OK);
    }
}
