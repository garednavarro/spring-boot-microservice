package com.appsdeveloper.estore.orderservice.service;

import com.appsdeveloper.estore.orderservice.model.dto.InventoryResponse;
import com.appsdeveloper.estore.orderservice.model.dto.OrderLineItemsDto;
import com.appsdeveloper.estore.orderservice.model.dto.OrderRequestDto;
import com.appsdeveloper.estore.orderservice.model.entity.OrderEntity;
import com.appsdeveloper.estore.orderservice.model.entity.OrderLineItemsEntity;
import com.appsdeveloper.estore.orderservice.event.OrderPlacedEvent;
import com.appsdeveloper.estore.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public String placeOrder(OrderRequestDto orderRequestDto) {
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

        if (allProductsInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order Placed Successfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
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
