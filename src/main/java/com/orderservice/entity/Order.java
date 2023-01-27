package com.orderservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_order")
public class Order {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String orderNumber;
	    @OneToMany(targetEntity = OrderLineItems.class,cascade = CascadeType.ALL)
	    @JoinColumn(name = "order_id", referencedColumnName = "id")
	    private List<OrderLineItems> orderLineItemsList;
		public Order() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Order(Long id, String orderNumber, List<OrderLineItems> orderLineItemsList) {
			super();
			this.id = id;
			this.orderNumber = orderNumber;
			this.orderLineItemsList = orderLineItemsList;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(String orderNumber) {
			this.orderNumber = orderNumber;
		}
		public List<OrderLineItems> getOrderLineItemsList() {
			return orderLineItemsList;
		}
		public void setOrderLineItemsList(List<OrderLineItems> orderLineItemsList) {
			this.orderLineItemsList = orderLineItemsList;
		}
}
