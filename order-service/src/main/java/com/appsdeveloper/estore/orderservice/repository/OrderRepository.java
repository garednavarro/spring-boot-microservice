package com.appsdeveloper.estore.orderservice.repository;

import com.appsdeveloper.estore.orderservice.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
