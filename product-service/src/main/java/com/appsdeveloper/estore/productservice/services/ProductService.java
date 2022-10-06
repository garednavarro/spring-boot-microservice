package com.appsdeveloper.estore.productservice.services;

import com.appsdeveloper.estore.productservice.model.dto.ProductRequest;
import com.appsdeveloper.estore.productservice.model.dto.ProductResponse;
import com.appsdeveloper.estore.productservice.model.entity.ProductEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public ResponseEntity<List<ProductResponse>> getAllProducts();

    public ProductEntity findById(int theId);

    public ProductEntity saveProduct(ProductRequest productRequest);

    ResponseEntity<ProductEntity> insert(ProductRequest productRequest);
}
