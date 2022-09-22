package com.appsdeveloper.estore.productservice.repository;

import com.appsdeveloper.estore.productservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
