package com.shopapp.orders.controller;

import com.shopapp.orders.model.Orders;
import com.shopapp.orders.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RepositoryRestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("")
    public ResponseEntity<?> getOrders() {
        Iterable<Orders> orders = orderRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders);
        response.put("message", "success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody Orders orders) {
        log.info(orders.toString());
        Orders savedOrders = orderRepository.save(orders);
        Map<String, Object> response = new HashMap<>();
        response.put("order", savedOrders);
        response.put("message", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") long id) {
        Optional<Orders> orderExists = orderRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        orderExists.ifPresentOrElse((orders) -> {
            response.put("order", orders);
            response.put("message", "success");
            response.put("status", HttpStatus.OK);
        }, () -> {
            response.put("error", "no such order");
            response.put("message", "sorry");
            response.put("status", HttpStatus.NOT_FOUND);
        });

        return new ResponseEntity(response, (HttpStatus) response.get("status"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") long id, @RequestBody Orders orders) {
        Orders existingOrders = orderRepository.findById(id).get();
        existingOrders.setStatus(orders.getStatus());
        existingOrders.setShippingAddress(orders.getShippingAddress());
        existingOrders.setTotalPrice(orders.getTotalPrice());
        Map<String, Object> response = new HashMap<>();
        Orders updatedOrders = orderRepository.save(existingOrders);
        response.put("order", updatedOrders);
        response.put("message", "updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id) {
        Map<String, Object> response = new HashMap<>();
        orderRepository.deleteById(id);
        response.put("message", "deleted successfully");
        return ResponseEntity.ok(response);
    }
}
