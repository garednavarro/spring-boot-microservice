package com.appsdeveloper.estore.productservice.services;

import com.appsdeveloper.estore.productservice.dto.ProductRequest;
import com.appsdeveloper.estore.productservice.dto.ProductResponse;
import com.appsdeveloper.estore.productservice.dto.ProductResponse;
import com.appsdeveloper.estore.productservice.entity.ProductEntity;
import com.appsdeveloper.estore.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(ProductEntity product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public ProductEntity findById(int theId) {
        Optional<ProductEntity> product = productRepository.findById(theId);

        if (product.isEmpty())
            throw new RuntimeException(String.format("Product with id %s not found", theId));

        return product.get();
    }

    @Override
    public void save(ProductEntity theEmployee) {
        productRepository.save(theEmployee);
    }

    @Override
    public void insert(ProductRequest productRequest) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        save(productEntity);

        log.info("Product {} is saed", productEntity.getId());

        // return productRequest;
    }
}
