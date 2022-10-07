package com.appsdeveloper.estore.orderservice.model.mapper;

import com.appsdeveloper.estore.orderservice.model.dto.OrderLineItemsDto;
import com.appsdeveloper.estore.orderservice.model.entity.OrderLineItemsEntity;

public class OrderMapper {

    public static OrderLineItemsEntity mapToDto(OrderLineItemsDto orderLineItemsDto) {

        return OrderLineItemsEntity
                .builder()
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }
}
