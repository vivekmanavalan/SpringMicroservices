package com.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderservice.dto.OrderLineItemsDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItems;
import com.orderservice.repository.OrderServiceDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderServiceDao dao;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> items = orderRequest.getOrderLineItemsDtoList().stream().map(orderItems -> mapToDto(orderItems)).toList();
		order.setOrderLineItemsList(items);
		dao.save(order);
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderItems) {
		// TODO Auto-generated method stub
		OrderLineItems items = new OrderLineItems();
		items.setId(orderItems.getId());
		items.setSkuCode(orderItems.getSkuCode());
		items.setPrice(orderItems.getPrice());
		items.setQuantity(orderItems.getQuantity());
		return items;
	}
}
