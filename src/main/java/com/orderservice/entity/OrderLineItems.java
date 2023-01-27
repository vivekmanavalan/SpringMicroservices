package com.orderservice.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "t_order_line_items")
public class OrderLineItems {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skucode;
    private BigDecimal price;
    private Integer quantity;
	public OrderLineItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderLineItems(Long id, String skuCode, BigDecimal price, Integer quantity) {
		super();
		this.id = id;
		this.skucode = skucode;
		this.price = price;
		this.quantity = quantity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSkuCode() {
		return skucode;
	}
	public void setSkuCode(String skucode) {
		this.skucode = skucode;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
