package com.appsdeveloper.estore.orderservice.service;

import com.appsdeveloper.estore.orderservice.model.dto.OrderLineItemsDto;
import com.appsdeveloper.estore.orderservice.model.dto.OrderRequestDto;
import com.appsdeveloper.estore.orderservice.model.entity.OrderLineItemsEntity;

public interface OrderService {
    public String placeOrder(OrderRequestDto orderRequestDto);

    public OrderLineItemsEntity mapToDto(OrderLineItemsDto orderLineItemsDto);

}
