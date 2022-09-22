package com.appsdeveloper.estore.inventoryservice.service;

import com.appsdeveloper.estore.inventoryservice.dto.InventoryResponse;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
    //public Optional<InventoryResponse> isInStock(List<String> skuCode);
    //public boolean isInStock(List<String> skuCode);
}
