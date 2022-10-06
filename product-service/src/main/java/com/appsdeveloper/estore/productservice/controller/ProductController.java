package com.appsdeveloper.estore.productservice.controller;

import com.appsdeveloper.estore.productservice.model.dto.ProductRequest;
import com.appsdeveloper.estore.productservice.model.dto.ProductResponse;
import com.appsdeveloper.estore.productservice.model.entity.ProductEntity;
import com.appsdeveloper.estore.productservice.services.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.insert(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        //return ResponseEntity.ok();
        return productService.getAllProducts();
    }

}
