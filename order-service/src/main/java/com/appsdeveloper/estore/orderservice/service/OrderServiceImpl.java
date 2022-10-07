package com.appsdeveloper.estore.orderservice.service;

import com.appsdeveloper.estore.orderservice.exception.OrderNotPlacedException;
import com.appsdeveloper.estore.orderservice.model.dto.InventoryResponse;
import com.appsdeveloper.estore.orderservice.model.dto.OrderLineItemsDto;
import com.appsdeveloper.estore.orderservice.model.dto.OrderRequestDto;
import com.appsdeveloper.estore.orderservice.model.entity.OrderEntity;
import com.appsdeveloper.estore.orderservice.model.entity.OrderLineItemsEntity;
import com.appsdeveloper.estore.orderservice.event.OrderPlacedEvent;
import com.appsdeveloper.estore.orderservice.model.mapper.OrderMapper;
import com.appsdeveloper.estore.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

        setUpOrder(orderRequestDto, order);

        if (allProductsAreInStock(order)) {
            return placeOrderSuccessfully(order);
        } else {
            throw new OrderNotPlacedException("Product is not in stock, please try again later");
        }
    }

    private void setUpOrder(OrderRequestDto orderRequestDto, OrderEntity order) {
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItemsEntity> orderLineItemsEntities = collectMappedEntities(orderRequestDto);

        order.setOrderLineItemsEntities(orderLineItemsEntities);
    }

    private List<OrderLineItemsEntity> collectMappedEntities(OrderRequestDto orderRequestDto) {
        return orderRequestDto.getOrderLineItemsDtos()
                .stream()
                .map(OrderMapper::mapToDto).collect(Collectors.toList());
    }

    public List<String> getSkuCodesFromOrder(OrderEntity order) {
        return order
                .getOrderLineItemsEntities()
                .stream()
                .map(OrderLineItemsEntity::getSkuCode)
                .collect(Collectors.toList());
    }

    private InventoryResponse[] makeSynchronousComunicationWithInventoryService(List<String> skuCodes) {
        return webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
    }

    private boolean allProductsAreInStock(OrderEntity order) {
        List<String> skuCodes = getSkuCodesFromOrder(order);
        return Arrays.stream(makeSynchronousComunicationWithInventoryService(skuCodes)).allMatch(InventoryResponse::isInStock);
    }

    private String placeOrderSuccessfully(OrderEntity order){
        orderRepository.save(order);
        kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
        return "Order Placed Successfully";
    }
}
