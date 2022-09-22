package com.appsdeveloper.estore.orderservice.controller;

import com.appsdeveloper.estore.orderservice.dto.OrderRequestDto;
import com.appsdeveloper.estore.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public String placeOrder(@RequestBody OrderRequestDto orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order Placed Succesfully";
    }
}
