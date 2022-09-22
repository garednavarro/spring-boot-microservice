package com.appsdeveloper.estore.orderservice.service;

import com.appsdeveloper.estore.orderservice.dto.InventoryResponse;
import com.appsdeveloper.estore.orderservice.dto.OrderLineItemsDto;
import com.appsdeveloper.estore.orderservice.dto.OrderRequestDto;
import com.appsdeveloper.estore.orderservice.entity.OrderEntity;
import com.appsdeveloper.estore.orderservice.entity.OrderLineItemsEntity;
import com.appsdeveloper.estore.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, WebClient.Builder webClientBuilder) {
        this.orderRepository = orderRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public void placeOrder(OrderRequestDto orderRequestDto) {
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItemsEntity> orderLineItemsEntities = orderRequestDto.getOrderLineItemsDtos()
                .stream()
                .map(this::mapToDto).collect(Collectors.toList());
        order.setOrderLineItemsEntities(orderLineItemsEntities);

        List<String> skuCodes = order
                .getOrderLineItemsEntities()
                .stream()
                .map(OrderLineItemsEntity::getSkuCode)
                .collect(Collectors.toList());
        // call inventory service, and place order if product is in stock
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert inventoryResponses != null;

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock)
            orderRepository.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock, please try again later");

    }

    @Override
    public OrderLineItemsEntity mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItemsEntity orderLineItems = new OrderLineItemsEntity();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
