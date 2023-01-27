package com.inventoryService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inventoryService.entity.Inventory;
import com.inventoryService.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone");
			inventory.setQuantity(100);
			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("ball");
			inventory2.setQuantity(20);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);
		};
	}

}
