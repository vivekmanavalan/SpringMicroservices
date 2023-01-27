package com.productservice.service;

import java.util.List;

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
}
