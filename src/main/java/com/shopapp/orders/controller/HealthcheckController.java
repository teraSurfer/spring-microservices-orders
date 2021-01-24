package com.shopapp.orders.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HealthcheckController {

    @GetMapping()
    public ResponseEntity<Map<String, String>> getHealthStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("Message", "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
