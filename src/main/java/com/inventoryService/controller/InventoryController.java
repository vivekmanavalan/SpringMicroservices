package com.inventoryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.inventoryService.service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	@GetMapping("/{skuCode}")
	public boolean isInStock(@PathVariable String skuCode) {
		return inventoryService.isInStock(skuCode);
	}
}
