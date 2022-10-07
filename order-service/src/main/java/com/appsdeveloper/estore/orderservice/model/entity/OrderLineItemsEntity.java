package com.appsdeveloper.estore.orderservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "t_order_line_items")
public class OrderLineItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "skucode")
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
