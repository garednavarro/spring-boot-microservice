package com.appsdeveloper.estore.inventoryservice;

import com.appsdeveloper.estore.inventoryservice.entity.InventoryEntity;
import com.appsdeveloper.estore.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            InventoryEntity inventory = new InventoryEntity();
            inventory.setSkuCode("iphone_13");
            inventory.setQuantity(100);

            InventoryEntity inventory1 = new InventoryEntity();
            inventory1.setSkuCode("iphone_13_red");
            inventory1.setQuantity(0);

            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory1);
        };
    }

}
