package com.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productservice.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

}
