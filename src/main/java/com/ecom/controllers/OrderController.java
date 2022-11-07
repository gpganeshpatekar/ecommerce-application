package com.ecom.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping(value = "/{principal}", consumes = "application/json",produces = "application/json")
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest, @PathVariable Principal principal){
		OrderDto createOrder = this.orderService.createOrder(orderRequest, principal.getName());
		return new ResponseEntity<OrderDto>(createOrder,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<List<OrderDto>> getAllOrders(){
		List<OrderDto> orders = this.orderService.getAllOrders();
		return new ResponseEntity<List<OrderDto>>(orders,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{orderId}",produces = "application/json")
	public ResponseEntity<OrderDto> getOrderByOrderId(@PathVariable Integer orderId){
		OrderDto order = this.orderService.getOrder(orderId);
		return new ResponseEntity<OrderDto>(order,HttpStatus.OK);
	}
	
	

}
