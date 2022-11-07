package com.ecom.services;

import java.util.List;

import com.ecom.entities.OrderRequest;
import com.ecom.payload.OrderDto;

public interface OrderService {
	
//	create order
	
	OrderDto createOrder(OrderRequest orderRequest,String username);
//	get all order
	List<OrderDto> getAllOrders();
//	get single order
	OrderDto getOrder(Integer orderId);
//	update order
	OrderDto updateOrder(OrderDto orderDto,Integer orderId);
//	delete order
	void deleteOrder(Integer orderId);


}
