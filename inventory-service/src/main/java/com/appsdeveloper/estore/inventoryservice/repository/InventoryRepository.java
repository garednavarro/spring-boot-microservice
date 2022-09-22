package com.appsdeveloper.estore.inventoryservice.repository;

import com.appsdeveloper.estore.inventoryservice.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    List<InventoryEntity> findBySkuCodeIn(List<String> skuCode);

    //Optional<InventoryEntity> findBySkuCode(List<String> skuCode);

    //public boolean isInStock(String skuCode);
}
