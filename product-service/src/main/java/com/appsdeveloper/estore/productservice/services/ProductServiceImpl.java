package com.appsdeveloper.estore.productservice.services;

import com.appsdeveloper.estore.productservice.model.dto.ProductRequest;
import com.appsdeveloper.estore.productservice.model.dto.ProductResponse;
import com.appsdeveloper.estore.productservice.model.entity.ProductEntity;
import com.appsdeveloper.estore.productservice.exceptions.ProductNotFoundException;
import com.appsdeveloper.estore.productservice.model.mapper.ProductMapper;
import com.appsdeveloper.estore.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return ResponseEntity.ok(productRepository.findAll().stream().map(ProductMapper::convertToDto).collect(Collectors.toList()));
        //return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
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
        return productRepository.findById(theId).orElseThrow(ProductNotFoundException::new);
        //ProductEntity product = productRepository.findById(theId).orElseThrow(ProductNotFoundException::new);
        /*if (product.isEmpty())
            //throw new RuntimeException(String.format("Product with id %s not found", theId));
            throw new ProductNotFoundException();*/
        //return product.get();
    }

    @Override
    public ProductEntity saveProduct(ProductRequest productRequest) {
        return productRepository.save(ProductEntity.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build());
    }

    @Override
    public ResponseEntity<ProductEntity> insert(ProductRequest productRequest) {
        if (productRequest == null) throw new ProductNotFoundException("NULL INPUT, YOU CAN'T SEND A NULL INPUT");

        return ResponseEntity.ok(saveProduct(productRequest));
    }
}
