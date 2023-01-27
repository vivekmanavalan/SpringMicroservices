package com.inventoryService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryService.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findBySkuCode(String skuCode);
}
