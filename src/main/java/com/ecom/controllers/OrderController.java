package com.ecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entities.OrderRequest;
import com.ecom.payload.OrderDto;
import com.ecom.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	String userName = "virat@gmail.com";
	
	@PostMapping("/")
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest){
		OrderDto createOrder = this.orderService.createOrder(orderRequest, userName);
		return new ResponseEntity<OrderDto>(createOrder,HttpStatus.CREATED);
	}

}
