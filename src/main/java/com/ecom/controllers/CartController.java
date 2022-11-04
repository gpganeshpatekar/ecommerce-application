package com.ecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	String userName = "virat@gmail.com";
//	String userName = "rohit@gmail.com";
	@PostMapping(value = "/",consumes = "application/json",produces = "application/json" )
	public ResponseEntity<CartDto> addItemToCart(@RequestBody ItemRequest request){
		CartDto addItem = this.cartService.addItem(request, userName);
		return new ResponseEntity<CartDto>(addItem,HttpStatus.OK);
	}
	
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<CartDto> getCart(){
		CartDto cartDto = this.cartService.getCart(userName);
		return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<CartDto> removeProductFromCart(@PathVariable Integer productId){
		CartDto removeItem = this.cartService.removeItem(userName, productId);
		return new ResponseEntity<CartDto>(removeItem,HttpStatus.OK);
	}
}
