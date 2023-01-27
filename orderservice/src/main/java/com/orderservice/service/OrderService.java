package com.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.orderservice.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderservice.dto.OrderLineItemsDto;
import com.orderservice.dto.OrderRequest;
import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItems;
import com.orderservice.repository.OrderServiceDao;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderServiceDao dao;
	@Autowired
	private WebClient.Builder webClient;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> items = orderRequest.getOrderLineItemsDtoList().stream().map(orderItems -> mapToDto(orderItems)).toList();
		order.setOrderLineItemsList(items);
		List<String> skuCodes = order.getOrderLineItemsList().stream()
				.map(orderLineItems -> orderLineItems.getSkuCode()).toList();

		//Making call to inventory service to check if the product is in stock
		//inter process communication using webclient in confif folder
		InventoryResponse[] inventoryResponses = webClient.build().get().uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve().bodyToMono(InventoryResponse[].class).block();
		//In the above code block() method is added to make a sync request and bodyToMono() to retrieve value from that
		//microservice
		//In the below code we already have the responses from the inventory service in the inventoryresponses array
		//so we can get the is in stock of each skucode from the dto class and check if all is true

		//Contacting product service to check if the product is available in our store.
		InventoryResponse[] productResponses = webClient.build().get()
				.uri("http://product-service/api/product",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve().bodyToMono(InventoryResponse[].class).block();
		List<String> names = Arrays.stream(productResponses).map(pr -> pr.getSkuCode()).toList();


		boolean productResult = names.size() > 0 ?
				Arrays.stream(productResponses).allMatch(productResponse -> productResponse.isInStock()) : false;
		boolean inventoryResult = Arrays.stream(inventoryResponses)
				.allMatch(inventoryResponse -> inventoryResponse.isInStock());
		if(inventoryResult && productResult) dao.save(order);
		else throw new IllegalArgumentException("Product is not in stock. Please comeback later");
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderItems) {
		// TODO Auto-generated method stub
		OrderLineItems items = new OrderLineItems();
		items.setSkuCode(orderItems.getSkuCode());
		items.setPrice(orderItems.getPrice());
		items.setQuantity(orderItems.getQuantity());
		return items;
	}
}
