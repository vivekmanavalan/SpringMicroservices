package com.inventoryService.repository;

import java.util.List;
import java.util.Optional;

import com.inventoryService.dto.InventoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inventoryService.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
