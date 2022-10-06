package com.appsdeveloper.estore.orderservice.service;

import com.appsdeveloper.estore.orderservice.dto.OrderLineItemsDto;
import com.appsdeveloper.estore.orderservice.dto.OrderRequestDto;
import com.appsdeveloper.estore.orderservice.entity.OrderLineItemsEntity;

public interface OrderService {
    public String placeOrder(OrderRequestDto orderRequestDto);

    public OrderLineItemsEntity mapToDto(OrderLineItemsDto orderLineItemsDto);

}
