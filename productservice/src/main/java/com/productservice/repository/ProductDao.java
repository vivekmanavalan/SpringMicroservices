package com.productservice.repository;

import com.productservice.dto.InventoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import com.productservice.model.Product;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findByNameIn(List<String> skuCode);
}
