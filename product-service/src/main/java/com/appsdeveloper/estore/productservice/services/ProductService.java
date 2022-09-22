package com.appsdeveloper.estore.productservice.services;

import com.appsdeveloper.estore.productservice.dto.ProductRequest;
import com.appsdeveloper.estore.productservice.dto.ProductResponse;
import com.appsdeveloper.estore.productservice.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public List<ProductResponse> getAllProducts();

    public ProductEntity findById(int theId);

    public void save(ProductEntity theEmployee);

    void insert(ProductRequest productRequest);
}
