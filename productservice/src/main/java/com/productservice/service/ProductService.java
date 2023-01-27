package com.productservice.service;

import java.util.List;

import com.productservice.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productservice.dto.ProductRequest;
import com.productservice.model.Product;
import com.productservice.repository.ProductDao;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	public void createProduct(Product productRequest) {
		productDao.save(productRequest);
	}
	
	public List<Product> getAllProducts(){
		return productDao.findAll();
	}

	public List<InventoryResponse> checkIsAvailable(List<String> skuCode){
		return productDao.findByNameIn(skuCode).stream()
				.map(productResponse -> InventoryResponse.builder().skuCode(productResponse.getName())
						.isInStock(productResponse.getName().length() > 0).build()).toList();
	}
}
