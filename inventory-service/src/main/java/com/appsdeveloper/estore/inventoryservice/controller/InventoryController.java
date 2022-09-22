package com.appsdeveloper.estore.inventoryservice.controller;

import com.appsdeveloper.estore.inventoryservice.dto.InventoryResponse;
import com.appsdeveloper.estore.inventoryservice.service.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private InventoryServiceImpl inventoryService;

    @Autowired
    public InventoryController(InventoryServiceImpl inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
