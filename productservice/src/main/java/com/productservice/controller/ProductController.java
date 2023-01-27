package com.productservice.controller;

import java.util.List;

import com.productservice.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.productservice.model.Product;
import com.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody Product product) {
		productService.createProduct(product);
	}

	@GetMapping()
	public List<InventoryResponse> checkIsAvailable(@RequestParam List<String> skuCode){
		return productService.checkIsAvailable(skuCode);	}
	
	@GetMapping("/getAll")
	@ResponseStatus(HttpStatus.OK)
	public List<Product> getAllProducts () {
		return productService.getAllProducts();
		
	}
}
