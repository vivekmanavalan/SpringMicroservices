package com.inventoryService.controller;

import com.inventoryService.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.inventoryService.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	//The input would be like below
	//http://localhost:8082/api/inventory?skuCode=iphone&skuCode=ball
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
		System.out.print(skuCode);
		return inventoryService.isInStock(skuCode);
	}
}
