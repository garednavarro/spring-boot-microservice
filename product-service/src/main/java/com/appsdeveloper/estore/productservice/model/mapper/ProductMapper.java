package com.appsdeveloper.estore.productservice.model.mapper;

import com.appsdeveloper.estore.productservice.model.dto.ProductResponse;
import com.appsdeveloper.estore.productservice.model.entity.ProductEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;

public class ProductMapper {

    public static ProductResponse convertToDto(ProductEntity product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
