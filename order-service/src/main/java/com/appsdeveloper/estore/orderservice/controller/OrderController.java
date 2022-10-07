package com.appsdeveloper.estore.orderservice.controller;

import com.appsdeveloper.estore.orderservice.model.dto.OrderRequestDto;
import com.appsdeveloper.estore.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderServiceImpl orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    //@TimeLimiter(name = "inventory")
    //@Retry(name = "inventory")
    //CompletableFuture<String>
    public String placeOrder(@RequestBody OrderRequestDto orderRequest){
        return orderService.placeOrder(orderRequest);
        //return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
        //orderService.placeOrder(orderRequest);
        //return "Order Placed Succesfully";
    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDto orderRequestDto, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please order after some time!");
        //return "Oops! Something went wrong, please order after some time";
    }
}
